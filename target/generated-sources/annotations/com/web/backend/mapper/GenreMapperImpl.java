package com.web.backend.mapper;

import com.web.backend.dto.response.genre.GenreResponse;
import com.web.backend.entity.Genre;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-13T17:06:09+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class GenreMapperImpl implements GenreMapper {

    @Override
    public GenreResponse toGenreResponse(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreResponse.GenreResponseBuilder genreResponse = GenreResponse.builder();

        genreResponse.id( genre.getId() );
        genreResponse.name( genre.getName() );

        return genreResponse.build();
    }
}
