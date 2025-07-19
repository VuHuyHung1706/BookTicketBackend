package com.web.backend.dto.response.showtime;

import com.web.backend.dto.response.movie.MovieResponse;
import com.web.backend.dto.response.room.RoomResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowtimeResponse {
    private Integer id;
    private Integer movieId;
    private Integer roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer ticketPrice;
    private MovieResponse movie;
    private RoomResponse room;
}
