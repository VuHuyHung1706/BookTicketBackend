package com.web.backend.mapper;

import com.web.backend.dto.request.room.RoomRequest;
import com.web.backend.dto.response.room.RoomResponse;
import com.web.backend.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cinema", ignore = true)
    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "showtimes", ignore = true)
    Room toRoom(RoomRequest request);

    @Mapping(source = "cinema.name", target = "cinemaName")
    RoomResponse toRoomResponse(Room room);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cinema", ignore = true)
    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "showtimes", ignore = true)
    void updateRoom(@MappingTarget Room room, RoomRequest request);
}
