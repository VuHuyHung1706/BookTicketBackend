package com.web.backend.repository;

import com.web.backend.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
    @Query("SELECT s FROM Showtime s WHERE s.roomId = :roomId AND " +
            "((s.startTime <= :startTime AND s.endTime > :startTime) OR " +
            "(s.startTime < :endTime AND s.endTime >= :endTime) OR " +
            "(s.startTime >= :startTime AND s.endTime <= :endTime))")
    List<Showtime> findConflictingShowtimes(@Param("roomId") Integer roomId,
                                            @Param("startTime") LocalDateTime startTime,
                                            @Param("endTime") LocalDateTime endTime);
    @Query(value = "SELECT * FROM showtimes s " +
            "WHERE DAY(s.start_time) = DAY(:date) " +
            "AND MONTH(s.start_time) = MONTH(:date) " +
            "AND YEAR(s.start_time) = YEAR(:date) " +
            "AND (s.room_id = :roomId) " +
            "ORDER BY s.start_time",
            nativeQuery = true)
    List<Showtime> findShowtimesByDateAndRoom(
            @Param("date") LocalDate date,
            @Param("roomId") Integer roomId
    );

    List<Showtime> findByMovieId(Integer movieId);
    List<Showtime> findByRoomCinemaId(Integer cinemaId);
    List<Showtime> findByMovieIdAndRoomCinemaId(Integer movieId, Integer cinemaId);
    List<Showtime> findByMovieIdAndRoomId(Integer movieId, Integer roomId);
    List<Showtime> findByRoomId(Integer roomId);
}
