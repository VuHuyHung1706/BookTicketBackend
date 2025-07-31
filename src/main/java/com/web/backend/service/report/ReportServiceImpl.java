package com.web.backend.service.report;

import com.web.backend.constant.PaymentStatus;
import com.web.backend.dto.response.report.CinemaReportResponse;
import com.web.backend.dto.response.report.MovieReportResponse;
import com.web.backend.dto.response.report.RevenueReportResponse;
import com.web.backend.entity.Cinema;
import com.web.backend.entity.Invoice;
import com.web.backend.entity.Movie;
import com.web.backend.entity.Ticket;
import com.web.backend.exception.AppException;
import com.web.backend.exception.ErrorCode;
import com.web.backend.repository.CinemaRepository;
import com.web.backend.repository.InvoiceRepository;
import com.web.backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public RevenueReportResponse getTotalReport(LocalDate date, LocalDate startDate, LocalDate endDate) {
        List<Invoice> paidInvoices = getPaidInvoicesInDateRange(date, startDate, endDate);

        int totalRevenue = 0;
        int totalTicketsSold = 0;

        for (Invoice invoice : paidInvoices) {
            totalRevenue += invoice.getTotalAmount();
            totalTicketsSold += invoice.getTickets().size();
        }

        return RevenueReportResponse.builder()
                .totalRevenue(totalRevenue)
                .totalTicketsSold(totalTicketsSold)
                .build();
    }

    @Override
    public MovieReportResponse getSpecificMovieReport(Integer movieId, LocalDate date, LocalDate startDate, LocalDate endDate) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_EXISTED));

        List<Invoice> paidInvoices = getPaidInvoicesInDateRange(date, startDate, endDate);

        int totalRevenue = 0;
        int totalTicketsSold = 0;

        for (Invoice invoice : paidInvoices) {
            for (Ticket ticket : invoice.getTickets()) {
                if (ticket.getShowtime().getMovie().getId().equals(movieId)) {
                    totalRevenue += ticket.getPrice();
                    totalTicketsSold++;
                }
            }
        }

        return MovieReportResponse.builder()
                .movieId(movie.getId())
                .movieTitle(movie.getTitle())
                .totalRevenue(totalRevenue)
                .totalTicketsSold(totalTicketsSold)
                .build();
    }

    @Override
    public CinemaReportResponse getSpecificCinemaReport(Integer cinemaId, LocalDate date, LocalDate startDate, LocalDate endDate) {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_NOT_EXISTED));

        List<Invoice> paidInvoices = getPaidInvoicesInDateRange(date, startDate, endDate);

        int totalRevenue = 0;
        int totalTicketsSold = 0;

        for (Invoice invoice : paidInvoices) {
            for (Ticket ticket : invoice.getTickets()) {
                if (ticket.getShowtime().getRoom().getCinema().getId().equals(cinemaId)) {
                    totalRevenue += ticket.getPrice();
                    totalTicketsSold++;
                }
            }
        }

        return CinemaReportResponse.builder()
                .cinemaId(cinema.getId())
                .cinemaName(cinema.getName())
                .totalRevenue(totalRevenue)
                .totalTicketsSold(totalTicketsSold)
                .build();
    }

    private List<Invoice> getPaidInvoicesInDateRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        if (date != null) {
            startDateTime = date.atStartOfDay();
            endDateTime = date.atTime(LocalTime.MAX);
        } else if (startDate != null && endDate != null) {
            startDateTime = startDate.atStartOfDay();
            endDateTime = endDate.atTime(LocalTime.MAX);
        } else {
            return invoiceRepository.findAll().stream()
                    .filter(invoice -> invoice.getPaymentStatus() == PaymentStatus.PAID)
                    .toList();
        }

        return invoiceRepository.findByPaymentStatusAndPaidAtBetween(PaymentStatus.PAID, startDateTime, endDateTime);
    }
}
