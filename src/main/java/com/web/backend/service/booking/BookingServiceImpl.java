package com.web.backend.service.booking;

import com.web.backend.constant.PaymentStatus;
import com.web.backend.dto.request.booking.BookingRequest;
import com.web.backend.dto.response.booking.BookingResponse;
import com.web.backend.dto.response.ticket.TicketResponse;
import com.web.backend.entity.*;
import com.web.backend.exception.AppException;
import com.web.backend.exception.ErrorCode;
import com.web.backend.mapper.ShowtimeMapper;
import com.web.backend.mapper.SeatMapper;
import com.web.backend.mapper.TicketMapper;
import com.web.backend.repository.*;
import com.web.backend.service.qr.QRCodeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ShowtimeMapper showtimeMapper;

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private QRCodeService qrCodeService;

    @Override
    public BookingResponse bookTickets(BookingRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Validate showtime exists
        Showtime showtime = showtimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_EXISTED));

        // Validate seats exist and are available
        List<Seat> seats = new ArrayList<>();
        for (Integer seatId : request.getSeatIds()) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_EXISTED));

            // Check if seat is already booked for this showtime
            boolean isBooked = ticketRepository.existsByShowtimeIdAndSeatIdAndStatus(
                    request.getShowtimeId(), seatId, true);

            if (isBooked) {
                throw new AppException(ErrorCode.INVALID_KEY); // Seat already booked
            }

            seats.add(seat);
        }

        // Calculate total amount
        int totalAmount = seats.size() * showtime.getTicketPrice();

        // Create invoice
        Invoice invoice = Invoice.builder()
                .username(username)
                .totalAmount(totalAmount)
                .paymentStatus(PaymentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
        invoice = invoiceRepository.save(invoice);

        // Create tickets
        List<Ticket> tickets = new ArrayList<>();

        for (Seat seat : seats) {
            Ticket ticket = Ticket.builder()
                    .showtimeId(request.getShowtimeId())
                    .seatId(seat.getId())
                    .invoiceId(invoice.getId())
                    .price(showtime.getTicketPrice())
                    .status(true) // Mark as booked
                    .createdAt(LocalDateTime.now())
                    .build();

            ticket = ticketRepository.save(ticket);

            // Generate QR code for the ticket
            String qrCode = qrCodeService.generateQRCode(ticket.getId());
            ticket.setQrCode(qrCode);
            ticket = ticketRepository.save(ticket);
            tickets.add(ticket);
        }

        // Build response
        return BookingResponse.builder()
                .invoiceId(invoice.getId())
                .username(username)
                .totalAmount(totalAmount)
                .paymentStatus(invoice.getPaymentStatus())
                .vnpayTransactionId(invoice.getVnpayTransactionId())
                .bookingTime(invoice.getCreatedAt())
                .paidAt(invoice.getPaidAt())
                .showtime(showtimeMapper.toShowtimeResponse(showtime))
                .seats(seats.stream().map(seatMapper::toSeatResponse).collect(Collectors.toList()))
                .tickets(tickets.stream().map(ticketMapper::toTicketResponse).collect(Collectors.toList()))
                .build();
    }

    @Override
    public BookingResponse getBookingById(Integer invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Get tickets for this invoice
        List<Ticket> tickets = ticketRepository.findByInvoiceId(invoiceId);

        if (tickets.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        // Get showtime from first ticket (all tickets should have same showtime)
        Showtime showtime = tickets.get(0).getShowtime();

        // Get seats
        List<Seat> seats = tickets.stream()
                .map(Ticket::getSeat)
                .collect(Collectors.toList());

        return BookingResponse.builder()
                .invoiceId(invoice.getId())
                .username(invoice.getUsername())
                .totalAmount(invoice.getTotalAmount())
                .paymentStatus(invoice.getPaymentStatus())
                .vnpayTransactionId(invoice.getVnpayTransactionId())
                .bookingTime(invoice.getCreatedAt() != null ? invoice.getCreatedAt() : LocalDateTime.now())
                .paidAt(invoice.getPaidAt())
                .showtime(showtimeMapper.toShowtimeResponse(showtime))
                .seats(seats.stream().map(seatMapper::toSeatResponse).collect(Collectors.toList()))
                .tickets(tickets.stream().map(ticketMapper::toTicketResponse).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<BookingResponse> getMyBookings() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Get all invoices for the user
        List<Invoice> invoices = invoiceRepository.findByUsername(username);

        if (invoices.isEmpty()) {
            return new ArrayList<>(); // No bookings found
        }

        List<BookingResponse> bookings = new ArrayList<>();

        for (Invoice invoice : invoices) {
            // Get tickets for this invoice
            List<Ticket> tickets = ticketRepository.findByInvoiceId(invoice.getId());

            if (tickets.isEmpty()) {
                continue; // Skip if no tickets found
            }

            // Get showtime from first ticket (all tickets should have same showtime)
            Showtime showtime = tickets.get(0).getShowtime();

            // Get seats
            List<Seat> seats = tickets.stream()
                    .map(Ticket::getSeat)
                    .collect(Collectors.toList());

            BookingResponse booking = BookingResponse.builder()
                    .invoiceId(invoice.getId())
                    .username(invoice.getUsername())
                    .totalAmount(invoice.getTotalAmount())
                    .paymentStatus(invoice.getPaymentStatus())
                    .vnpayTransactionId(invoice.getVnpayTransactionId())
                    .bookingTime(invoice.getCreatedAt() != null ? invoice.getCreatedAt() : LocalDateTime.now())
                    .paidAt(invoice.getPaidAt())
                    .showtime(showtimeMapper.toShowtimeResponse(showtime))
                    .seats(seats.stream().map(seatMapper::toSeatResponse).collect(Collectors.toList()))
                    .tickets(tickets.stream().map(ticketMapper::toTicketResponse).collect(Collectors.toList()))
                    .build();

            bookings.add(booking);
        }

        return bookings;
    }
}
