package com.web.backend.dto.response.showtime;

import com.web.backend.dto.response.cinema.CinemaResponse;
import com.web.backend.dto.response.movie.MovieResponse;
import com.web.backend.dto.response.room.RoomResponse;
import com.web.backend.dto.response.ticket.TicketResponse;
import com.web.backend.entity.Cinema;
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
public class ShowtimeResponse {
    private Integer id;
    private Integer movieId;
    private Integer roomId;
    private String roomName;
    private String cinemaName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer ticketPrice;
    private MovieResponse movie;
    private RoomResponse room;
}
