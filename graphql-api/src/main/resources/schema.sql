DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS orders;

CREATE TABLE customer
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE orders
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    customerId INT,
    FOREIGN KEY (customerId) REFERENCES customer (id)
);
