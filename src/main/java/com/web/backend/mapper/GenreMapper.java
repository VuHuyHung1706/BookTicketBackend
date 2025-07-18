package com.web.backend.mapper;

import com.web.backend.dto.response.genre.GenreResponse;
import com.web.backend.entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreResponse toGenreResponse(Genre genre);
}
