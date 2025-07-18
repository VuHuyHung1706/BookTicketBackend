package com.web.backend.mapper;

import com.web.backend.dto.request.seat.SeatRequest;
import com.web.backend.dto.response.seat.SeatResponse;
import com.web.backend.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    Seat toSeat(SeatRequest request);

    SeatResponse toSeatResponse(Seat seat);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    void updateSeat(@MappingTarget Seat seat, SeatRequest request);
}
