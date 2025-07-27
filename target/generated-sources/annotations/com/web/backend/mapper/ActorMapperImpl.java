package com.web.backend.mapper;

import com.web.backend.dto.response.actor.ActorResponse;
import com.web.backend.entity.Actor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T03:48:43+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Amazon.com Inc.)"
)
@Component
public class ActorMapperImpl implements ActorMapper {

    @Override
    public ActorResponse toActorResponse(Actor actor) {
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
}
