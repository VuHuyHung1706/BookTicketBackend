package com.web.backend.repository;

import com.web.backend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    boolean existsByShowtimeIdAndSeatIdAndStatus(Integer showtimeId, Integer seatId, Boolean status);
    List<Ticket> findByInvoiceId(Integer invoiceId);
    List<Ticket> findByShowtimeIdAndStatus(Integer showtimeId, Boolean status);
}
