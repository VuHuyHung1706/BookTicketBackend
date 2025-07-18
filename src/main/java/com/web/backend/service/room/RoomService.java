package com.web.backend.service.room;

import com.web.backend.dto.request.room.RoomRequest;
import com.web.backend.dto.response.room.RoomResponse;

import java.util.List;

public interface RoomService {
    List<RoomResponse> getAllRooms();
    List<RoomResponse> getRoomsByCinemaId(Integer cinemaId);
    RoomResponse getRoomById(Integer id);
    RoomResponse createRoom(RoomRequest request);
    RoomResponse updateRoom(Integer id, RoomRequest request);
    void deleteRoom(Integer id);
}
