-- Password : PasswordA123
-- encrypted using https://www.javainuse.com/onlineBcrypt

INSERT INTO user_tbl ( u_email, u_first_name, u_last_name, u_password, u_username, email_verified,u_phone_number, deleted)
VALUES ( 'UserA@junit.com', 'UserAFirstName', 'UserALastName', '$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2', 'UserA', true,09123858573, false)
    , ( 'Admin@junit.com', 'AdminFirstName', 'AdminLastName', '$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2', 'Admin', true,09123858573, false)
     , ( 'UserB@junit.com', 'UserBFirstName', 'UserBLastName', '$2a$10$TlYbg57fqOy/1LJjispkjuSIvFJXbh3fy0J9fvHnCpuntZOITAjVG', 'UserB', false,09123858573 , false)
     , ('UserC@junit.com', 'UserCFirstName', 'UserCLastName', '$2a$10$SYiYAIW80gDh39jwSaPyiuKGuhrLi7xTUjocL..NOx/1COWe5P03.', 'UserC', false,09123858573, false);

INSERT INTO role_tbl ( role_name)
VALUES ('user'),
       ('admin');

INSERT INTO user_role (user_id, role_id)
VALUES (1,1),
       (2,2);


INSERT INTO address_tbl (address_line_1, city, country, user_id, deleted)
VALUES ('123 Tester Hill', 'Testerton', 'England', 1, false)
     , ('312 Spring Boot', 'Hibernate', 'England', 3, false);

INSERT INTO brand_tbl (b_name, deleted)
VALUES ('lcwaikiki',0),
       ('laboutin',0);

INSERT INTO product_tbl (p_name, short_description, long_description, deleted,brand_id)
VALUES ('Product #1', 'Product one short description.', 'This is a very long description of product #1.', false,1)
     , ('Product #2', 'Product two short description.', 'This is a very long description of product #2.' ,false,1)
     , ('Product #3', 'Product three short description.', 'This is a very long description of product #3.' ,false,1)
     , ('Product #4', 'Product four short description.', 'This is a very long description of product #4.' ,false,2)
     , ('Product #5', 'Product five short description.', 'This is a very long description of product #5.' ,false,2);

INSERT INTO category_tbl (c_name, deleted)
VALUES ('men',0),
       ('woman',0),
       ('unisex',0),
       ('shorts',0),
       ('tShirt',0),
       ('winterShorts',0);

# men and shorts
UPDATE category_tbl SET category_id=1 where id=4;

UPDATE category_tbl SET category_id=4 where id=6;


INSERT INTO category_products_tbl (product_id, categories_id)
VALUES (1,1),
        (1,4),
        (2,1);

INSERT INTO color_tbl (c_name,deleted)
VALUES ('blue',0),
        ('red',0),
        ('purple',0);

INSERT INTO inventory_tbl (product_id, i_quantity, deleted, color_id, i_price, i_off_percent)
VALUES (1, 5, false,1, 6, 90)
     , (1,3,false,2, 8, 78)
     , (2, 8, false,1, 90, 20)
     , (3, 12, false,1, 78, 0)
     , (4, 73, false,1, 56, 0)
     , (5, 2, false,1, 53, 10);

INSERT INTO order_tbl (address_id, user_id, deleted)
VALUES (1, 1, false)
     , (1, 1, false)
     , (1, 1, false)
     , (2, 3, false)
     , (2, 3, false);

INSERT INTO order_quantities_tbl (order_id, product_id, quantity, deleted)
VALUES (1, 1, 5, false)
     , (1, 2, 5, false)
     , (2, 3, 5, false)
     , (2, 2, 5, false)
     , (2, 5, 5, false)
     , (3, 3, 5, false)
     , (4, 4, 5, false)
     , (4, 2, 5, false)
     , (5, 3, 5, false)
     , (5, 1, 5, false);