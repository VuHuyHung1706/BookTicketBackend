package com.web.backend.controller;

import com.web.backend.dto.response.report.CinemaReportResponse;
import com.web.backend.dto.response.report.DailyReportResponse;
import com.web.backend.dto.response.report.MovieReportResponse;
import com.web.backend.dto.response.report.TimeReportResponse;
import com.web.backend.service.report.ReportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reports")
@SecurityRequirement(name = "Bearer")
@PreAuthorize("hasRole('MANAGER')")
public class ReportController {
    @Autowired
    private ReportService ticketReportService;

    @GetMapping("/cinema")
    public List<CinemaReportResponse> getRevenueByCinema(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ticketReportService.getRevenueByCinema(from, to);
    }

    @GetMapping("/movie")
    public List<MovieReportResponse> getRevenueByMovie(
            @RequestParam   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ticketReportService.getRevenueByMovie(from, to);
    }

    @GetMapping("/daily")
    public List<DailyReportResponse> getRevenueByDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ticketReportService.getRevenueByDay(from, to);
    }

    @GetMapping("/monthly")
    public List<TimeReportResponse> getRevenueByMonth(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ticketReportService.getRevenueByMonth(from, to);
    }

    @GetMapping("/quarterly")
    public List<TimeReportResponse> getRevenueByQuarter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ticketReportService.getRevenueByQuarter(from, to);
    }

    @GetMapping("/yearly")
    public List<TimeReportResponse> getRevenueByYear(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ticketReportService.getRevenueByYear(from, to);
    }
}
