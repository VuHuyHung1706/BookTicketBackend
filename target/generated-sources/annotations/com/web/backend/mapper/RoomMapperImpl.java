package com.web.backend.mapper;

import com.web.backend.dto.request.room.RoomRequest;
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
    date = "2025-08-13T17:06:09+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public Room toRoom(RoomRequest request) {
        if ( request == null ) {
            return null;
        }

        Room.RoomBuilder room = Room.builder();

        room.name( request.getName() );
        room.totalSeats( request.getTotalSeats() );
        room.cinemaId( request.getCinemaId() );

        return room.build();
    }

    @Override
    public RoomResponse toRoomResponse(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomResponse.RoomResponseBuilder roomResponse = RoomResponse.builder();

        roomResponse.cinemaName( roomCinemaName( room ) );
        roomResponse.id( room.getId() );
        roomResponse.name( room.getName() );
        roomResponse.totalSeats( room.getTotalSeats() );
        roomResponse.cinemaId( room.getCinemaId() );
        roomResponse.seats( seatListToSeatResponseList( room.getSeats() ) );

        return roomResponse.build();
    }

    @Override
    public void updateRoom(Room room, RoomRequest request) {
        if ( request == null ) {
            return;
        }

        room.setName( request.getName() );
        room.setTotalSeats( request.getTotalSeats() );
        room.setCinemaId( request.getCinemaId() );
    }

    private String roomCinemaName(Room room) {
        Cinema cinema = room.getCinema();
        if ( cinema == null ) {
            return null;
        }
        return cinema.getName();
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
}
