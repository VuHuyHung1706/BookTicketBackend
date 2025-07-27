package com.web.backend.controller;

import com.web.backend.dto.request.showtime.ShowtimeRequest;
import com.web.backend.dto.response.ApiResponse;
import com.web.backend.dto.response.seat.SeatResponse;
import com.web.backend.dto.response.showtime.ShowtimeResponse;
import com.web.backend.service.showtime.ShowtimeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/showtimes")
@SecurityRequirement(name = "Bearer")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping
    public ApiResponse<List<ShowtimeResponse>> getAllShowtimes() {
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(showtimeService.getAllShowtimes())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ShowtimeResponse> getShowtimeById(@PathVariable Integer id) {
        return ApiResponse.<ShowtimeResponse>builder()
                .result(showtimeService.getShowtimeById(id))
                .build();
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ApiResponse<ShowtimeResponse> createShowtime(@Valid @RequestBody ShowtimeRequest request) {
        return ApiResponse.<ShowtimeResponse>builder()
                .result(showtimeService.createShowtime(request))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ApiResponse<ShowtimeResponse> updateShowtime(@PathVariable Integer id,
                                                        @Valid @RequestBody ShowtimeRequest request) {
        return ApiResponse.<ShowtimeResponse>builder()
                .result(showtimeService.updateShowtime(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ApiResponse<String> deleteShowtime(@PathVariable Integer id) {
        showtimeService.deleteShowtime(id);
        return ApiResponse.<String>builder()
                .result("Showtime deleted successfully")
                .build();
    }

    @GetMapping("/{id}/available-seats")
    public ApiResponse<List<SeatResponse>> getAvailableSeats(@PathVariable Integer id) {
        return ApiResponse.<List<SeatResponse>>builder()
                .result(showtimeService.getAvailableSeats(id))
                .build();
    }

    @GetMapping("/movie/{movieId}")
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByMovieId(@PathVariable Integer movieId) {
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(showtimeService.getShowtimesByMovieId(movieId))
                .build();
    }

    @GetMapping("/cinema/{cinemaId}")
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByCinemaId(@PathVariable Integer cinemaId) {
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(showtimeService.getShowtimesByCinemaId(cinemaId))
                .build();
    }

    @GetMapping("/movie/{movieId}/cinema/{cinemaId}")
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByMovieAndCinema(
            @PathVariable Integer movieId,
            @PathVariable Integer cinemaId) {
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(showtimeService.getShowtimesByMovieAndCinema(movieId, cinemaId))
                .build();
    }

    @GetMapping("/movie/{movieId}/room/{roomId}")
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByMovieAndRoom(
            @PathVariable Integer movieId,
            @PathVariable Integer roomId) {
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(showtimeService.getShowtimesByMovieAndRoom(movieId, roomId))
                .build();
    }
}
