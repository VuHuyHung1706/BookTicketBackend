package com.web.backend.dto.response.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CinemaReportResponse {
    private Integer cinemaId;
    private String cinemaName;
    private long totalRevenue;
    private long totalTicketsSold;
}
