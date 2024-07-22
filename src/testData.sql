-- Password : PasswordA123
-- encrypted using https://www.javainuse.com/onlineBcrypt

INSERT INTO user_tbl ( u_email, u_password, u_username, email_verified, deleted)
VALUES ( 'UserA@junit.com', '$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2', 'UserA', true, false)
    , ( 'Admin@junit.com', '$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2', 'Admin', true, false)
     , ( 'UserB@junit.com', '$2a$10$TlYbg57fqOy/1LJjispkjuSIvFJXbh3fy0J9fvHnCpuntZOITAjVG', 'UserB', false , false)
     , ('UserC@junit.com', '$2a$10$SYiYAIW80gDh39jwSaPyiuKGuhrLi7xTUjocL..NOx/1COWe5P03.', 'UserC', false, false);

INSERT INTO role_tbl ( role_name,deleted)
VALUES ('user',false),
       ('admin',false);

INSERT INTO user_role (user_id, role_id)
VALUES (1,1),
       (2,2);


INSERT INTO address_tbl (address_line_1, city, country, user_id, deleted)
VALUES ('123 Tester Hill', 'Testerton', 'England', 1, false)
     , ('312 Spring Boot', 'Hibernate', 'England', 3, false);

INSERT INTO brand_tbl (b_name, deleted)
VALUES ('lcwaikiki',0),
       ('laboutin',0);

INSERT INTO color_tbl (c_name,deleted)
VALUES ('blue',0),
       ('red',0),
       ('purple',0);

INSERT INTO product_tbl (p_code,p_name, short_description, long_description, deleted,brand_id,color_id,p_price,p_off_percent)
VALUES ('1','Product1', 'Product one short description.', 'This is a very long description of product #1.', false,1,1,10,0)
     , ('2','Product2', 'Product two short description.', 'This is a very long description of product #2.' ,false,1,1,100,80)
     , ('3','Product3', 'Product three short description.', 'This is a very long description of product #3.' ,false,1,2,1000,50)
     , ('4','Product4', 'Product four short description.', 'This is a very long description of product #4.' ,false,2,2,10000,30)
     , ('5','Product5', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('6','Product6', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('7','Product7', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('8','Product8', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('9','Product9', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('10','Product10', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('11','Product11', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('12','Product12', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('13','Product13', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('14','Product14', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('15','Product15', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('16','Product16', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('17','Product17', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('18','Product18', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('19','Product19', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('20','Product20', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('21','Product21', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('22','Product22', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('23','Product23', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('24','Product24', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('25','Product25', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('26','Product26', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72)
     , ('27','Product27', 'Product five short description.', 'This is a very long description of product #5.' ,false,2,3,100000,72);

INSERT INTO category_tbl (c_name, deleted)
VALUES ('digikala',0),
       ('men',0),
       ('woman',0),
       ('unisex',0),
       ('shortsMen',0),
       ('shortsWomen',0),
       ('tShirt',0),
       ('winterShortsMen',0),
       ('winterShortsWomen',0);

# men and shorts
UPDATE category_tbl SET category_id=1 where id=2;

UPDATE category_tbl SET category_id=1 where id=3;

UPDATE category_tbl SET category_id=2 where id=5;

UPDATE category_tbl SET category_id=3 where id=6;

UPDATE category_tbl SET category_id=5 where id=8;

UPDATE category_tbl SET category_id=6 where id=9;


INSERT INTO category_products_tbl (product_id, categories_id)
VALUES (1,1),
        (1,4),
        (2,1);

INSERT INTO inventory_tbl (product_id, i_quantity, deleted,product_size)
VALUES (1, 5, false,0)
     , (1,3,false,1)
     , (2, 8, false,2)
     , (3, 12, false,3)
     , (4, 73, false,5);

INSERT INTO order_tbl (address_id, user_id, deleted,date_created,order_status)
VALUES (1, 1, false,localtime,0)
     , (1, 1, false,localtime,0)
     , (1, 1, false,localtime,0)
     , (2, 3, false,localtime,0)
     , (2, 3, false,localtime,0);

INSERT INTO order_inventory_tbl (order_id, inventory_id, quantity, deleted)
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