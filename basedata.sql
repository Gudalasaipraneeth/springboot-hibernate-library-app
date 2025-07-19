# SQL configs
SET SQL_MODE ='IGNORE_SPACE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

# create database and use it
CREATE DATABASE IF NOT EXISTS librarydb;
USE librarydb;

# create the genre table
CREATE TABLE IF NOT EXISTS GENRE(
genre_id int unique key not null auto_increment primary key,
name        varchar(255) null
);

# insert default genres
INSERT INTO GENRE(name) VALUES ('Fiction'),
                              ('Non-Fiction'),
                              ('Science'),
                              ('History'),
                              ('Biography'),
                              ('Children'),
                              ('Fantasy'),
                              ('Mystery'),
                              ('Other');

# create the member table
CREATE TABLE IF NOT EXISTS MEMBER(
id       int unique key not null auto_increment primary key,
address  varchar(255) null,
email    varchar(255) null,
password varchar(255) null,
role     varchar(255) null,
username varchar(255) null,
UNIQUE (username)
);

# insert default members
INSERT INTO MEMBER(address, email, password, role, username) VALUES
                                                               ('123, Albany Street', 'admin@library.com', '123', 'ROLE_ADMIN', 'admin'),
                                                               ('765, 5th Avenue', 'lisa@gmail.com', '765', 'ROLE_NORMAL', 'lisa');

# create the book table
CREATE TABLE IF NOT EXISTS BOOK(
book_id  int unique key not null auto_increment primary key,
description varchar(255) null,
image       varchar(255) null,
name        varchar(255) null,
price       int null,
quantity    int null,
weight      int null,
genre_id int null,
member_id int null
);

# insert default books
INSERT INTO BOOK(description, image, name, price, quantity, weight, genre_id) VALUES
    ('A classic novel', 'https://example.com/book1.jpg', 'Pride and Prejudice', 10, 5, 300, 1),
    ('A science book', 'https://example.com/book2.jpg', 'Brief History of Time', 15, 3, 250, 3);

# create indexes
CREATE INDEX FK_genre_id
    ON BOOK (genre_id);

CREATE INDEX FK_member_id
    ON BOOK (member_id);