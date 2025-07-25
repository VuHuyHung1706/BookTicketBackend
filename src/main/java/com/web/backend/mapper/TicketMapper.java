package com.web.backend.mapper;

import com.web.backend.dto.response.ticket.TicketDetailResponse;
import com.web.backend.dto.response.ticket.TicketResponse;
import com.web.backend.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(source = "seat.name", target = "seatName")
    TicketResponse toTicketResponse(Ticket ticket);

    @Mapping(source = "seat.name", target = "seatName")
    @Mapping(source = "showtime", target = "showtime")
    @Mapping(source = "seat", target = "seat")
    TicketDetailResponse toTicketDetailResponse(Ticket ticket);
}
