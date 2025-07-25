package com.web.backend.dto.request.seat;

import com.web.backend.constant.SeatType;
import jakarta.persistence.Column;
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

    @NotBlank(message = "SEAT_NAME_NOT_BLANK")
    private String name;

    private Integer seatRow;
    private Integer seatColumn;

    @NotNull(message = "ROOM_ID_NOT_NULL")
    private Integer roomId;

    private SeatType seatType;
}
