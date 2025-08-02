package com.web.backend.dto.response.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DailyReportResponse {
    private LocalDate date;
    private long totalRevenue;
    private long totalTicketsSold;
}