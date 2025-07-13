package com.web.backend.entity;

import com.web.backend.constant.TicketStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "showtime_id")
    private Integer showtimeId;

    @Column(name = "seat_id")
    private Integer seatId;

    @Column(name = "invoice_id")
    private Integer invoiceId;

    @Column(name = "price")
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private TicketStatus status = TicketStatus.BOOKED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showtime_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ToString.Exclude
    private Showtime showtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ToString.Exclude
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id", insertable = false, updatable = false)
    @ToString.Exclude
    private Invoice invoice;
}
