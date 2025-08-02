package com.web.backend.service.movie;

import com.web.backend.dto.request.movie.MovieRequest;
import com.web.backend.dto.response.movie.MovieResponse;
import com.web.backend.entity.Movie;
import com.web.backend.entity.Genre;
import com.web.backend.entity.Actor;
import com.web.backend.entity.Showtime;
import com.web.backend.exception.AppException;
import com.web.backend.exception.ErrorCode;
import com.web.backend.mapper.MovieMapper;
import com.web.backend.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public Page<MovieResponse> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .map(movieMapper::toMovieResponse);
    }

    @Override
    public MovieResponse getMovieById(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_EXISTED));
        return movieMapper.toMovieResponse(movie);
    }

    @Override
    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = movieMapper.toMovie(request);

        // Set genres
        if (request.getGenreIds() != null && !request.getGenreIds().isEmpty()) {
            Set<Genre> genres = new HashSet<>();
            for (Integer genreId : request.getGenreIds()) {
                Genre genre = genreRepository.findById(genreId)
                        .orElseThrow(() -> new AppException(ErrorCode.GENRE_NOT_EXISTED));
                genres.add(genre);
            }
            movie.setGenres(genres);
        }

        // Set actors
        if (request.getActorIds() != null && !request.getActorIds().isEmpty()) {
            Set<Actor> actors = new HashSet<>();
            for (Integer actorId : request.getActorIds()) {
                Actor actor = actorRepository.findById(actorId)
                        .orElseThrow(() -> new AppException(ErrorCode.ACTOR_NOT_EXISTED));
                actors.add(actor);
            }
            movie.setActors(actors);
        }

        movie = movieRepository.save(movie);
        return movieMapper.toMovieResponse(movie);
    }

    @Override
    public MovieResponse updateMovie(Integer id, MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_EXISTED));

        movieMapper.updateMovie(movie, request);

        // Update genres
        if (request.getGenreIds() != null) {
            Set<Genre> genres = new HashSet<>();
            for (Integer genreId : request.getGenreIds()) {
                Genre genre = genreRepository.findById(genreId)
                        .orElseThrow(() -> new AppException(ErrorCode.GENRE_NOT_EXISTED));
                genres.add(genre);
            }
            movie.setGenres(genres);
        }

        // Update actors
        if (request.getActorIds() != null) {
            Set<Actor> actors = new HashSet<>();
            for (Integer actorId : request.getActorIds()) {
                Actor actor = actorRepository.findById(actorId)
                        .orElseThrow(() -> new AppException(ErrorCode.ACTOR_NOT_EXISTED));
                actors.add(actor);
            }
            movie.setActors(actors);
        }

        movie = movieRepository.save(movie);
        return movieMapper.toMovieResponse(movie);
    }

    @Override
    public void deleteMovie(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new AppException(ErrorCode.MOVIE_NOT_EXISTED);
        }
        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieResponse> searchMovies(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(movieMapper::toMovieResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieResponse> getMoviesByRoomId(Integer roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new AppException(ErrorCode.ROOM_NOT_EXISTED);
        }

        List<Movie> movies = showtimeRepository.findByRoomId(roomId)
                .stream()
                .map(Showtime::getMovie)
                .toList();

        return movies.stream()
                .map(movieMapper::toMovieResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieResponse> getNowShowingMovies() {
        LocalDateTime now = LocalDateTime.now();

        List<Movie> nowShowingMovies = showtimeRepository.findAll()
                .stream()
                .filter(showtime -> (showtime.getStartTime().isBefore(now) || showtime.getStartTime().isEqual(now))
                        && (showtime.getEndTime().isAfter(now) || showtime.getEndTime().isEqual(now)))
                .map(Showtime::getMovie)
                .distinct()
                .collect(Collectors.toList());

        return nowShowingMovies.stream()
                .map(movieMapper::toMovieResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieResponse> getUpcomingMovies() {
        LocalDateTime now = LocalDateTime.now();

        List<Movie> upcomingMovies = showtimeRepository.findAll()
                .stream()
                .filter(showtime -> showtime.getStartTime().isAfter(now))
                .map(Showtime::getMovie)
                .distinct()
                .collect(Collectors.toList());

        return upcomingMovies.stream()
                .map(movieMapper::toMovieResponse)
                .collect(Collectors.toList());
    }
}
