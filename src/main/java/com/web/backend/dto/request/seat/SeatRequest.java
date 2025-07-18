package com.web.backend.dto.request.seat;

import com.web.backend.constant.SeatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatRequest {

    @NotBlank(message = "Seat name cannot be blank")
    private String name;

    @NotNull(message = "Room ID is required")
    private Integer roomId;

    private SeatType seatType;
}
