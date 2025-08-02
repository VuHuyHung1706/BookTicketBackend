package com.web.backend.service.showtime;

import com.web.backend.dto.request.showtime.ShowtimeRequest;
import com.web.backend.dto.response.seat.SeatResponse;
import com.web.backend.dto.response.showtime.ShowtimeResponse;
import com.web.backend.dto.response.ticket.TicketResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShowtimeService {
    Page<ShowtimeResponse> getAllShowtimes(Pageable pageable);
    ShowtimeResponse getShowtimeById(Integer id);
    ShowtimeResponse createShowtime(ShowtimeRequest request);
    ShowtimeResponse updateShowtime(Integer id, ShowtimeRequest request);
    void deleteShowtime(Integer id);
    List<SeatResponse> getAvailableSeats(Integer showtimeId);
    List<ShowtimeResponse> getShowtimesByMovieId(Integer movieId);
    List<ShowtimeResponse> getShowtimesByCinemaId(Integer cinemaId);
    List<ShowtimeResponse> getShowtimesByMovieAndCinema(Integer movieId, Integer cinemaId);
    List<ShowtimeResponse> getShowtimesByMovieAndRoom(Integer movieId, Integer roomId);
    List<TicketResponse> getBookedTickets(Integer id);
    List<ShowtimeResponse> getShowtimesShowing();
}
