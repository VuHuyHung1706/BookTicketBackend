package com.web.backend.dto.response.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeReportResponse {
    private int year;
    private Integer period; // quarter or month
    private long totalRevenue;
    private long totalTicketsSold;
}
