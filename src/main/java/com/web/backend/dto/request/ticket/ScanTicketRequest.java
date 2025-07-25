package com.web.backend.dto.request.ticket;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScanTicketRequest {
    @NotBlank(message = "QR code cannot be blank")
    private String qrCode;
}
