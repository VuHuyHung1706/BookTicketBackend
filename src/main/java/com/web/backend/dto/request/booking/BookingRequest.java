package com.web.backend.dto.request.booking;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {

    @NotNull(message = "Showtime ID is required")
    private Integer showtimeId;

    @NotEmpty(message = "At least one seat must be selected")
    private List<Integer> seatIds;
}
