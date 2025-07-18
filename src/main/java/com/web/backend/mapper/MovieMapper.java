package com.web.backend.mapper;

import com.web.backend.dto.request.movie.MovieRequest;
import com.web.backend.dto.response.movie.MovieResponse;
import com.web.backend.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "actors", ignore = true)
    @Mapping(target = "showtimes", ignore = true)
    Movie toMovie(MovieRequest request);

    MovieResponse toMovieResponse(Movie movie);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "actors", ignore = true)
    @Mapping(target = "showtimes", ignore = true)
    void updateMovie(@MappingTarget Movie movie, MovieRequest request);
}
