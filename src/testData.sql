-- Password : PasswordA123
-- encrypted using https://www.javainuse.com/onlineBcrypt

INSERT INTO user_tbl ( u_email, u_password, u_username, email_verified, deleted,u_is_identified,version_id)
VALUES ( 'UserA@junit.com', '$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2', 'UserA', true, false,false,0)
    , ( 'Admin@junit.com', '$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2', 'Admin', true, false,true,0)
     , ( 'UserB@junit.com', '$2a$10$TlYbg57fqOy/1LJjispkjuSIvFJXbh3fy0J9fvHnCpuntZOITAjVG', 'UserB', false , false,false,0)
     , ('UserC@junit.com', '$2a$10$SYiYAIW80gDh39jwSaPyiuKGuhrLi7xTUjocL..NOx/1COWe5P03.', 'UserC', false, false,false,0);

INSERT INTO role_tbl ( role_name,deleted,version_id)
VALUES ('user',false,0),
       ('admin',false,0);

INSERT INTO user_role (user_id, role_id)
VALUES (1,1),
       (2,1),
        (2,2);
# INSERT INTO address_tbl (a_address_line,a_postal_code, deleted,version_id)
# VALUES ('123 Tester Hill', '1234567890', false,0)
#      , ('312 Spring Boot', '1234567890', false,0);

# INSERT INTO person_tbl (p_first_name, p_last_name, p_phone_number, deleted,address_id,user_id,version_id)
# VALUES ('veze','vezeee','09365006724',false,1,2,0);


# INSERT INTO brand_tbl (b_name, deleted,version_id,b_explanation)
# VALUES ('lcwaikiki',0,0,'aaaaaaa'),
#        ('laboutin',0,0,'bbbbbbbb');

# INSERT INTO color_tbl (c_name,deleted,version_id,hex_code)
# VALUES ('blue',0,0,'00000000'),
#        ('red',0,0,'1111111'),
#        ('purple',0,0,'3333333');
#
# INSERT INTO product_tbl (p_code,p_name, p_description, deleted,brand_id,color_id,p_price,p_off_percent,p_material,p_pattern,p_height,version_id)
# VALUES ('1','Product1', 'This is a very long description of product #1.', false,1,1,10,0,'material','pattern',20,0)
#      , ('2','Product2', 'This is a very long description of product #2.' ,false,1,1,100,80,'material','pattern',30,0)
#      , ('3','Product3', 'This is a very long description of product #3.' ,false,1,2,1000,50,'material','pattern',40,0)
#      , ('4','Product4','This is a very long description of product #4.' ,false,2,2,10000,30,'material','pattern',50,0)
#      , ('5','Product5', 'This is a very long description of product #5.' ,false,2,3,100000,72,'material','pattern',60,0);

INSERT INTO category_tbl (c_name, deleted,version_id)
VALUES ('دیجی کالا',0,0),
       ('هودی',0,0),
       ('هودی زنانه',0,0),
       ('هودی مردانه',0,0),
       ('هودی یونیسکس',0,0);

# digikala
UPDATE category_tbl SET category_id=1 where id=2;

UPDATE category_tbl SET category_id=2 where id=3;

UPDATE category_tbl SET category_id=2 where id=4;

UPDATE category_tbl SET category_id=2 where id=5;



# INSERT INTO category_products_tbl (product_id, categories_id)
# VALUES (1,1),
#         (1,4),
#         (2,1);
#
# INSERT INTO inventory_tbl (product_id, i_quantity, deleted,product_size,version_id)
# VALUES (1, 5, false,0,0)
#      , (1,3,false,1,0)
#      , (2, 8, false,2,0)
#      , (3, 12, false,3,0)
#      , (4, 73, false,5,0)
#      , (5, 73, false,5,0);
