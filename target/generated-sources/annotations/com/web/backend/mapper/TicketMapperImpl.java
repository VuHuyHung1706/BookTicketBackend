package com.web.backend.mapper;

import com.web.backend.dto.response.CustomerResponse;
import com.web.backend.dto.response.actor.ActorResponse;
import com.web.backend.dto.response.genre.GenreResponse;
import com.web.backend.dto.response.movie.MovieResponse;
import com.web.backend.dto.response.room.RoomResponse;
import com.web.backend.dto.response.seat.SeatResponse;
import com.web.backend.dto.response.showtime.ShowtimeResponse;
import com.web.backend.dto.response.ticket.TicketDetailResponse;
import com.web.backend.dto.response.ticket.TicketResponse;
import com.web.backend.entity.Account;
import com.web.backend.entity.Actor;
import com.web.backend.entity.Customer;
import com.web.backend.entity.Genre;
import com.web.backend.entity.Invoice;
import com.web.backend.entity.Movie;
import com.web.backend.entity.Room;
import com.web.backend.entity.Seat;
import com.web.backend.entity.Showtime;
import com.web.backend.entity.Ticket;
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
public class TicketMapperImpl implements TicketMapper {

    @Override
    public TicketResponse toTicketResponse(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketResponse.TicketResponseBuilder ticketResponse = TicketResponse.builder();

        ticketResponse.seatName( ticketSeatName( ticket ) );
        ticketResponse.customer( customerToCustomerResponse( ticketInvoiceAccountCustomer( ticket ) ) );
        ticketResponse.id( ticket.getId() );
        ticketResponse.showtimeId( ticket.getShowtimeId() );
        ticketResponse.seatId( ticket.getSeatId() );
        ticketResponse.invoiceId( ticket.getInvoiceId() );
        ticketResponse.price( ticket.getPrice() );
        ticketResponse.status( ticket.getStatus() );
        ticketResponse.isScanned( ticket.getIsScanned() );

        return ticketResponse.build();
    }

    @Override
    public TicketDetailResponse toTicketDetailResponse(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketDetailResponse.TicketDetailResponseBuilder ticketDetailResponse = TicketDetailResponse.builder();

        ticketDetailResponse.seatName( ticketSeatName( ticket ) );
        ticketDetailResponse.showtime( showtimeToShowtimeResponse( ticket.getShowtime() ) );
        ticketDetailResponse.seat( seatToSeatResponse( ticket.getSeat() ) );
        ticketDetailResponse.id( ticket.getId() );
        ticketDetailResponse.showtimeId( ticket.getShowtimeId() );
        ticketDetailResponse.seatId( ticket.getSeatId() );
        ticketDetailResponse.invoiceId( ticket.getInvoiceId() );
        ticketDetailResponse.price( ticket.getPrice() );
        ticketDetailResponse.status( ticket.getStatus() );
        ticketDetailResponse.qrCode( ticket.getQrCode() );
        ticketDetailResponse.createdAt( ticket.getCreatedAt() );
        ticketDetailResponse.scannedAt( ticket.getScannedAt() );
        ticketDetailResponse.isScanned( ticket.getIsScanned() );

        return ticketDetailResponse.build();
    }

    private String ticketSeatName(Ticket ticket) {
        Seat seat = ticket.getSeat();
        if ( seat == null ) {
            return null;
        }
        return seat.getName();
    }

    private Customer ticketInvoiceAccountCustomer(Ticket ticket) {
        Invoice invoice = ticket.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        Account account = invoice.getAccount();
        if ( account == null ) {
            return null;
        }
        return account.getCustomer();
    }

    protected CustomerResponse customerToCustomerResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponse.CustomerResponseBuilder customerResponse = CustomerResponse.builder();

        customerResponse.id( customer.getId() );
        customerResponse.firstName( customer.getFirstName() );
        customerResponse.lastName( customer.getLastName() );
        customerResponse.email( customer.getEmail() );
        customerResponse.phone( customer.getPhone() );
        customerResponse.gender( customer.getGender() );
        customerResponse.dateOfBirth( customer.getDateOfBirth() );
        customerResponse.address( customer.getAddress() );

        return customerResponse.build();
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

    protected ShowtimeResponse showtimeToShowtimeResponse(Showtime showtime) {
        if ( showtime == null ) {
            return null;
        }

        ShowtimeResponse.ShowtimeResponseBuilder showtimeResponse = ShowtimeResponse.builder();

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
}
