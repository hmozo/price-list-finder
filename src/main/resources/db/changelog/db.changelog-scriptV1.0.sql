
CREATE TABLE brands (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE prices (
    id BIGINT PRIMARY KEY AUTO_INCREMENT ,
    brand_id INT NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    price_list_id BIGINT NOT NULL UNIQUE,
    product_id BIGINT NOT NULL,
    priority INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brands(id)
);

CREATE INDEX idx_start_date_end_date_product_id_brand_id
ON prices (start_date, end_date, product_id, brand_id);

