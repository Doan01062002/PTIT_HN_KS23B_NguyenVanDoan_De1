create database hackathon_web_java;

use hackathon_web_java;

create table Category(
    category_id int not null auto_increment primary key,
    category_name varchar(50) not null unique,
    description text,
    status bit default(1)
);

create table Product(
    product_id int not null auto_increment primary key,
    product_name varchar(100) not null,
    description text,
    price double not null check ( price > 0 ),
    image_url varchar(255),
    category_id int not null,
    status bit default(1),
    created_at datetime,
    foreign key (category_id) references Category(category_id)
);

DELIMITER //
create procedure insert_product(
    in p_product_name varchar(100),
    in p_description text,
    in p_price double,
    in p_image_url varchar(255),
    in p_category_id int
)
begin
    insert into Product(product_name, description, price, image_url, category_id, created_at)
    values (p_product_name, p_description, p_price, p_image_url, p_category_id, now());
end;

create procedure update_product(
    in p_product_id int,
    in p_product_name varchar(100),
    in p_description text,
    in p_price double,
    in p_image_url varchar(255),
    in p_category_id int)
begin
    update Product
    set product_name = p_product_name,
        description = p_description,
        price = p_price,
        image_url = p_image_url,
        category_id = p_category_id
    where product_id = p_product_id;
end;

create procedure delete_product(
    in p_product_id int)
begin
    delete from Product
    where product_id = p_product_id;
end;

DELIMITER //


-- Category Management Procedures
DELIMITER //
create procedure insert_category(
    in p_category_name varchar(50),
    in p_description text
)
begin
    insert into Category(category_name, description)
    values (p_category_name, p_description);
end;

create procedure update_category(
    in p_category_id int,
    in p_category_name varchar(50),
    in p_description text)
begin
    update Category
    set category_name = p_category_name,
        description = p_description
    where category_id = p_category_id;
end;

create procedure delete_category(
    in p_category_id int)
begin
    delete from Category
    where category_id = p_category_id;
end;
DELIMITER //