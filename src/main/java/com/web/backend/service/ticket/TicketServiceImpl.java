package com.web.backend.service.ticket;

import com.web.backend.dto.request.ticket.ScanTicketRequest;
import com.web.backend.dto.response.ticket.ScanTicketResponse;
import com.web.backend.dto.response.ticket.TicketDetailResponse;
import com.web.backend.entity.Ticket;
import com.web.backend.exception.AppException;
import com.web.backend.exception.ErrorCode;
import com.web.backend.mapper.TicketMapper;
import com.web.backend.repository.TicketRepository;
import com.web.backend.repository.InvoiceRepository;
import com.web.backend.service.qr.QRCodeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private QRCodeService qrCodeService;

    @Override
    public List<TicketDetailResponse> getMyTickets() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Get all invoices for the user and then get tickets
        return invoiceRepository.findByUsername(username)
                .stream()
                .flatMap(invoice -> ticketRepository.findByInvoiceId(invoice.getId()).stream())
                .map(ticketMapper::toTicketDetailResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDetailResponse getTicketById(Integer id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_EXISTED));

        // Check if the ticket belongs to the current user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!ticket.getInvoice().getUsername().equals(username)) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        return ticketMapper.toTicketDetailResponse(ticket);
    }

    @Override
    public byte[] generateTicketQRCode(Integer ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_EXISTED));

        // Check if the ticket belongs to the current user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!ticket.getInvoice().getUsername().equals(username)) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        // Generate QR code if not exists
        if (ticket.getQrCode() == null) {
            String qrCode = qrCodeService.generateQRCode(ticketId);
            ticket.setQrCode(qrCode);
            ticketRepository.save(ticket);
        }

        return qrCodeService.generateQRCodeImage(ticket.getQrCode());
    }

    @Override
    public ScanTicketResponse scanTicket(ScanTicketRequest request) {
        // Validate QR code format
        if (!qrCodeService.validateQRCode(request.getQrCode())) {
            return ScanTicketResponse.builder()
                    .success(false)
                    .message("Invalid QR code format")
                    .build();
        }

        // Extract ticket ID from QR code
        String[] parts = request.getQrCode().split("\\|");
        Integer ticketId = Integer.parseInt(parts[0]);

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElse(null);

        if (ticket == null) {
            return ScanTicketResponse.builder()
                    .success(false)
                    .message("Ticket not found")
                    .build();
        }

        if (!ticket.getStatus()) {
            return ScanTicketResponse.builder()
                    .success(false)
                    .message("Ticket is not active")
                    .build();
        }

        if (ticket.getIsScanned()) {
            return ScanTicketResponse.builder()
                    .success(false)
                    .message("Ticket already scanned at " + ticket.getScannedAt())
                    .ticket(ticketMapper.toTicketDetailResponse(ticket))
                    .build();
        }

        // Check if showtime has started (optional validation)
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(ticket.getShowtime().getStartTime().minusMinutes(30))) {
            return ScanTicketResponse.builder()
                    .success(false)
                    .message("Too early to scan ticket. Showtime starts at " + ticket.getShowtime().getStartTime())
                    .ticket(ticketMapper.toTicketDetailResponse(ticket))
                    .build();
        }

        // Mark ticket as scanned
        ticket.setIsScanned(true);
        ticket.setScannedAt(now);
        ticketRepository.save(ticket);

        return ScanTicketResponse.builder()
                .success(true)
                .message("Ticket scanned successfully")
                .ticket(ticketMapper.toTicketDetailResponse(ticket))
                .scannedAt(now)
                .build();
    }

    @Override
    public List<TicketDetailResponse> getTicketsByShowtime(Integer showtimeId) {
        return ticketRepository.findByShowtimeIdAndStatus(showtimeId, true)
                .stream()
                .map(ticketMapper::toTicketDetailResponse)
                .collect(Collectors.toList());
    }
}
