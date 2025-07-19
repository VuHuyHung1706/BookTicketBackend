package com.web.backend.dto.response.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {
    private Integer id;
    private Integer showtimeId;
    private Integer seatId;
    private Integer invoiceId;
    private Integer price;
    private Boolean status;
    private String seatName;
}
