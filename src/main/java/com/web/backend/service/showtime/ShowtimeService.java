package com.web.backend.service.showtime;

import com.web.backend.dto.request.showtime.ShowtimeRequest;
import com.web.backend.dto.response.seat.SeatResponse;
import com.web.backend.dto.response.showtime.ShowtimeResponse;

import java.util.List;

public interface ShowtimeService {
    List<ShowtimeResponse> getAllShowtimes();
    ShowtimeResponse getShowtimeById(Integer id);
    ShowtimeResponse createShowtime(ShowtimeRequest request);
    ShowtimeResponse updateShowtime(Integer id, ShowtimeRequest request);
    void deleteShowtime(Integer id);
    List<SeatResponse> getAvailableSeats(Integer showtimeId);
    List<ShowtimeResponse> getShowtimesByMovieId(Integer movieId);
    List<ShowtimeResponse> getShowtimesByCinemaId(Integer cinemaId);
    List<ShowtimeResponse> getShowtimesByMovieAndCinema(Integer movieId, Integer cinemaId);
}
