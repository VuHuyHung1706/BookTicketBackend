package com.web.backend.controller;

import com.web.backend.dto.request.movie.MovieRequest;
import com.web.backend.dto.response.ApiResponse;
import com.web.backend.dto.response.movie.MovieResponse;
import com.web.backend.service.movie.MovieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@SecurityRequirement(name = "Bearer")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ApiResponse<Page<MovieResponse>> getAllMovies(Pageable pageable) {
        return ApiResponse.<Page<MovieResponse>>builder()
                .result(movieService.getAllMovies(pageable))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<MovieResponse> getMovieById(@PathVariable Integer id) {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.getMovieById(id))
                .build();
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ApiResponse<MovieResponse> createMovie(@Valid @RequestBody MovieRequest request) {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.createMovie(request))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ApiResponse<MovieResponse> updateMovie(@PathVariable Integer id, @Valid @RequestBody MovieRequest request) {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.updateMovie(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ApiResponse<String> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ApiResponse.<String>builder()
                .result("Movie deleted successfully")
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<MovieResponse>> searchMovies(@RequestParam String title) {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.searchMovies(title))
                .build();
    }

    @GetMapping("/room/{roomId}")
    public ApiResponse<List<MovieResponse>> getMoviesByRoomId(@PathVariable Integer roomId) {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMoviesByRoomId(roomId))
                .build();
    }

    @GetMapping("/now-showing")
    public ApiResponse<List<MovieResponse>> getNowShowingMovies() {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getNowShowingMovies())
                .build();
    }

    @GetMapping("/upcoming")
    public ApiResponse<List<MovieResponse>> getUpcomingMovies() {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getUpcomingMovies())
                .build();
    }
}
