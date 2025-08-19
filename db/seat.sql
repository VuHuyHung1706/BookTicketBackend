USE bookticket;

-- Insert Seats for all rooms (7 rows x 8 columns = 56 seats per room)
-- This will create seats for all 19 rooms

DELIMITER $$

CREATE PROCEDURE InsertSeatsForAllRooms()
BEGIN
    DECLARE room_counter INT DEFAULT 1;
    DECLARE max_rooms INT DEFAULT 19;
    DECLARE row_counter INT;
    DECLARE col_counter INT;
    DECLARE seat_name VARCHAR(10);

    WHILE room_counter <= max_rooms DO
        SET row_counter = 1;

        WHILE row_counter <= 7 DO
            SET col_counter = 1;

            WHILE col_counter <= 8 DO
                SET seat_name = CONCAT(CHAR(64 + row_counter), col_counter);

INSERT INTO seats (name, seat_row, seat_column, room_id)
VALUES (seat_name, row_counter, col_counter, room_counter);

SET col_counter = col_counter + 1;
END WHILE;

            SET row_counter = row_counter + 1;
END WHILE;

        SET room_counter = room_counter + 1;
END WHILE;
END$$

DELIMITER ;

-- Execute the procedure
CALL InsertSeatsForAllRooms();

-- Drop the procedure after use
DROP PROCEDURE InsertSeatsForAllRooms;
