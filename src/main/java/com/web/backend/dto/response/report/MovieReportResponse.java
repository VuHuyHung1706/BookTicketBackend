package com.web.backend.dto.response.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieReportResponse {
    private Integer movieId;
    private String movieTitle;
    private long totalRevenue;
    private long totalTicketsSold;
}
