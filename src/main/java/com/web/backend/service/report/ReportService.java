package com.web.backend.service.report;

import com.web.backend.dto.response.report.CinemaReportResponse;
import com.web.backend.dto.response.report.MovieReportResponse;
import com.web.backend.dto.response.report.RevenueReportResponse;

import java.time.LocalDate;

public interface ReportService {
    RevenueReportResponse getTotalReport(LocalDate date, LocalDate startDate, LocalDate endDate);
    MovieReportResponse getSpecificMovieReport(Integer movieId, LocalDate date, LocalDate startDate, LocalDate endDate);
    CinemaReportResponse getSpecificCinemaReport(Integer cinemaId, LocalDate date, LocalDate startDate, LocalDate endDate);
}
