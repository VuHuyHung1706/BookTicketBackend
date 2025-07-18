package com.web.backend.mapper;

import com.web.backend.dto.response.actor.ActorResponse;
import com.web.backend.entity.Actor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    ActorResponse toActorResponse(Actor actor);
}
