package com.web.backend.service.report;

import com.web.backend.dto.response.report.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    List<CinemaReportResponse> getRevenueByCinema(LocalDateTime from, LocalDateTime to);
    List<MovieReportResponse> getRevenueByMovie(LocalDateTime from, LocalDateTime to);
    List<DailyReportResponse> getRevenueByDay(LocalDateTime from, LocalDateTime to);
    List<TimeReportResponse> getRevenueByMonth(LocalDateTime from, LocalDateTime to);
    List<TimeReportResponse> getRevenueByQuarter(LocalDateTime from, LocalDateTime to);
    List<TimeReportResponse> getRevenueByYear(LocalDateTime from, LocalDateTime to);
}
