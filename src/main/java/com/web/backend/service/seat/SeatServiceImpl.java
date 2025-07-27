package com.web.backend.service.seat;

import com.web.backend.dto.request.seat.SeatRequest;
import com.web.backend.dto.response.seat.SeatResponse;
import com.web.backend.entity.Seat;
import com.web.backend.exception.AppException;
import com.web.backend.exception.ErrorCode;
import com.web.backend.mapper.SeatMapper;
import com.web.backend.repository.SeatRepository;
import com.web.backend.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private SeatMapper seatMapper;

    @Override
    public List<SeatResponse> getSeatsByRoomId(Integer roomId) {
        return seatRepository.findByRoomIdOrderByName(roomId)
                .stream()
                .map(seatMapper::toSeatResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SeatResponse getSeatById(Integer id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return seatMapper.toSeatResponse(seat);
    }

    @Override
    public SeatResponse createSeat(SeatRequest request) {
        if (!roomRepository.existsById(request.getRoomId())) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        Seat seat = seatMapper.toSeat(request);
        seat = seatRepository.save(seat);
        return seatMapper.toSeatResponse(seat);
    }

    @Override
    public SeatResponse updateSeat(Integer id, SeatRequest request) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (!roomRepository.existsById(request.getRoomId())) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        seatMapper.updateSeat(seat, request);
        seat = seatRepository.save(seat);
        return seatMapper.toSeatResponse(seat);
    }

    @Override
    public void deleteSeat(Integer id) {
        if (!seatRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        seatRepository.deleteById(id);
    }
}
