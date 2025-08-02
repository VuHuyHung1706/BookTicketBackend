package com.web.backend.service.report;

import com.web.backend.dto.response.report.CinemaReportResponse;
import com.web.backend.dto.response.report.MovieReportResponse;
import com.web.backend.dto.response.report.TimeReportResponse;
import com.web.backend.dto.response.report.DailyReportResponse;
import com.web.backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
        @Autowired  TicketRepository ticketRepository;
        @Override
        public List<CinemaReportResponse> getRevenueByCinema(LocalDateTime from, LocalDateTime to) {
            return ticketRepository.getRevenueByCinema(from, to).stream()
                    .map(row -> new CinemaReportResponse(
                            ((Number) row[0]).intValue(),
                            (String) row[1],
                            ((BigDecimal) row[2]).longValue(),
                            ((Number) row[3]).longValue()
                    )).collect(Collectors.toList());
        }

        @Override
        public List<MovieReportResponse> getRevenueByMovie(LocalDateTime from, LocalDateTime to) {
            return ticketRepository.getRevenueByMovie(from, to).stream()
                    .map(row -> new MovieReportResponse(
                            ((Number) row[0]).intValue(),
                            (String) row[1],
                            ((BigDecimal) row[2]).longValue(),
                            ((Number) row[3]).longValue()
                    )).collect(Collectors.toList());
        }

        @Override
        public List<DailyReportResponse> getRevenueByDay(LocalDateTime from, LocalDateTime to) {
            return ticketRepository.getRevenueByDay(from, to).stream()
                    .map(row -> new DailyReportResponse(
                            ((Timestamp) row[0]).toLocalDateTime().toLocalDate(),
                            ((BigDecimal) row[1]).longValue(),
                            ((Number) row[2]).longValue()
                    )).collect(Collectors.toList());
        }

        @Override
        public List<TimeReportResponse> getRevenueByMonth(LocalDateTime from, LocalDateTime to) {
            return ticketRepository.getRevenueByMonth(from, to).stream()
                    .map(row -> new TimeReportResponse(
                            ((Number) row[0]).intValue(),
                            ((Number) row[1]).intValue(),
                            ((BigDecimal) row[2]).longValue(),
                            ((Number) row[3]).longValue()
                    )).collect(Collectors.toList());
        }

        @Override
        public List<TimeReportResponse> getRevenueByQuarter(LocalDateTime from, LocalDateTime to) {
            return ticketRepository.getRevenueByQuarter(from, to).stream()
                    .map(row -> new TimeReportResponse(
                            ((Number) row[0]).intValue(),
                            ((Number) row[1]).intValue(),
                            ((BigDecimal) row[2]).longValue(),
                            ((Number) row[3]).longValue()
                    )).collect(Collectors.toList());
        }

        @Override
        public List<TimeReportResponse> getRevenueByYear(LocalDateTime from, LocalDateTime to) {
            return ticketRepository.getRevenueByYear(from, to).stream()
                    .map(row -> new TimeReportResponse(
                            ((Number) row[0]).intValue(),
                            null, // no month/quarter
                            ((BigDecimal) row[1]).longValue(),
                            ((Number) row[2]).longValue()
                    )).collect(Collectors.toList());
        }
}
