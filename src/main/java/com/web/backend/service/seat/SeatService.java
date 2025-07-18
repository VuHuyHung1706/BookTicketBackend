package com.web.backend.service.seat;

import com.web.backend.dto.request.seat.SeatRequest;
import com.web.backend.dto.response.seat.SeatResponse;

import java.util.List;

public interface SeatService {
    List<SeatResponse> getSeatsByRoomId(Integer roomId);
    SeatResponse getSeatById(Integer id);
    SeatResponse createSeat(SeatRequest request);
    SeatResponse updateSeat(Integer id, SeatRequest request);
    void deleteSeat(Integer id);
}
