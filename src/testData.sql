-- Password : PasswordA123
-- encrypted using https://www.javainuse.com/onlineBcrypt

INSERT INTO user_tbl ( u_email, u_first_name, u_last_name, u_password, u_username, email_verified, deleted)
VALUES ( 'UserA@junit.com', 'UserAFirstName', 'UserALastName', '$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2', 'UserA', true, false)
     , ( 'UserB@junit.com', 'UserBFirstName', 'UserBLastName', '$2a$10$TlYbg57fqOy/1LJjispkjuSIvFJXbh3fy0J9fvHnCpuntZOITAjVG', 'UserB', false , false)
     , ('UserC@junit.com', 'UserCFirstName', 'UserCLastName', '$2a$10$SYiYAIW80gDh39jwSaPyiuKGuhrLi7xTUjocL..NOx/1COWe5P03.', 'UserC', false, false);


INSERT INTO address_tbl (address_line_1, city, country, user_id, deleted)
VALUES ('123 Tester Hill', 'Testerton', 'England', 1, false)
     , ('312 Spring Boot', 'Hibernate', 'England', 3, false);

INSERT INTO product_tbl (p_name, short_description, long_description, p_price, deleted)
VALUES ('Product #1', 'Product one short description.', 'This is a very long description of product #1.', 5.50, false)
     , ('Product #2', 'Product two short description.', 'This is a very long description of product #2.', 10.56 ,false)
     , ('Product #3', 'Product three short description.', 'This is a very long description of product #3.', 2.74 ,false)
     , ('Product #4', 'Product four short description.', 'This is a very long description of product #4.', 15.69 ,false)
     , ('Product #5', 'Product five short description.', 'This is a very long description of product #5.', 42.59 ,false);

INSERT INTO inventory_tbl (product_id, quantity, deleted)
VALUES (1, 5, false)
     , (2, 8, false)
     , (3, 12, false)
     , (4, 73, false)
     , (5, 2, false);

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