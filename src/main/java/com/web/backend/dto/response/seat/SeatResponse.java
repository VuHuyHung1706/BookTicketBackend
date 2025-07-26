package com.web.backend.dto.response.seat;

import com.web.backend.constant.SeatType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponse {
    private Integer id;
    private String name;
    private Integer seatRow;
    private Integer seatColumn;
    private Integer roomId;
    private SeatType seatType;
}
