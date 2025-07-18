package com.web.backend.service.actor;

import com.web.backend.dto.response.actor.ActorResponse;

import java.util.List;

public interface ActorService {
    List<ActorResponse> getAllActors();
    ActorResponse getActorById(Integer id);
}
