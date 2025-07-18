package com.web.backend.controller;

import com.web.backend.dto.request.seat.SeatRequest;
import com.web.backend.dto.response.ApiResponse;
import com.web.backend.dto.response.seat.SeatResponse;
import com.web.backend.service.seat.SeatService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@SecurityRequirement(name = "Bearer")
@PreAuthorize("hasRole('MANAGER')")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/room/{roomId}")
    public ApiResponse<List<SeatResponse>> getSeatsByRoomId(@PathVariable Integer roomId) {
        return ApiResponse.<List<SeatResponse>>builder()
                .result(seatService.getSeatsByRoomId(roomId))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<SeatResponse> getSeatById(@PathVariable Integer id) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.getSeatById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<SeatResponse> createSeat(@Valid @RequestBody SeatRequest request) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.createSeat(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<SeatResponse> updateSeat(@PathVariable Integer id, @Valid @RequestBody SeatRequest request) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.updateSeat(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteSeat(@PathVariable Integer id) {
        seatService.deleteSeat(id);
        return ApiResponse.<String>builder()
                .result("Seat deleted successfully")
                .build();
    }
}
