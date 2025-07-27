package com.web.backend.mapper;

import com.web.backend.dto.request.movie.MovieRequest;
import com.web.backend.dto.response.actor.ActorResponse;
import com.web.backend.dto.response.genre.GenreResponse;
import com.web.backend.dto.response.movie.MovieResponse;
import com.web.backend.entity.Actor;
import com.web.backend.entity.Genre;
import com.web.backend.entity.Movie;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T03:48:43+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Amazon.com Inc.)"
)
@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public Movie toMovie(MovieRequest request) {
        if ( request == null ) {
            return null;
        }

        Movie.MovieBuilder movie = Movie.builder();

        movie.title( request.getTitle() );
        movie.description( request.getDescription() );
        movie.duration( request.getDuration() );
        movie.language( request.getLanguage() );
        movie.poster( request.getPoster() );
        movie.trailer( request.getTrailer() );
        movie.releaseDate( request.getReleaseDate() );
        movie.endDate( request.getEndDate() );
        movie.status( request.getStatus() );

        return movie.build();
    }

    @Override
    public MovieResponse toMovieResponse(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieResponse.MovieResponseBuilder movieResponse = MovieResponse.builder();

        movieResponse.id( movie.getId() );
        movieResponse.title( movie.getTitle() );
        movieResponse.description( movie.getDescription() );
        movieResponse.duration( movie.getDuration() );
        movieResponse.language( movie.getLanguage() );
        movieResponse.poster( movie.getPoster() );
        movieResponse.trailer( movie.getTrailer() );
        movieResponse.releaseDate( movie.getReleaseDate() );
        movieResponse.endDate( movie.getEndDate() );
        movieResponse.status( movie.getStatus() );
        movieResponse.genres( genreSetToGenreResponseSet( movie.getGenres() ) );
        movieResponse.actors( actorSetToActorResponseSet( movie.getActors() ) );

        return movieResponse.build();
    }

    @Override
    public void updateMovie(Movie movie, MovieRequest request) {
        if ( request == null ) {
            return;
        }

        movie.setTitle( request.getTitle() );
        movie.setDescription( request.getDescription() );
        movie.setDuration( request.getDuration() );
        movie.setLanguage( request.getLanguage() );
        movie.setPoster( request.getPoster() );
        movie.setTrailer( request.getTrailer() );
        movie.setReleaseDate( request.getReleaseDate() );
        movie.setEndDate( request.getEndDate() );
        movie.setStatus( request.getStatus() );
    }

    protected GenreResponse genreToGenreResponse(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreResponse.GenreResponseBuilder genreResponse = GenreResponse.builder();

        genreResponse.id( genre.getId() );
        genreResponse.name( genre.getName() );

        return genreResponse.build();
    }

    protected Set<GenreResponse> genreSetToGenreResponseSet(Set<Genre> set) {
        if ( set == null ) {
            return null;
        }

        Set<GenreResponse> set1 = new LinkedHashSet<GenreResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Genre genre : set ) {
            set1.add( genreToGenreResponse( genre ) );
        }

        return set1;
    }

    protected ActorResponse actorToActorResponse(Actor actor) {
        if ( actor == null ) {
            return null;
        }

        ActorResponse.ActorResponseBuilder actorResponse = ActorResponse.builder();

        actorResponse.id( actor.getId() );
        actorResponse.firstName( actor.getFirstName() );
        actorResponse.lastName( actor.getLastName() );
        actorResponse.gender( actor.getGender() );
        actorResponse.dateOfBirth( actor.getDateOfBirth() );
        actorResponse.nationality( actor.getNationality() );

        return actorResponse.build();
    }

    protected Set<ActorResponse> actorSetToActorResponseSet(Set<Actor> set) {
        if ( set == null ) {
            return null;
        }

        Set<ActorResponse> set1 = new LinkedHashSet<ActorResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Actor actor : set ) {
            set1.add( actorToActorResponse( actor ) );
        }

        return set1;
    }
}
