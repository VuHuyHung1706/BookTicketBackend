package com.web.backend.service.movie;

import com.web.backend.dto.request.movie.MovieRequest;
import com.web.backend.dto.response.movie.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    List<MovieResponse> getAllMovie();
    Page<MovieResponse> getAllMovies(Pageable pageable);
    MovieResponse getMovieById(Integer id);
    MovieResponse createMovie(MovieRequest request);
    MovieResponse updateMovie(Integer id, MovieRequest request);
    void deleteMovie(Integer id);
    List<MovieResponse> searchMovies(String title);
    List<MovieResponse> searchMovieByTitleCinemaId(String title, Integer cinemaId);
    List<MovieResponse> getMoviesByRoomId(Integer roomId);
    List<MovieResponse> getNowShowingMovies();
    List<MovieResponse> getUpcomingMovies();
    List<MovieResponse> getMoviesByCinemaId(Integer cinemaId);
}
