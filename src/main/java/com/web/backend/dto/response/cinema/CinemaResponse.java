package com.web.backend.dto.response.cinema;

import com.web.backend.dto.response.room.RoomResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinemaResponse {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private List<RoomResponse> rooms;
}
