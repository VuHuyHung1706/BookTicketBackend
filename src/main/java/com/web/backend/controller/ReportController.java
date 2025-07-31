package com.web.backend.controller;

import com.web.backend.dto.response.ApiResponse;
import com.web.backend.dto.response.report.CinemaReportResponse;
import com.web.backend.dto.response.report.MovieReportResponse;
import com.web.backend.dto.response.report.RevenueReportResponse;
import com.web.backend.service.report.ReportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reports")
@SecurityRequirement(name = "Bearer")
@PreAuthorize("hasRole('MANAGER')")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/total")
    public ApiResponse<RevenueReportResponse> getTotalReport(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ApiResponse.<RevenueReportResponse>builder()
                .result(reportService.getTotalReport(date, startDate, endDate))
                .build();
    }

    @GetMapping("/movie/{movieId}")
    public ApiResponse<MovieReportResponse> getSpecificMovieReport(
            @PathVariable Integer movieId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ApiResponse.<MovieReportResponse>builder()
                .result(reportService.getSpecificMovieReport(movieId, date, startDate, endDate))
                .build();
    }

    @GetMapping("/cinema/{cinemaId}")
    public ApiResponse<CinemaReportResponse> getSpecificCinemaReport(
            @PathVariable Integer cinemaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ApiResponse.<CinemaReportResponse>builder()
                .result(reportService.getSpecificCinemaReport(cinemaId, date, startDate, endDate))
                .build();
    }
}
