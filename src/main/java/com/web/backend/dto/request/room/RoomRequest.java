package com.web.backend.dto.request.room;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRequest {

    @NotBlank(message = "ROOM_NAME_NOT_BLANK")
    private String name;

    @Min(value = 1, message = "TOTAL_SEATS_MIN")
    private Integer totalSeats;

    @NotNull(message = "CINEMA_ID_NOT_NULL")
    private Integer cinemaId;
}
