package com.web.backend.dto.response.ticket;

import com.web.backend.dto.response.showtime.ShowtimeResponse;
import com.web.backend.dto.response.seat.SeatResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDetailResponse {
    private Integer id;
    private Integer showtimeId;
    private Integer seatId;
    private Integer invoiceId;
    private Integer price;
    private Boolean status;
    private String qrCode;
    private LocalDateTime createdAt;
    private LocalDateTime scannedAt;
    private Boolean isScanned;
    private String seatName;
    private ShowtimeResponse showtime;
    private SeatResponse seat;
}
