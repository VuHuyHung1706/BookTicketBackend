package com.web.backend.controller;

import com.web.backend.dto.request.CinemaRequest;
import com.web.backend.dto.response.ApiResponse;
import com.web.backend.dto.response.cinema.CinemaResponse;
import com.web.backend.service.cinema.CinemaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
@SecurityRequirement(name = "Bearer")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping
    public ApiResponse<List<CinemaResponse>> getAllCinemas() {
        return ApiResponse.<List<CinemaResponse>>builder()
                .result(cinemaService.getAllCinemas())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CinemaResponse> getCinemaById(@PathVariable Integer id) {
        return ApiResponse.<CinemaResponse>builder()
                .result(cinemaService.getCinemaById(id))
                .build();
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ApiResponse<CinemaResponse> createCinema(@Valid @RequestBody CinemaRequest request) {
        return ApiResponse.<CinemaResponse>builder()
                .result(cinemaService.createCinema(request))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ApiResponse<CinemaResponse> updateCinema(@PathVariable Integer id, @Valid @RequestBody CinemaRequest request) {
        return ApiResponse.<CinemaResponse>builder()
                .result(cinemaService.updateCinema(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ApiResponse<String> deleteCinema(@PathVariable Integer id) {
        cinemaService.deleteCinema(id);
        return ApiResponse.<String>builder()
                .result("Cinema deleted successfully")
                .build();
    }
}
