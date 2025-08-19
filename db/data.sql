USE bookticket;

-- Insert Genres
INSERT INTO genres (name) VALUES
                              ('Action'),
                              ('Comedy'),
                              ('Drama'),
                              ('Horror'),
                              ('Romance'),
                              ('Sci-Fi'),
                              ('Thriller'),
                              ('Adventure'),
                              ('Animation'),
                              ('Crime'),
                              ('Fantasy'),
                              ('Mystery'),
                              ('War'),
                              ('Documentary'),
                              ('Musical');

-- Insert Actors
INSERT INTO actors (first_name, last_name, gender, date_of_birth, nationality) VALUES
                                                                                   ('Robert', 'Downey Jr.', TRUE, '1965-04-04', 'American'),
                                                                                   ('Scarlett', 'Johansson', FALSE, '1984-11-22', 'American'),
                                                                                   ('Chris', 'Evans', TRUE, '1981-06-13', 'American'),
                                                                                   ('Jennifer', 'Lawrence', FALSE, '1990-08-15', 'American'),
                                                                                   ('Leonardo', 'DiCaprio', TRUE, '1974-11-11', 'American'),
                                                                                   ('Emma', 'Stone', FALSE, '1988-11-06', 'American'),
                                                                                   ('Ryan', 'Gosling', TRUE, '1980-11-12', 'Canadian'),
                                                                                   ('Margot', 'Robbie', FALSE, '1990-07-02', 'Australian'),
                                                                                   ('Tom', 'Hanks', TRUE, '1956-07-09', 'American'),
                                                                                   ('Meryl', 'Streep', FALSE, '1949-06-22', 'American'),
                                                                                   ('Brad', 'Pitt', TRUE, '1963-12-18', 'American'),
                                                                                   ('Angelina', 'Jolie', FALSE, '1975-06-04', 'American'),
                                                                                   ('Will', 'Smith', TRUE, '1968-09-25', 'American'),
                                                                                   ('Sandra', 'Bullock', FALSE, '1964-07-26', 'American'),
                                                                                   ('Denzel', 'Washington', TRUE, '1954-12-28', 'American'),
                                                                                   ('Julia', 'Roberts', FALSE, '1967-10-28', 'American'),
                                                                                   ('Johnny', 'Depp', TRUE, '1963-06-09', 'American'),
                                                                                   ('Anne', 'Hathaway', FALSE, '1982-11-12', 'American'),
                                                                                   ('Matt', 'Damon', TRUE, '1970-10-08', 'American'),
                                                                                   ('Natalie', 'Portman', FALSE, '1981-06-09', 'Israeli-American'),
                                                                                   ('Christian', 'Bale', TRUE, '1974-01-30', 'British'),
                                                                                   ('Amy', 'Adams', FALSE, '1974-08-20', 'American'),
                                                                                   ('Hugh', 'Jackman', TRUE, '1968-10-12', 'Australian'),
                                                                                   ('Charlize', 'Theron', FALSE, '1975-08-07', 'South African'),
                                                                                   ('Ryan', 'Reynolds', TRUE, '1976-10-23', 'Canadian');

-- Insert Movies
INSERT INTO movies (title, description, duration, language, poster, trailer, release_date) VALUES
                                                                                               ('Avengers: Endgame', 'The Avengers assemble once more to reverse the damage caused by Thanos.', 181, 'English', 'https://m.media-amazon.com/images/I/91-UCbbhoiL._AC_SL1500_.jpg', 'https://www.youtube.com/watch?v=hA6hldpSTF8', '2019-04-26'),
                                                                                               ('The Dark Knight', 'Batman faces the Joker in this critically acclaimed superhero film.', 152, 'English', 'https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_FMjpg_UX1000_.jpg', 'https://www.youtube.com/watch?v=EXeTwQWrcwY', '2008-07-18'),
                                                                                               ('Inception', 'A thief enters people\'s dreams to steal secrets from their subconscious.', 148, 'English', 'https://images-na.ssl-images-amazon.com/images/I/71uKM+LdgFL.jpg', 'https://www.youtube.com/watch?v=YoHD9XEInc0', '2010-07-16'),
('Titanic', 'A seventeen-year-old aristocrat falls in love with a kind but poor artist.', 194, 'English', 'https://m.media-amazon.com/images/I/71V3V0FE1gS._AC_SL1200_.jpg', 'https://www.youtube.com/watch?v=kVrqfYjkTdQ', '1997-12-19'),
('The Shawshank Redemption', 'Two imprisoned men bond over years, finding solace and redemption.', 142, 'English', 'https://m.media-amazon.com/images/I/71JxA6I+sgL._AC_SL1000_.jpg', 'https://www.youtube.com/watch?v=PLl99DlL6b4', '1994-09-23'),
('Forrest Gump', 'The presidencies of Kennedy and Johnson through the eyes of an Alabama man.', 142, 'English', 'https://m.media-amazon.com/images/M/MV5BNDYwNzVjMTItZmU5YS00YjQ5LTljYjgtMjY2NDVmYWMyNWFmXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg', 'https://www.youtube.com/watch?v=bLvqoHBptjg', '1994-07-06'),
('The Matrix', 'A computer hacker learns about the true nature of his reality.', 136, 'English', 'https://m.media-amazon.com/images/I/71PfZFFz9yL._AC_SL1000_.jpg', 'https://www.youtube.com/watch?v=vKQi3bBA1y8', '1999-03-31'),
('Pulp Fiction', 'The lives of two mob hitmen, a boxer, and others intertwine.', 154, 'English', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScbd4vnne7REqYc1wzVKOK4KsWYmTqNQt2PQ&s', 'https://www.youtube.com/watch?v=s7EdQ4FqbhY', '1994-10-14'),
('The Godfather', 'The aging patriarch of a crime dynasty transfers control to his son.', 175, 'English', 'https://m.media-amazon.com/images/M/MV5BNGEwYjgwOGQtYjg5ZS00Njc1LTk2ZGEtM2QwZWQ2NjdhZTE5XkEyXkFqcGc@._V1_.jpg', 'https://www.youtube.com/watch?v=UaVTIH8mujA', '1972-03-24'),
('Schindler\'s List', 'In German-occupied Poland, Schindler saves over a thousand Jewish lives.', 195, 'English', 'https://m.media-amazon.com/images/M/MV5BNjM1ZDQxYWUtMzQyZS00MTE1LWJmZGYtNGUyNTdlYjM3ZmVmXkEyXkFqcGc@._V1_.jpg', 'https://www.youtube.com/watch?v=mxphAlJID9U', '1993-12-15'),
                                                                                               ('Interstellar', 'A team of explorers travel through a wormhole in space.', 169, 'English', 'https://m.media-amazon.com/images/I/714iHb8BQ3L._AC_SL1500_.jpg', 'https://www.youtube.com/watch?v=zSWdZVtXT7E', '2014-11-07'),
                                                                                               ('The Lion King', 'A young lion prince flees his kingdom after his father\'s murder.', 88, 'English', 'https://cdn.europosters.eu/image/1300/76297.jpg', 'https://www.youtube.com/watch?v=7TavVZMewpY', '1994-06-24'),
('Gladiator', 'A former Roman General seeks vengeance against the emperor.', 155, 'English', 'https://m.media-amazon.com/images/M/MV5BYWQ4YmNjYjEtOWE1Zi00Y2U4LWI4NTAtMTU0MjkxNWQ1ZmJiXkEyXkFqcGc@._V1_.jpg', 'https://www.youtube.com/watch?v=P5ieIbInFpg', '2000-05-05'),
('Avatar', 'A paraplegic Marine is sent to the moon Pandora on a unique mission.', 162, 'English', 'https://images-cdn.ubuy.ae/6410d97025486d446f7d1332-avatar-the-way-of-water-movie-poster-28.jpg', 'https://www.youtube.com/watch?v=5PSNL1qE6VY', '2009-12-18'),
('The Departed', 'An undercover cop and a police informant try to identify each other.', 151, 'English', 'https://www.originalfilmart.com/cdn/shop/products/departed_2006_original_film_art_921946c9-5eaa-43a5-9425-2e13cb2de4ac_600x.jpg?v=1679940744', 'https://www.youtube.com/watch?v=iojhqm0JTW4', '2006-10-06'),
('Fight Club', 'An insomniac office worker forms an underground fight club.', 139, 'English', 'https://m.media-amazon.com/images/M/MV5BOTgyOGQ1NDItNGU3Ny00MjU3LTg2YWEtNmEyYjBiMjI1Y2M5XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg', 'https://www.youtube.com/watch?v=qtRKdVHc-cE', '1999-10-15'),
('Goodfellas', 'The story of Henry Hill and his life in the mob.', 146, 'English', 'https://m.media-amazon.com/images/M/MV5BN2E5NzI2ZGMtY2VjNi00YTRjLWI1MDUtZGY5OWU1MWJjZjRjXkEyXkFqcGc@._V1_.jpg', 'https://www.youtube.com/watch?v=2ilzidi_J8Q', '1990-09-21'),
('The Silence of the Lambs', 'A young FBI cadet seeks help from the imprisoned Dr. Hannibal Lecter.', 118, 'English', 'https://m.media-amazon.com/images/M/MV5BNDdhOGJhYzctYzYwZC00YmI2LWI0MjctYjg4ODdlMDExYjBlXkEyXkFqcGc@._V1_.jpg', 'https://www.youtube.com/watch?v=6iB21hsprAQ', '1991-02-14'),
('Saving Private Ryan', 'Following D-Day, a group of soldiers search for a paratrooper.', 169, 'English', 'https://m.media-amazon.com/images/M/MV5BZGZhZGQ1ZWUtZTZjYS00MDJhLWFkYjctN2ZlYjE5NWYwZDM2XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg', 'https://www.youtube.com/watch?v=S1Qj_AVu2pA', '1998-07-24'),
('The Green Mile', 'The lives of guards on Death Row are affected by one of their charges.', 189, 'English', 'https://m.media-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1_FMjpg_UX1000_.jpg', 'https://www.youtube.com/watch?v=Ki4haFrqSrw', '1999-12-10');

-- Insert Movie-Genre relationships
INSERT INTO movie_genres (movie_id, genre_id) VALUES
-- Avengers: Endgame (Action, Adventure, Sci-Fi)
(1, 1), (1, 8), (1, 6),
-- The Dark Knight (Action, Crime, Drama)
(2, 1), (2, 10), (2, 3),
-- Inception (Action, Sci-Fi, Thriller)
(3, 1), (3, 6), (3, 7),
-- Titanic (Drama, Romance)
(4, 3), (4, 5),
-- The Shawshank Redemption (Drama)
(5, 3),
-- Forrest Gump (Drama, Romance)
(6, 3), (6, 5),
-- The Matrix (Action, Sci-Fi)
(7, 1), (7, 6),
-- Pulp Fiction (Crime, Drama)
(8, 10), (8, 3),
-- The Godfather (Crime, Drama)
(9, 10), (9, 3),
-- Schindler's List (Drama, War)
(10, 3), (10, 13),
-- Interstellar (Drama, Sci-Fi)
                                                                                                (11, 3), (11, 6),
-- The Lion King (Animation, Adventure)
                                                                                                (12, 9), (12, 8),
-- Gladiator (Action, Adventure, Drama)
                                                                                                (13, 1), (13, 8), (13, 3),
-- Avatar (Action, Adventure, Sci-Fi)
                                                                                                (14, 1), (14, 8), (14, 6),
-- The Departed (Crime, Drama, Thriller)
                                                                                                (15, 10), (15, 3), (15, 7),
-- Fight Club (Drama)
                                                                                                (16, 3),
-- Goodfellas (Crime, Drama)
                                                                                                (17, 10), (17, 3),
-- The Silence of the Lambs (Crime, Horror, Thriller)
                                                                                                (18, 10), (18, 4), (18, 7),
-- Saving Private Ryan (Drama, War)
                                                                                                (19, 3), (19, 13),
-- The Green Mile (Crime, Drama, Fantasy)
                                                                                                (20, 10), (20, 3), (20, 11);

-- Insert Movie-Actor relationships
INSERT INTO movie_actors (movie_id, actor_id) VALUES
-- Avengers: Endgame
(1, 1), (1, 2), (1, 3),
-- The Dark Knight
(2, 21), (2, 17),
-- Inception
(3, 5), (3, 18),
-- Titanic
(4, 5), (4, 6),
-- The Shawshank Redemption
(5, 9), (5, 15),
-- Forrest Gump
(6, 9), (6, 16),
-- The Matrix
(7, 13), (7, 4),
-- Pulp Fiction
(8, 11), (8, 13),
-- The Godfather
(9, 11), (9, 17),
-- Schindler's List
(10, 19), (10, 10),
-- Interstellar
(11, 19), (11, 18),
-- The Lion King (Animation - voice actors)
(12, 17), (12, 13),
-- Gladiator
(13, 15), (13, 12),
-- Avatar
(14, 14), (14, 24),
-- The Departed
(15, 5), (15, 19),
-- Fight Club
(16, 11), (16, 7),
-- Goodfellas
(17, 1), (17, 15),
-- The Silence of the Lambs
(18, 16), (18, 17),
-- Saving Private Ryan
(19, 9), (19, 19),
-- The Green Mile
(20, 9), (20, 15);

-- Insert Cinemas
INSERT INTO cinemas (name, address, phone) VALUES
                                               ('CGV Vincom Center', '191 Ba Trieu, Hai Ba Trung, Ha Noi', '024-3974-3333'),
                                               ('Lotte Cinema Landmark', '5B Nguyen Dinh Chieu, District 3, Ho Chi Minh City', '028-3930-0510'),
                                               ('Galaxy Cinema Nguyen Du', '116 Nguyen Du, District 1, Ho Chi Minh City', '1900-2224'),
                                               ('BHD Star Cineplex', '3/2 Street, District 10, Ho Chi Minh City', '1900-2099');

-- Insert Rooms for each Cinema
INSERT INTO rooms (name, total_seats, cinema_id) VALUES
-- CGV Vincom Center (Cinema 1)
('Screen 1', 56, 1),
('Screen 2', 56, 1),
('Screen 3', 56, 1),
('Screen 4', 56, 1),
('Screen 5', 56, 1),
-- Lotte Cinema Landmark (Cinema 2)
('Hall A', 56, 2),
('Hall B', 56, 2),
('Hall C', 56, 2),
('Hall D', 56, 2),
('Hall E', 56, 2),
('Hall F', 56, 2),
-- Galaxy Cinema Nguyen Du (Cinema 3)
('Theater 1', 56, 3),
('Theater 2', 56, 3),
('Theater 3', 56, 3),
('Theater 4', 56, 3),
-- BHD Star Cineplex (Cinema 4)
('Cinema 1', 56, 4),
('Cinema 2', 56, 4),
('Cinema 3', 56, 4),
('Cinema 4', 56, 4),
('Cinema 5', 56, 4);
