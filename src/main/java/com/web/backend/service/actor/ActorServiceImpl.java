package com.web.backend.service.actor;

import com.web.backend.dto.response.actor.ActorResponse;
import com.web.backend.entity.Actor;
import com.web.backend.exception.AppException;
import com.web.backend.exception.ErrorCode;
import com.web.backend.mapper.ActorMapper;
import com.web.backend.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorMapper actorMapper;

    @Override
    public List<ActorResponse> getAllActors() {
        return actorRepository.findAll().stream()
                .map(actorMapper::toActorResponse)
                .toList();
    }

    @Override
    public ActorResponse getActorById(Integer id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACTOR_NOT_EXISTED));
        return actorMapper.toActorResponse(actor);
    }
}
