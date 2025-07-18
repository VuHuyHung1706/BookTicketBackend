package com.web.backend.service.cinema;

import com.web.backend.dto.request.CinemaRequest;
import com.web.backend.dto.response.cinema.CinemaResponse;
import com.web.backend.entity.Cinema;
import com.web.backend.exception.AppException;
import com.web.backend.exception.ErrorCode;
import com.web.backend.mapper.CinemaMapper;
import com.web.backend.repository.CinemaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CinemaMapper cinemaMapper;

    @Override
    public List<CinemaResponse> getAllCinemas() {
        return cinemaRepository.findAll()
                .stream()
                .map(cinemaMapper::toCinemaResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CinemaResponse getCinemaById(Integer id) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_NOT_EXISTED));
        return cinemaMapper.toCinemaResponse(cinema);
    }

    @Override
    public CinemaResponse createCinema(CinemaRequest request) {
        Cinema cinema = cinemaMapper.toCinema(request);
        cinema = cinemaRepository.save(cinema);
        return cinemaMapper.toCinemaResponse(cinema);
    }

    @Override
    public CinemaResponse updateCinema(Integer id, CinemaRequest request) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_NOT_EXISTED));

        cinemaMapper.updateCinema(cinema, request);
        cinema = cinemaRepository.save(cinema);
        return cinemaMapper.toCinemaResponse(cinema);
    }

    @Override
    public void deleteCinema(Integer id) {
        if (!cinemaRepository.existsById(id)) {
            throw new AppException(ErrorCode.CINEMA_NOT_EXISTED);
        }
        cinemaRepository.deleteById(id);
    }
}
