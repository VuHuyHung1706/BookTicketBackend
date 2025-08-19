USE bookticket;

-- Insert Showtimes based on current date
-- This will create showtimes for the next 7 days
-- Each movie will have multiple showtimes across different cinemas and rooms

DELIMITER $$

CREATE PROCEDURE InsertShowtimes()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE movie_id_var INT;
    DECLARE movie_duration_var INT;
    DECLARE room_id_var INT;
    DECLARE day_offset INT;
    DECLARE time_slot INT;
    DECLARE start_datetime DATETIME;
    DECLARE end_datetime DATETIME;
    DECLARE base_price INT DEFAULT 80000; -- Base price 80,000 VND
    DECLARE weekend_price INT DEFAULT 100000; -- Weekend price 100,000 VND
    DECLARE ticket_price INT;

    -- Cursor to get all movies with their durations
    DECLARE movie_cursor CURSOR FOR
SELECT id, duration FROM movies;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- Loop through each day (next 7 days)
    SET day_offset = 0;
    WHILE day_offset < 7 DO

        -- Reset movie cursor for each day
        SET done = FALSE;
OPEN movie_cursor;

movie_loop: LOOP
            FETCH movie_cursor INTO movie_id_var, movie_duration_var;
            IF done THEN
                LEAVE movie_loop;
END IF;

            -- Each movie will have 3-4 showtimes per day across different rooms
            SET time_slot = 1;
            WHILE time_slot <= 4 DO

                -- Calculate room_id (rotate through rooms 1-19)
                SET room_id_var = ((movie_id_var + day_offset + time_slot - 3) % 19) + 1;

                -- Calculate start time based on time slot
CASE time_slot
                    WHEN 1 THEN SET start_datetime = DATE_ADD(DATE_ADD(CURDATE(), INTERVAL day_offset DAY), INTERVAL 9 HOUR); -- 9:00 AM
WHEN 2 THEN SET start_datetime = DATE_ADD(DATE_ADD(CURDATE(), INTERVAL day_offset DAY), INTERVAL 14 HOUR); -- 2:00 PM
WHEN 3 THEN SET start_datetime = DATE_ADD(DATE_ADD(CURDATE(), INTERVAL day_offset DAY), INTERVAL 17 HOUR); -- 5:00 PM
WHEN 4 THEN SET start_datetime = DATE_ADD(DATE_ADD(CURDATE(), INTERVAL day_offset DAY), INTERVAL 20 HOUR); -- 8:00 PM
END CASE;

                -- Calculate end time
                SET end_datetime = DATE_ADD(start_datetime, INTERVAL movie_duration_var MINUTE);

                -- Determine ticket price (weekend is more expensive)
                IF DAYOFWEEK(DATE_ADD(CURDATE(), INTERVAL day_offset DAY)) IN (1, 7) THEN -- Sunday = 1, Saturday = 7
                    SET ticket_price = weekend_price;
ELSE
                    SET ticket_price = base_price;
END IF;

                -- Add premium for evening shows (after 5 PM)
                IF HOUR(start_datetime) >= 17 THEN
                    SET ticket_price = ticket_price + 20000; -- Add 20,000 VND for evening shows
END IF;

                -- Insert showtime (avoid conflicts by checking if showtime already exists)
                INSERT IGNORE INTO showtimes (movie_id, room_id, start_time, end_time, ticket_price)
                VALUES (movie_id_var, room_id_var, start_datetime, end_datetime, ticket_price);

                SET time_slot = time_slot + 1;
END WHILE;

END LOOP;

CLOSE movie_cursor;
SET day_offset = day_offset + 1;
END WHILE;

END$$

DELIMITER ;

-- Execute the procedure
CALL InsertShowtimes();

-- Drop the procedure after use
DROP PROCEDURE InsertShowtimes;

-- Additional showtimes for popular movies (more showtimes for blockbusters)
INSERT INTO showtimes (movie_id, room_id, start_time, end_time, ticket_price) VALUES
-- Avengers: Endgame - Extra showtimes
(1, 1, DATE_ADD(CURDATE(), INTERVAL 10 HOUR), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 10 HOUR), INTERVAL 181 MINUTE), 120000),
(1, 6, DATE_ADD(CURDATE(), INTERVAL 13 HOUR), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 13 HOUR), INTERVAL 181 MINUTE), 100000),
(1, 12, DATE_ADD(CURDATE(), INTERVAL 21 HOUR), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 21 HOUR), INTERVAL 181 MINUTE), 120000),

-- The Dark Knight - Extra showtimes
(2, 2, DATE_ADD(CURDATE(), INTERVAL 11 HOUR), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 11 HOUR), INTERVAL 152 MINUTE), 100000),
(2, 7, DATE_ADD(CURDATE(), INTERVAL 15 HOUR), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 15 HOUR), INTERVAL 152 MINUTE), 100000),
(2, 16, DATE_ADD(CURDATE(), INTERVAL 19 HOUR), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 19 HOUR), INTERVAL 152 MINUTE), 120000),

-- Inception - Extra showtimes
(3, 3, DATE_ADD(CURDATE(), INTERVAL 12 HOUR), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 12 HOUR), INTERVAL 148 MINUTE), 100000),
(3, 8, DATE_ADD(CURDATE(), INTERVAL 16 HOUR), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 16 HOUR), INTERVAL 148 MINUTE), 100000),

-- Tomorrow's showtimes for variety
(1, 4, DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 DAY), INTERVAL 10 HOUR), DATE_ADD(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 DAY), INTERVAL 10 HOUR), INTERVAL 181 MINUTE), 100000),
(2, 9, DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 DAY), INTERVAL 14 HOUR), DATE_ADD(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 DAY), INTERVAL 14 HOUR), INTERVAL 152 MINUTE), 100000),
(3, 13, DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 DAY), INTERVAL 18 HOUR), DATE_ADD(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 DAY), INTERVAL 18 HOUR), INTERVAL 148 MINUTE), 120000),
(4, 17, DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 DAY), INTERVAL 20 HOUR), DATE_ADD(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 DAY), INTERVAL 20 HOUR), INTERVAL 194 MINUTE), 120000),

-- Day after tomorrow's showtimes
(5, 5, DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 2 DAY), INTERVAL 9 HOUR), DATE_ADD(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 2 DAY), INTERVAL 9 HOUR), INTERVAL 142 MINUTE), 80000),
(6, 10, DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 2 DAY), INTERVAL 13 HOUR), DATE_ADD(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 2 DAY), INTERVAL 13 HOUR), INTERVAL 142 MINUTE), 80000),
(7, 14, DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 2 DAY), INTERVAL 17 HOUR), DATE_ADD(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 2 DAY), INTERVAL 17 HOUR), INTERVAL 136 MINUTE), 100000),
(8, 18, DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 2 DAY), INTERVAL 21 HOUR), DATE_ADD(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 2 DAY), INTERVAL 21 HOUR), INTERVAL 154 MINUTE), 120000);

-- Add some past showtimes for testing (movies that have already ended)
INSERT INTO showtimes (movie_id, room_id, start_time, end_time, ticket_price) VALUES
-- Yesterday's completed showtimes
(9, 1, DATE_SUB(DATE_ADD(CURDATE(), INTERVAL 9 HOUR), INTERVAL 1 DAY), DATE_SUB(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 9 HOUR), INTERVAL 175 MINUTE), INTERVAL 1 DAY), 100000),
(10, 2, DATE_SUB(DATE_ADD(CURDATE(), INTERVAL 14 HOUR), INTERVAL 1 DAY), DATE_SUB(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 14 HOUR), INTERVAL 195 MINUTE), INTERVAL 1 DAY), 100000),
(11, 3, DATE_SUB(DATE_ADD(CURDATE(), INTERVAL 19 HOUR), INTERVAL 1 DAY), DATE_SUB(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 19 HOUR), INTERVAL 169 MINUTE), INTERVAL 1 DAY), 120000),

-- Two days ago
(12, 4, DATE_SUB(DATE_ADD(CURDATE(), INTERVAL 10 HOUR), INTERVAL 2 DAY), DATE_SUB(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 10 HOUR), INTERVAL 88 MINUTE), INTERVAL 2 DAY), 80000),
(13, 5, DATE_SUB(DATE_ADD(CURDATE(), INTERVAL 15 HOUR), INTERVAL 2 DAY), DATE_SUB(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 15 HOUR), INTERVAL 155 MINUTE), INTERVAL 2 DAY), 100000),
(14, 6, DATE_SUB(DATE_ADD(CURDATE(), INTERVAL 20 HOUR), INTERVAL 2 DAY), DATE_SUB(DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 20 HOUR), INTERVAL 162 MINUTE), INTERVAL 2 DAY), 120000);
