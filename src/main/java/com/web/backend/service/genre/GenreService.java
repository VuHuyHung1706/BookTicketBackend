package com.web.backend.service.genre;

import com.web.backend.dto.response.genre.GenreResponse;

import java.util.List;

public interface GenreService {
    List<GenreResponse> getAllGenres();
    GenreResponse getGenreById(Integer id);
}
