package com.web.backend.controller;

import com.web.backend.dto.request.room.RoomRequest;
import com.web.backend.dto.response.ApiResponse;
import com.web.backend.dto.response.room.RoomResponse;
import com.web.backend.service.room.RoomService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@SecurityRequirement(name = "Bearer")
@PreAuthorize("hasRole('MANAGER')")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ApiResponse<List<RoomResponse>> getAllRooms() {
        return ApiResponse.<List<RoomResponse>>builder()
                .result(roomService.getAllRooms())
                .build();
    }

    @GetMapping("/cinema/{cinemaId}")
    public ApiResponse<List<RoomResponse>> getRoomsByCinemaId(@PathVariable Integer cinemaId) {
        return ApiResponse.<List<RoomResponse>>builder()
                .result(roomService.getRoomsByCinemaId(cinemaId))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<RoomResponse> getRoomById(@PathVariable Integer id) {
        return ApiResponse.<RoomResponse>builder()
                .result(roomService.getRoomById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<RoomResponse> createRoom(@Valid @RequestBody RoomRequest request) {
        return ApiResponse.<RoomResponse>builder()
                .result(roomService.createRoom(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<RoomResponse> updateRoom(@PathVariable Integer id, @Valid @RequestBody RoomRequest request) {
        return ApiResponse.<RoomResponse>builder()
                .result(roomService.updateRoom(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteRoom(@PathVariable Integer id) {
        roomService.deleteRoom(id);
        return ApiResponse.<String>builder()
                .result("Room deleted successfully")
                .build();
    }

}
