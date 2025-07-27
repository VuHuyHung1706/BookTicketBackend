package com.web.backend.mapper;

import com.web.backend.dto.request.seat.SeatRequest;
import com.web.backend.dto.response.seat.SeatResponse;
import com.web.backend.entity.Seat;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T03:48:44+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Amazon.com Inc.)"
)
@Component
public class SeatMapperImpl implements SeatMapper {

    @Override
    public Seat toSeat(SeatRequest request) {
        if ( request == null ) {
            return null;
        }

        Seat.SeatBuilder seat = Seat.builder();

        seat.name( request.getName() );
        seat.seatRow( request.getSeatRow() );
        seat.seatColumn( request.getSeatColumn() );
        seat.roomId( request.getRoomId() );

        return seat.build();
    }

    @Override
    public SeatResponse toSeatResponse(Seat seat) {
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

    @Override
    public void updateSeat(Seat seat, SeatRequest request) {
        if ( request == null ) {
            return;
        }

        seat.setName( request.getName() );
        seat.setSeatRow( request.getSeatRow() );
        seat.setSeatColumn( request.getSeatColumn() );
        seat.setRoomId( request.getRoomId() );
    }
}
