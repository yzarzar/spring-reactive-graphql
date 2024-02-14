CREATE TABLE book
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    title  VARCHAR(225) NOT NULL,
    author VARCHAR(225) NOT NULL,
    price  FLOAT        NOT NULL
);