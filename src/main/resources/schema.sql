CREATE TABLE IF NOT EXISTS Customer(
    id INT PRIMARY KEY,
    name VARCHAR(100),
    phone BIGINT,
    email VARCHAR(100),
    address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Product (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(100) NOT NULL,
    price          DOUBLE NOT NULL,
    gst_percentage DOUBLE NOT NULL,
    stock_quantity INT NOT NULL
);

