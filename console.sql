create
database hackathon_web_java;

use
hackathon_web_java;

CREATE TABLE category
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    status      BOOLEAN   DEFAULT TRUE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100)   NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    image       VARCHAR(255),
    category_id INT,
    status      BOOLEAN   DEFAULT TRUE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES category (id)
);

DELIMITER
//

CREATE PROCEDURE get_all_categories()
BEGIN
SELECT *
FROM category
ORDER BY created_at DESC;
END
//

CREATE PROCEDURE get_category_by_id(IN p_id INT)
BEGIN
SELECT *
FROM category
WHERE id = p_id;
END
//

CREATE PROCEDURE add_category(IN p_name VARCHAR (100), IN p_description TEXT, IN p_status BOOLEAN)
BEGIN
INSERT INTO category (name, description, status)
VALUES (p_name, p_description, p_status);
END
//

CREATE PROCEDURE update_category(IN p_id INT, IN p_name VARCHAR (100), IN p_description TEXT, IN p_status BOOLEAN)
BEGIN
UPDATE category
SET name        = p_name,
    description = p_description,
    status      = p_status
WHERE id = p_id;
END
//

CREATE PROCEDURE delete_category(IN p_id INT)
BEGIN
DELETE
FROM category
WHERE id = p_id;
END
//

CREATE PROCEDURE search_categories(IN p_name VARCHAR (100))
BEGIN
SELECT *
FROM category
WHERE name LIKE p_name
ORDER BY created_at DESC;
END
//

CREATE PROCEDURE check_category_products(IN p_category_id INT)
BEGIN
SELECT COUNT(*) AS product_count
FROM product
WHERE category_id = p_category_id;
END
//

CREATE PROCEDURE get_all_products(IN p_offset INT, IN p_limit INT)
BEGIN
SELECT p.*, c.name AS category_name
FROM product p
         JOIN category c ON p.category_id = c.id
ORDER BY p.created_at DESC LIMIT p_offset, p_limit;
END
//

CREATE PROCEDURE get_total_products()
BEGIN
SELECT COUNT(*) AS total
FROM product;
END
//

CREATE PROCEDURE get_product_by_id(IN p_id INT)
BEGIN
SELECT p.*, c.name AS category_name
FROM product p
         JOIN category c ON p.category_id = c.id
WHERE p.id = p_id;
END
//

CREATE PROCEDURE add_product(IN p_name VARCHAR (100), IN p_description TEXT, IN p_price DECIMAL (10,2),
                             IN p_image VARCHAR (255), IN p_category_id INT, IN p_status BOOLEAN)
BEGIN
INSERT INTO product (name, description, price, image, category_id, status)
VALUES (p_name, p_description, p_price, p_image, p_category_id, p_status);
END
//

CREATE PROCEDURE update_product(IN p_id INT, IN p_name VARCHAR (100), IN p_description TEXT, IN p_price DECIMAL (10,2),
                                IN p_image VARCHAR (255), IN p_category_id INT, IN p_status BOOLEAN)
BEGIN
UPDATE product
SET name        = p_name,
    description = p_description,
    price       = p_price,
    image       = COALESCE(p_image, image),
    category_id = p_category_id,
    status      = p_status
WHERE id = p_id;
END
//

CREATE PROCEDURE delete_product(IN p_id INT)
BEGIN
DELETE
FROM product
WHERE id = p_id;
END
//

CREATE PROCEDURE search_products(IN p_name VARCHAR (100))
BEGIN
SELECT p.*, c.name AS category_name
FROM product p
         JOIN category c ON p.category_id = c.id
WHERE p.name LIKE p_name
ORDER BY p.created_at DESC;
END
//

DELIMITER ;