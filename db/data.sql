-- Insert Genres
INSERT INTO genres (name) VALUES
                              ('Action'),
                              ('Drama'),
                              ('Comedy'),
                              ('Horror'),
                              ('Romance'),
                              ('Sci-Fi'),
                              ('Thriller'),
                              ('Adventure');

-- Insert Actors
INSERT INTO actors (first_name, last_name, gender, date_of_birth, nationality) VALUES
                                                                                   ('Tom', 'Cruise', true, '1962-07-03', 'American'),
                                                                                   ('Scarlett', 'Johansson', false, '1984-11-22', 'American'),
                                                                                   ('Leonardo', 'DiCaprio', true, '1974-11-11', 'American'),
                                                                                   ('Emma', 'Stone', false, '1988-11-06', 'American'),
                                                                                   ('Robert', 'Downey Jr.', true, '1965-04-04', 'American'),
                                                                                   ('Jennifer', 'Lawrence', false, '1990-08-15', 'American'),
                                                                                   ('Chris', 'Evans', true, '1981-06-13', 'American'),
                                                                                   ('Margot', 'Robbie', false, '1990-07-02', 'Australian'),
                                                                                   ('Ryan', 'Gosling', true, '1980-11-12', 'Canadian'),
                                                                                   ('Gal', 'Gadot', false, '1985-04-30', 'Israeli'),
                                                                                   ('Dwayne', 'Johnson', true, '1972-05-02', 'American'),
                                                                                   ('Anne', 'Hathaway', false, '1982-11-12', 'American');

-- Insert Movies
INSERT INTO movies (title, description, duration, language, poster, trailer, release_date) VALUES
                                                                                                                 ('Avengers: Endgame', 'The Avengers assemble once more to reverse the damage caused by Thanos in Infinity War.', 181, 'English', 'https://i.ebayimg.com/images/g/wmUAAOSwasZcw2Ts/s-l1200.jpg', 'https://example.com/avengers-endgame-trailer.mp4', '2024-01-15'),

                                                                                                                 ('La La Land', 'A jazz musician and an aspiring actress fall in love while pursuing their dreams in Los Angeles.', 128, 'English', 'https://m.media-amazon.com/images/M/MV5BMzUzNDM2NzM2MV5BMl5BanBnXkFtZTgwNTM3NTg4OTE@._V1_FMjpg_UX1000_.jpg', 'https://example.com/la-la-land-trailer.mp4', '2024-02-01'),

                                                                                                                 ('The Dark Knight', 'Batman faces the Joker, a criminal mastermind who wants to plunge Gotham City into anarchy.', 152, 'English', 'https://m.media-amazon.com/images/S/pv-target-images/e9a43e647b2ca70e75a3c0af046c4dfdcd712380889779cbdc2c57d94ab63902.jpg', 'https://example.com/dark-knight-trailer.mp4', '2024-01-20',),

                                                                                                                 ('Inception', 'A thief who enters people\'s dreams to steal secrets is given a chance to have his criminal record erased.', 148, 'English', 'https://play-lh.googleusercontent.com/buKf27Hxendp3tLNpNtP3E-amP0o4yYV-SGKyS2u-Y3GdGRTyfNCIT5WAVs2OudOz6so5K1jtYdAUKI9nw8=w240-h480-rw', 'https://example.com/inception-trailer.mp4', '2024-03-01'),

('Titanic', 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious Titanic.', 195, 'English', 'https://m.media-amazon.com/images/I/811lT7khIrL.jpg', 'https://example.com/titanic-trailer.mp4', '2024-02-14');

-- Link movies with genres
INSERT INTO movie_genres (movie_id, genre_id) VALUES
-- Avengers: Endgame (Action, Adventure, Sci-Fi)
(1, 1), (1, 8), (1, 6),
-- La La Land (Romance, Drama, Comedy)
(2, 5), (2, 2), (2, 3),
-- The Dark Knight (Action, Thriller, Drama)
(3, 1), (3, 7), (3, 2),
-- Inception (Sci-Fi, Thriller, Action)
(4, 6), (4, 7), (4, 1),
-- Titanic (Romance, Drama)
(5, 5), (5, 2);

-- Link movies with actors
INSERT INTO movie_actors (movie_id, actor_id) VALUES
-- Avengers: Endgame
(1, 5), (1, 2), (1, 7),
-- La La Land
(2, 4), (2, 9),
-- The Dark Knight
(3, 1), (3, 12),
-- Inception
(4, 3), (4, 6),
-- Titanic
(5, 3), (5, 12);

-- Insert Cinemas
INSERT INTO cinemas (name, address, phone) VALUES
('CGV Vincom Center', '191 Ba Trieu, Hai Ba Trung, Ha Noi', '1900-6017'),
('Lotte Cinema Landmark', '5B Nguyen Dinh Chieu, District 1, Ho Chi Minh City', '1900-5555'),
('Galaxy Cinema Nguyen Du', '116 Nguyen Du, District 1, Ho Chi Minh City', '1900-2224');

-- Insert Rooms for Cinema 1 (CGV Vincom Center)
INSERT INTO rooms (name, total_seats, cinema_id) VALUES
('Screen 1', 72, 1),
('Screen 2', 72, 1),
('Screen 3', 72, 1),
('Screen 4', 72, 1);

-- Insert Rooms for Cinema 2 (Lotte Cinema Landmark)
INSERT INTO rooms (name, total_seats, cinema_id) VALUES
('Hall A', 72, 2),
('Hall B', 72, 2),
('Hall C', 72, 2),
('Hall D', 72, 2);

-- Insert Rooms for Cinema 3 (Galaxy Cinema Nguyen Du)
INSERT INTO rooms (name, total_seats, cinema_id) VALUES
('Theater 1', 72, 3),
('Theater 2', 72, 3),
('Theater 3', 72, 3),
('Theater 4', 72, 3);


-- Function to create seats for a room
-- This script will create seats for all 12 rooms (3 cinemas x 4 rooms each)
-- Each room has 6 rows (A-F) and 12 columns
-- Row A-B: STANDARD seats
-- Row C-D: VIP seats
-- Row E-F: COUPLE seats (each couple seat takes 2 positions)

-- Cinema 1 - Room 1 (Screen 1)
INSERT INTO seats (name, seat_row, seat_column, room_id) VALUES
-- Row A (STANDARD)
('A1', 1, 1, 1), ('A2', 1, 2, 1), ('A3', 1, 3, 1), ('A4', 1, 4, 1),
('A5', 1, 5, 1), ('A6', 1, 6, 1), ('A7', 1, 7, 1), ('A8', 1, 8, 1),
('A9', 1, 9, 1), ('A10', 1, 10, 1), ('A11', 1, 11, 1), ('A12', 1, 12, 1),
-- Row B (STANDARD)
('B1', 2, 1, 1), ('B2', 2, 2, 1), ('B3', 2, 3, 1), ('B4', 2, 4, 1),
('B5', 2, 5, 1), ('B6', 2, 6, 1), ('B7', 2, 7, 1), ('B8', 2, 8, 1),
('B9', 2, 9, 1), ('B10', 2, 10, 1), ('B11', 2, 11, 1), ('B12', 2, 12, 1),
-- Row C (VIP)
('C1', 3, 1, 1), ('C2', 3, 2, 1), ('C3', 3, 3, 1), ('C4', 3, 4, 1),
('C5', 3, 5, 1), ('C6', 3, 6, 1), ('C7', 3, 7, 1), ('C8', 3, 8, 1),
('C9', 3, 9, 1), ('C10', 3, 10, 1), ('C11', 3, 11, 1), ('C12', 3, 12, 1),
-- Row D (VIP)
('D1', 4, 1, 1), ('D2', 4, 2, 1), ('D3', 4, 3, 1), ('D4', 4, 4, 1),
('D5', 4, 5, 1), ('D6', 4, 6, 1), ('D7', 4, 7, 1), ('D8', 4, 8, 1),
('D9', 4, 9, 1), ('D10', 4, 10, 1), ('D11', 4, 11, 1), ('D12', 4, 12, 1),
-- Row E (COUPLE - 6 couple seats)
('E1-E2', 5, 1, 1), ('E3-E4', 5, 3, 1), ('E5-E6', 5, 5, 1),
('E7-E8', 5, 7, 1), ('E9-E10', 5, 9, 1), ('E11-E12', 5, 11, 1),
-- Row F (COUPLE - 6 couple seats)
('F1-F2', 6, 1, 1), ('F3-F4', 6, 3, 1), ('F5-F6', 6, 5, 1),
('F7-F8', 6, 7, 1), ('F9-F10', 6, 9, 1), ('F11-F12', 6, 11, 1);

-- Cinema 1 - Room 2 (Screen 2)
INSERT INTO seats (name, seat_row, seat_column, room_id) VALUES
-- Row A (STANDARD)
('A1', 1, 1, 2), ('A2', 1, 2, 2), ('A3', 1, 3, 2), ('A4', 1, 4, 2),
('A5', 1, 5, 2), ('A6', 1, 6, 2), ('A7', 1, 7, 2), ('A8', 1, 8, 2),
('A9', 1, 9, 2), ('A10', 1, 10, 2), ('A11', 1, 11, 2), ('A12', 1, 12, 2),
-- Row B (STANDARD)
('B1', 2, 1, 2), ('B2', 2, 2, 2), ('B3', 2, 3, 2), ('B4', 2, 4, 2),
('B5', 2, 5, 2), ('B6', 2, 6, 2), ('B7', 2, 7, 2), ('B8', 2, 8, 2),
('B9', 2, 9, 2), ('B10', 2, 10, 2), ('B11', 2, 11, 2), ('B12', 2, 12, 2),
-- Row C (VIP)
('C1', 3, 1, 2), ('C2', 3, 2, 2), ('C3', 3, 3, 2), ('C4', 3, 4, 2),
('C5', 3, 5, 2), ('C6', 3, 6, 2), ('C7', 3, 7, 2), ('C8', 3, 8, 2),
('C9', 3, 9, 2), ('C10', 3, 10, 2), ('C11', 3, 11, 2), ('C12', 3, 12, 2),
-- Row D (VIP)
('D1', 4, 1, 2), ('D2', 4, 2, 2), ('D3', 4, 3, 2), ('D4', 4, 4, 2),
('D5', 4, 5, 2), ('D6', 4, 6, 2), ('D7', 4, 7, 2), ('D8', 4, 8, 2),
('D9', 4, 9, 2), ('D10', 4, 10, 2), ('D11', 4, 11, 2), ('D12', 4, 12, 2),
-- Row E (COUPLE)
('E1-E2', 5, 1, 2), ('E3-E4', 5, 3, 2), ('E5-E6', 5, 5, 2),
('E7-E8', 5, 7, 2), ('E9-E10', 5, 9, 2), ('E11-E12', 5, 11, 2),
-- Row F (COUPLE)
('F1-F2', 6, 1, 2), ('F3-F4', 6, 3, 2), ('F5-F6', 6, 5, 2),
('F7-F8', 6, 7, 2), ('F9-F10', 6, 9, 2), ('F11-F12', 6, 11, 2);

-- Continue creating seats for remaining rooms (3-12)
-- Cinema 1 - Room 3 (Screen 3)
INSERT INTO seats (name, seat_row, seat_column, room_id) VALUES
-- Row A-B (STANDARD), Row C-D (VIP), Row E-F (COUPLE)
('A1', 1, 1, 3), ('A2', 1, 2, 3), ('A3', 1, 3, 3), ('A4', 1, 4, 3),
('A5', 1, 5, 3), ('A6', 1, 6, 3), ('A7', 1, 7, 3), ('A8', 1, 8, 3),
('A9', 1, 9, 3), ('A10', 1, 10, 3), ('A11', 1, 11, 3), ('A12', 1, 12, 3),
('B1', 2, 1, 3), ('B2', 2, 2, 3), ('B3', 2, 3, 3), ('B4', 2, 4, 3),
('B5', 2, 5, 3), ('B6', 2, 6, 3), ('B7', 2, 7, 3), ('B8', 2, 8, 3),
('B9', 2, 9, 3), ('B10', 2, 10, 3), ('B11', 2, 11, 3), ('B12', 2, 12, 3),
('C1', 3, 1, 3), ('C2', 3, 2, 3), ('C3', 3, 3, 3), ('C4', 3, 4, 3),
('C5', 3, 5, 3), ('C6', 3, 6, 3), ('C7', 3, 7, 3), ('C8', 3, 8, 3),
('C9', 3, 9, 3), ('C10', 3, 10, 3), ('C11', 3, 11, 3), ('C12', 3, 12, 3),
('D1', 4, 1, 3), ('D2', 4, 2, 3), ('D3', 4, 3, 3), ('D4', 4, 4, 3),
('D5', 4, 5, 3), ('D6', 4, 6, 3), ('D7', 4, 7, 3), ('D8', 4, 8, 3),
('D9', 4, 9, 3), ('D10', 4, 10, 3), ('D11', 4, 11, 3), ('D12', 4, 12, 3),
('E1-E2', 5, 1, 3), ('E3-E4', 5, 3, 3), ('E5-E6', 5, 5, 3),
('E7-E8', 5, 7, 3), ('E9-E10', 5, 9, 3), ('E11-E12', 5, 11, 3),
('F1-F2', 6, 1, 3), ('F3-F4', 6, 3, 3), ('F5-F6', 6, 5, 3),
('F7-F8', 6, 7, 3), ('F9-F10', 6, 9, 3), ('F11-F12', 6, 11, 3);

-- Cinema 1 - Room 4 (Screen 4)
INSERT INTO seats (name, seat_row, seat_column, room_id) VALUES
('A1', 1, 1, 4), ('A2', 1, 2, 4), ('A3', 1, 3, 4), ('A4', 1, 4, 4),
('A5', 1, 5, 4), ('A6', 1, 6, 4), ('A7', 1, 7, 4), ('A8', 1, 8, 4),
('A9', 1, 9, 4), ('A10', 1, 10, 4), ('A11', 1, 11, 4), ('A12', 1, 12, 4),
('B1', 2, 1, 4), ('B2', 2, 2, 4), ('B3', 2, 3, 4), ('B4', 2, 4, 4),
('B5', 2, 5, 4), ('B6', 2, 6, 4), ('B7', 2, 7, 4), ('B8', 2, 8, 4),
('B9', 2, 9, 4), ('B10', 2, 10, 4), ('B11', 2, 11, 4), ('B12', 2, 12, 4),
('C1', 3, 1, 4), ('C2', 3, 2, 4), ('C3', 3, 3, 4), ('C4', 3, 4, 4),
('C5', 3, 5, 4), ('C6', 3, 6, 4), ('C7', 3, 7, 4), ('C8', 3, 8, 4),
('C9', 3, 9, 4), ('C10', 3, 10, 4), ('C11', 3, 11, 4), ('C12', 3, 12, 4),
('D1', 4, 1, 4), ('D2', 4, 2, 4), ('D3', 4, 3, 4), ('D4', 4, 4, 4),
('D5', 4, 5, 4), ('D6', 4, 6, 4), ('D7', 4, 7, 4), ('D8', 4, 8, 4),
('D9', 4, 9, 4), ('D10', 4, 10, 4), ('D11', 4, 11, 4), ('D12', 4, 12, 4),
('E1-E2', 5, 1, 4), ('E3-E4', 5, 3, 4), ('E5-E6', 5, 5, 4),
('E7-E8', 5, 7, 4), ('E9-E10', 5, 9, 4), ('E11-E12', 5, 11, 4),
('F1-F2', 6, 1, 4), ('F3-F4', 6, 3, 4), ('F5-F6', 6, 5, 4),
('F7-F8', 6, 7, 4), ('F9-F10', 6, 9, 4), ('F11-F12', 6, 11, 4);

-- Cinema 2 - Room 5 (Hall A)
INSERT INTO seats (name, seat_row, seat_column, room_id) VALUES
('A1', 1, 1, 5), ('A2', 1, 2, 5), ('A3', 1, 3, 5), ('A4', 1, 4, 5),
('A5', 1, 5, 5), ('A6', 1, 6, 5), ('A7', 1, 7, 5), ('A8', 1, 8, 5),
('A9', 1, 9, 5), ('A10', 1, 10, 5), ('A11', 1, 11, 5), ('A12', 1, 12, 5),
('B1', 2, 1, 5), ('B2', 2, 2, 5), ('B3', 2, 3, 5), ('B4', 2, 4, 5),
('B5', 2, 5, 5), ('B6', 2, 6, 5), ('B7', 2, 7, 5), ('B8', 2, 8, 5),
('B9', 2, 9, 5), ('B10', 2, 10, 5), ('B11', 2, 11, 5), ('B12', 2, 12, 5),
('C1', 3, 1, 5), ('C2', 3, 2, 5), ('C3', 3, 3, 5), ('C4', 3, 4, 5),
('C5', 3, 5, 5), ('C6', 3, 6, 5), ('C7', 3, 7, 5), ('C8', 3, 8, 5),
('C9', 3, 9, 5), ('C10', 3, 10, 5), ('C11', 3, 11, 5), ('C12', 3, 12, 5),
('D1', 4, 1, 5), ('D2', 4, 2, 5), ('D3', 4, 3, 5), ('D4', 4, 4, 5),
('D5', 4, 5, 5), ('D6', 4, 6, 5), ('D7', 4, 7, 5), ('D8', 4, 8, 5),
('D9', 4, 9, 5), ('D10', 4, 10, 5), ('D11', 4, 11, 5), ('D12', 4, 12, 5),
('E1-E2', 5, 1, 5), ('E3-E4', 5, 3, 5), ('E5-E6', 5, 5, 5),
('E7-E8', 5, 7, 5), ('E9-E10', 5, 9, 5), ('E11-E12', 5, 11, 5),
('F1-F2', 6, 1, 5), ('F3-F4', 6, 3, 5), ('F5-F6', 6, 5, 5),
('F7-F8', 6, 7, 5), ('F9-F10', 6, 9, 5), ('F11-F12', 6, 11, 5);

-- Cinema 2 - Room 6 (Hall B)
INSERT INTO seats (name, seat_row, seat_column, room_id) VALUES
('A1', 1, 1, 6), ('A2', 1, 2, 6), ('A3', 1, 3, 6), ('A4', 1, 4, 6),
('A5', 1, 5, 6), ('A6', 1, 6, 6), ('A7', 1, 7, 6), ('A8', 1, 8, 6),
('A9', 1, 9, 6), ('A10', 1, 10, 6), ('A11', 1, 11, 6), ('A12', 1, 12, 6),
('B1', 2, 1, 6), ('B2', 2, 2, 6), ('B3', 2, 3, 6), ('B4', 2, 4, 6),
('B5', 2, 5, 6), ('B6', 2, 6, 6), ('B7', 2, 7, 6), ('B8', 2, 8, 6),
('B9', 2, 9, 6), ('B10', 2, 10, 6), ('B11', 2, 11, 6), ('B12', 2, 12, 6),
('C1', 3, 1, 6), ('C2', 3, 2, 6), ('C3', 3, 3, 6), ('C4', 3, 4, 6),
('C5', 3, 5, 6), ('C6', 3, 6, 6), ('C7', 3, 7, 6), ('C8', 3, 8, 6),
('C9', 3, 9, 6), ('C10', 3, 10, 6), ('C11', 3, 11, 6), ('C12', 3, 12, 6),
('D1', 4, 1, 6), ('D2', 4, 2, 6), ('D3', 4, 3, 6), ('D4', 4, 4, 6),
('D5', 4, 5, 6), ('D6', 4, 6, 6), ('D7', 4, 7, 6), ('D8', 4, 8, 6),
('D9', 4, 9, 6), ('D10', 4, 10, 6), ('D11', 4, 11, 6), ('D12', 4, 12, 6),
('E1-E2', 5, 1, 6), ('E3-E4', 5, 3, 6), ('E5-E6', 5, 5, 6),
('E7-E8', 5, 7, 6), ('E9-E10', 5, 9, 6), ('E11-E12', 5, 11, 6),
('F1-F2', 6, 1, 6), ('F3-F4', 6, 3, 6), ('F5-F6', 6, 5, 6),
('F7-F8', 6, 7, 6), ('F9-F10', 6, 9, 6), ('F11-F12', 6, 11, 6);

-- Cinema 2 - Room 7 (Hall C)
INSERT INTO seats (name, seat_row, seat_column, room_id) VALUES
('A1', 1, 1, 7), ('A2', 1, 2, 7), ('A3', 1, 3, 7), ('A4', 1, 4, 7),
('A5', 1, 5, 7), ('A6', 1, 6, 7), ('A7', 1, 7, 7), ('A8', 1, 8, 7),
('A9', 1, 9, 7), ('A10', 1, 10, 7), ('A11', 1, 11, 7), ('A12', 1, 12, 7),
('B1', 2, 1, 7), ('B2', 2, 2, 7), ('B3', 2, 3, 7), ('B4', 2, 4, 7),
('B5', 2, 5, 7), ('B6', 2, 6, 7), ('B7', 2, 7, 7), ('B8', 2, 8, 7),
('B9', 2, 9, 7), ('B10', 2, 10, 7), ('B11', 2, 11, 7), ('B12', 2, 12, 7),
('C1', 3, 1, 7), ('C2', 3, 2, 7), ('C3', 3, 3, 7), ('C4', 3, 4, 7),
('C5', 3, 5, 7), ('C6', 3, 6, 7), ('C7', 3, 7, 7), ('C8', 3, 8, 7),
('C9', 3, 9, 7), ('C10', 3, 10, 7), ('C11', 3, 11, 7), ('C12', 3, 12, 7),
('D1', 4, 1, 7), ('D2', 4, 2, 7), ('D3', 4, 3, 7), ('D4', 4, 4, 7),
('D5', 4, 5, 7), ('D6', 4, 6, 7), ('D7', 4, 7, 7), ('D8', 4, 8, 7),
('D9', 4, 9, 7), ('D10', 4, 10, 7), ('D11', 4, 11, 7), ('D12', 4, 12, 7),
('E1-E2', 5, 1, 7), ('E3-E4', 5, 3, 7), ('E5-E6', 5, 5, 7),
('E7-E8', 5, 7, 7), ('E9-E10',
