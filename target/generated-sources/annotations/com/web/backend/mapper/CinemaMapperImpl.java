package com.web.backend.mapper;

import com.web.backend.dto.request.cinema.CinemaRequest;
import com.web.backend.dto.response.cinema.CinemaResponse;
import com.web.backend.dto.response.room.RoomResponse;
import com.web.backend.dto.response.seat.SeatResponse;
import com.web.backend.entity.Cinema;
import com.web.backend.entity.Room;
import com.web.backend.entity.Seat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T03:48:43+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Amazon.com Inc.)"
)
@Component
public class CinemaMapperImpl implements CinemaMapper {

    @Override
    public Cinema toCinema(CinemaRequest request) {
        if ( request == null ) {
            return null;
        }

        Cinema.CinemaBuilder cinema = Cinema.builder();

        cinema.name( request.getName() );
        cinema.address( request.getAddress() );
        cinema.phone( request.getPhone() );

        return cinema.build();
    }

    @Override
    public CinemaResponse toCinemaResponse(Cinema cinema) {
        if ( cinema == null ) {
            return null;
        }

        CinemaResponse.CinemaResponseBuilder cinemaResponse = CinemaResponse.builder();

        cinemaResponse.id( cinema.getId() );
        cinemaResponse.name( cinema.getName() );
        cinemaResponse.address( cinema.getAddress() );
        cinemaResponse.phone( cinema.getPhone() );
        cinemaResponse.rooms( roomListToRoomResponseList( cinema.getRooms() ) );

        return cinemaResponse.build();
    }

    @Override
    public void updateCinema(Cinema cinema, CinemaRequest request) {
        if ( request == null ) {
            return;
        }

        cinema.setName( request.getName() );
        cinema.setAddress( request.getAddress() );
        cinema.setPhone( request.getPhone() );
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

    protected List<RoomResponse> roomListToRoomResponseList(List<Room> list) {
        if ( list == null ) {
            return null;
        }

        List<RoomResponse> list1 = new ArrayList<RoomResponse>( list.size() );
        for ( Room room : list ) {
            list1.add( roomToRoomResponse( room ) );
        }

        return list1;
    }
}
