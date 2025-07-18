package com.web.backend.controller;

import com.web.backend.dto.response.ApiResponse;
import com.web.backend.dto.response.actor.ActorResponse;
import com.web.backend.service.actor.ActorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actors")
@SecurityRequirement(name = "Bearer")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public ApiResponse<List<ActorResponse>> getAllActors() {
        return ApiResponse.<List<ActorResponse>>builder()
                .result(actorService.getAllActors())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ActorResponse> getActorById(@PathVariable Integer id) {
        return ApiResponse.<ActorResponse>builder()
                .result(actorService.getActorById(id))
                .build();
    }
}
