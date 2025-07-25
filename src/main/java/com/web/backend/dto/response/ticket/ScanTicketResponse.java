package com.web.backend.dto.response.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScanTicketResponse {
    private Boolean success;
    private String message;
    private TicketDetailResponse ticket;
    private LocalDateTime scannedAt;
}
