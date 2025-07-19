package com.web.backend.dto.request.showtime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowtimeRequest {

    @NotNull(message = "Movie ID is required")
    private Integer movieId;

    @NotNull(message = "Room ID is required")
    private Integer roomId;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    private Integer ticketPrice;
}
