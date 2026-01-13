CREATE TABLE IF NOT EXISTS Customer(
    id INT PRIMARY KEY,
    name VARCHAR(100),
    phone BIGINT,
    email VARCHAR(100),
    address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    gst_percentage DOUBLE NOT NULL,
    stock_quantity INT NOT NULL
);

CREATE TABLE IF NOT EXISTS invoice (
    invoice_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    invoice_date DATE NOT NULL,
    customer_id INT NOT NULL,
    total_amount DOUBLE NOT NULL,
    total_tax DOUBLE NOT NULL,
    discount DOUBLE NOT NULL,
    final_amount DOUBLE NOT NULL,
    CONSTRAINT fk_invoice_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE IF NOT EXISTS invoice_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    invoice_id BIGINT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    tax_amount DOUBLE NOT NULL,
    total DOUBLE NOT NULL,
    discount DOUBLE NOT NULL,
    CONSTRAINT fk_invoice_item_invoice
    FOREIGN KEY (invoice_id) REFERENCES invoice(invoice_id),
    CONSTRAINT fk_invoice_item_product
    FOREIGN KEY (product_id) REFERENCES product(id)
);