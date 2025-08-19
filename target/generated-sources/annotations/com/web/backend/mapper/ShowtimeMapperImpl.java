package com.web.backend.mapper;

import com.web.backend.dto.request.showtime.ShowtimeRequest;
import com.web.backend.dto.response.actor.ActorResponse;
import com.web.backend.dto.response.genre.GenreResponse;
import com.web.backend.dto.response.movie.MovieResponse;
import com.web.backend.dto.response.room.RoomResponse;
import com.web.backend.dto.response.seat.SeatResponse;
import com.web.backend.dto.response.showtime.ShowtimeResponse;
import com.web.backend.entity.Actor;
import com.web.backend.entity.Cinema;
import com.web.backend.entity.Genre;
import com.web.backend.entity.Movie;
import com.web.backend.entity.Room;
import com.web.backend.entity.Seat;
import com.web.backend.entity.Showtime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-13T17:06:09+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class ShowtimeMapperImpl implements ShowtimeMapper {

    @Override
    public Showtime toShowtime(ShowtimeRequest request) {
        if ( request == null ) {
            return null;
        }

        Showtime.ShowtimeBuilder showtime = Showtime.builder();

        showtime.movieId( request.getMovieId() );
        showtime.roomId( request.getRoomId() );
        showtime.startTime( request.getStartTime() );
        showtime.ticketPrice( request.getTicketPrice() );

        return showtime.build();
    }

    @Override
    public ShowtimeResponse toShowtimeResponse(Showtime showtime) {
        if ( showtime == null ) {
            return null;
        }

        ShowtimeResponse.ShowtimeResponseBuilder showtimeResponse = ShowtimeResponse.builder();

        showtimeResponse.cinemaName( showtimeRoomCinemaName( showtime ) );
        showtimeResponse.roomName( showtimeRoomName( showtime ) );
        showtimeResponse.id( showtime.getId() );
        showtimeResponse.movieId( showtime.getMovieId() );
        showtimeResponse.roomId( showtime.getRoomId() );
        showtimeResponse.startTime( showtime.getStartTime() );
        showtimeResponse.endTime( showtime.getEndTime() );
        showtimeResponse.ticketPrice( showtime.getTicketPrice() );
        showtimeResponse.movie( movieToMovieResponse( showtime.getMovie() ) );
        showtimeResponse.room( roomToRoomResponse( showtime.getRoom() ) );

        return showtimeResponse.build();
    }

    @Override
    public void updateShowtime(Showtime showtime, ShowtimeRequest request) {
        if ( request == null ) {
            return;
        }

        showtime.setMovieId( request.getMovieId() );
        showtime.setRoomId( request.getRoomId() );
        showtime.setStartTime( request.getStartTime() );
        showtime.setTicketPrice( request.getTicketPrice() );
    }

    private String showtimeRoomCinemaName(Showtime showtime) {
        Room room = showtime.getRoom();
        if ( room == null ) {
            return null;
        }
        Cinema cinema = room.getCinema();
        if ( cinema == null ) {
            return null;
        }
        return cinema.getName();
    }

    private String showtimeRoomName(Showtime showtime) {
        Room room = showtime.getRoom();
        if ( room == null ) {
            return null;
        }
        return room.getName();
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

    protected MovieResponse movieToMovieResponse(Movie movie) {
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
        movieResponse.genres( genreSetToGenreResponseSet( movie.getGenres() ) );
        movieResponse.actors( actorSetToActorResponseSet( movie.getActors() ) );

        return movieResponse.build();
    }

    protected SeatResponse seatToSeatResponse(Seat seat) {
        if ( seat == null ) {
            return null;
        }

        SeatResponse.SeatResponseBuilder seatResponse = SeatResponse.builder();

        seatResponse.id( seat.getId() );
        seatResponse.name( seat.getName() );
        seatResponse.seatRow( seat.getSeatRow() );
        seatResponse.seatColumn( seat.getSeatColumn() );
        seatResponse.roomId( seat.getRoomId() );

        return seatResponse.build();
    }

    protected List<SeatResponse> seatListToSeatResponseList(List<Seat> list) {
        if ( list == null ) {
            return null;
        }

        List<SeatResponse> list1 = new ArrayList<SeatResponse>( list.size() );
        for ( Seat seat : list ) {
            list1.add( seatToSeatResponse( seat ) );
        }

        return list1;
    }

    protected RoomResponse roomToRoomResponse(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomResponse.RoomResponseBuilder roomResponse = RoomResponse.builder();

        roomResponse.id( room.getId() );
        roomResponse.name( room.getName() );
        roomResponse.totalSeats( room.getTotalSeats() );
        roomResponse.cinemaId( room.getCinemaId() );
        roomResponse.seats( seatListToSeatResponseList( room.getSeats() ) );

        return roomResponse.build();
    }
}
