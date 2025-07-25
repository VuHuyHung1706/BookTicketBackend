package com.web.backend.mapper;

import com.web.backend.dto.request.cinema.CinemaRequest;
import com.web.backend.dto.response.cinema.CinemaResponse;
import com.web.backend.entity.Cinema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CinemaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    Cinema toCinema(CinemaRequest request);

    CinemaResponse toCinemaResponse(Cinema cinema);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    void updateCinema(@MappingTarget Cinema cinema, CinemaRequest request);
}
