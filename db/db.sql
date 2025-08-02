-- Create database
CREATE DATABASE IF NOT EXISTS bookticket;
USE bookticket;

-- Create tables
CREATE TABLE IF NOT EXISTS accounts (
    username VARCHAR(255) UNIQUE NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE
    );

CREATE TABLE IF NOT EXISTS customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),
    gender BOOLEAN,
    date_of_birth DATE,
    address TEXT,
    username VARCHAR(255),
    FOREIGN KEY (username) REFERENCES accounts(username)
    );

CREATE TABLE IF NOT EXISTS managers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),
    gender BOOLEAN,
    date_of_birth DATE,
    address TEXT,
    id_card VARCHAR(255),
    username VARCHAR(255),
    FOREIGN KEY (username) REFERENCES accounts(username)
    );

CREATE TABLE IF NOT EXISTS genres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS actors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    gender BOOLEAN,
    date_of_birth DATE,
    nationality VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    duration INT NOT NULL,
    language VARCHAR(100),
    poster VARCHAR(500),
    trailer VARCHAR(500),
    release_date DATE,
    );

CREATE TABLE IF NOT EXISTS movie_genres (
    movie_id INT,
    genre_id INT,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS movie_actors (
    movie_id INT,
    actor_id INT,
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE,
    FOREIGN KEY (actor_id) REFERENCES actors(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS cinemas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address TEXT NOT NULL,
    phone VARCHAR(20)
    );

CREATE TABLE IF NOT EXISTS rooms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    total_seats INT NOT NULL,
    cinema_id INT NOT NULL,
    FOREIGN KEY (cinema_id) REFERENCES cinemas(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS seats (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(10) NOT NULL,
    seat_row INT,
    seat_column INT,
    room_id INT NOT NULL,
    FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS showtimes (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         movie_id INT NOT NULL,
                                         room_id INT NOT NULL,
                                         start_time DATETIME NOT NULL,
                                         end_time DATETIME NOT NULL,
                                         ticket_price INT NOT NULL,
                                         FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS invoices (
                                        invoice_id INT AUTO_INCREMENT PRIMARY KEY,
                                        username VARCHAR(255) NOT NULL,
    total_amount INT NOT NULL,
    payment_status ENUM('PENDING', 'PAID', 'FAILED') DEFAULT 'PENDING',
    vnpay_transaction_id VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    paid_at DATETIME,
    FOREIGN KEY (username) REFERENCES accounts(username)
    );

CREATE TABLE IF NOT EXISTS tickets (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       showtime_id INT NOT NULL,
                                       seat_id INT NOT NULL,
                                       invoice_id INT NOT NULL,
                                       price INT NOT NULL,
                                       status BOOLEAN DEFAULT FALSE,
                                       qr_code VARCHAR(500) UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    scanned_at DATETIME,
    is_scanned BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (showtime_id) REFERENCES showtimes(id) ON DELETE CASCADE,
    FOREIGN KEY (seat_id) REFERENCES seats(id) ON DELETE CASCADE,
    FOREIGN KEY (invoice_id) REFERENCES invoices(invoice_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS invalidated_token (
                                                 id VARCHAR(255) PRIMARY KEY,
    expiry_time DATETIME
    );
