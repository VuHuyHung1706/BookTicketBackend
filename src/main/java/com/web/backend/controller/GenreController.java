package com.web.backend.controller;

import com.web.backend.dto.response.ApiResponse;
import com.web.backend.dto.response.genre.GenreResponse;
import com.web.backend.service.genre.GenreService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@SecurityRequirement(name = "Bearer")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ApiResponse<List<GenreResponse>> getAllGenres() {
        return ApiResponse.<List<GenreResponse>>builder()
                .result(genreService.getAllGenres())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<GenreResponse> getGenreById(@PathVariable Integer id) {
        return ApiResponse.<GenreResponse>builder()
                .result(genreService.getGenreById(id))
                .build();
    }

}
