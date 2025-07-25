package com.web.backend.service.cinema;

import com.web.backend.dto.request.cinema.CinemaRequest;
import com.web.backend.dto.response.cinema.CinemaResponse;

import java.util.List;

public interface CinemaService {
    List<CinemaResponse> getAllCinemas();
    CinemaResponse getCinemaById(Integer id);
    CinemaResponse createCinema(CinemaRequest request);
    CinemaResponse updateCinema(Integer id, CinemaRequest request);
    void deleteCinema(Integer id);
}
