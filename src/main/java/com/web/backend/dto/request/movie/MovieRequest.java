package com.web.backend.dto.request.movie;

import com.web.backend.constant.MovieStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class MovieRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;

    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be positive")
    private Integer duration;

    private String language;
    private String poster;
    private String trailer;

    @NotNull(message = "Release date is required")
    private LocalDate releaseDate;

    private LocalDate endDate;
    private MovieStatus status;
    private Set<Integer> genreIds;
    private Set<Integer> actorIds;
}