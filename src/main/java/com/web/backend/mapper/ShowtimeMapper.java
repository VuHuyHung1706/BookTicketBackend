package com.web.backend.mapper;

import com.web.backend.dto.request.showtime.ShowtimeRequest;
import com.web.backend.dto.response.showtime.ShowtimeResponse;
import com.web.backend.entity.Showtime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    Showtime toShowtime(ShowtimeRequest request);

    @Mapping(target = "cinemaName", source = "room.cinema.name")
    @Mapping(target = "roomName", source = "room.name")
    ShowtimeResponse toShowtimeResponse(Showtime showtime);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    @Mapping(target = "movie", ignore = true)
    void updateShowtime(@MappingTarget Showtime showtime, ShowtimeRequest request);
}
