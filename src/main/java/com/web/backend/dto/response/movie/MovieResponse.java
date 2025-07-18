package com.web.backend.dto.response.movie;

import com.web.backend.constant.MovieStatus;
import com.web.backend.dto.response.actor.ActorResponse;
import com.web.backend.dto.response.genre.GenreResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponse {
    private Integer id;
    private String title;
    private String description;
    private Integer duration;
    private String language;
    private String poster;
    private String trailer;
    private LocalDate releaseDate;
    private LocalDate endDate;
    private MovieStatus status;
    private Set<GenreResponse> genres;
    private Set<ActorResponse> actors;
}
