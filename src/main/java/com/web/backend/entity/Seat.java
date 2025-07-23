package com.web.backend.entity;

import com.web.backend.constant.SeatType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "seats")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name; // e.g., A1, B2

    @Column(name = "seat_row")
    private String row;

    @Column(name = "seat_col")
    private Integer col;

    @Column(name = "room_id")
    private Integer roomId;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type")
    @Builder.Default
    private SeatType seatType = SeatType.STANDARD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ToString.Exclude
    private Room room;

    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Ticket> tickets;
}
