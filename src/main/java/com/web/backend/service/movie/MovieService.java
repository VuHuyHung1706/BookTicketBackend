package com.web.backend.service.movie;

import com.web.backend.dto.request.movie.MovieRequest;
import com.web.backend.dto.response.movie.MovieResponse;

import java.util.List;

public interface MovieService {
    List<MovieResponse> getAllMovies();
    MovieResponse getMovieById(Integer id);
    MovieResponse createMovie(MovieRequest request);
    MovieResponse updateMovie(Integer id, MovieRequest request);
    void deleteMovie(Integer id);
    List<MovieResponse> searchMovies(String title);
    List<MovieResponse> getMoviesByRoomId(Integer roomId);
}
