package com.web.backend.dto.response.booking;

import com.web.backend.dto.response.showtime.ShowtimeResponse;
import com.web.backend.dto.response.seat.SeatResponse;
import com.web.backend.dto.response.ticket.TicketResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private Integer invoiceId;
    private String username;
    private Integer totalAmount;
    private LocalDateTime bookingTime;
    private ShowtimeResponse showtime;
    private List<SeatResponse> seats;
    private List<TicketResponse> tickets;
}
