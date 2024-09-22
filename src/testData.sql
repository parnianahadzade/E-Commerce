-- Password : PasswordA123
-- encrypted using https://www.javainuse.com/onlineBcrypt
INSERT INTO person_tbl (p_first_name, p_last_name, p_phone_number, deleted, p_address_line, p_postal_code)
VALUES ('ادمین','ادمین','09365006724',false,'دهکده المپیک ، بلوار زیبادشت ، خ لاله',1489623836);

INSERT INTO user_tbl ( u_email, u_password, u_username, email_verified, deleted,u_is_identified,version_id, person_id)
VALUES ( 'UserA@junit.com', '$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2', 'UserA', true, false,false,0, null)
    , ( 'Admin@junit.com', '$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2', 'Admin', true, false,true,0,1)
     , ( 'UserB@junit.com', '$2a$10$TlYbg57fqOy/1LJjispkjuSIvFJXbh3fy0J9fvHnCpuntZOITAjVG', 'UserB', false , false,false,0, null)
     , ('UserC@junit.com', '$2a$10$SYiYAIW80gDh39jwSaPyiuKGuhrLi7xTUjocL..NOx/1COWe5P03.', 'UserC', false, false,false,0, null);

INSERT INTO role_tbl ( role_name,deleted,version_id)
VALUES ('user',false,0),
       ('admin',false,0);

INSERT INTO user_role (user_id, role_id)
VALUES (1,1),
       (2,1),
        (2,2);

INSERT INTO brand_tbl (b_name, deleted,version_id,b_explanation)
VALUES ('ال سی وایکیکی',0,0,'این توضیحات برند است.'),
       ('لابیوتن',0,0,'این توضیحات برند است.'),
       ('وای اس ال',0,0,'این توضیحات برند است.'),
       ('نایک',0,0,'این توضیحات برند است.'),
       ('اسمارا',0,0,'این توضیحات برند است.');

INSERT INTO color_tbl (c_name,deleted,version_id,hex_code)
VALUES ('آبی پر رنگ',0,0,'#0c30cf'),
       ('آبی روشن',0,0,'#23cdd9'),
       ('بنفش',0,0,'#7f23db'),
       ('کرمی',0,0,'#e1dee3'),
       ('مشکی',0,0,'#212021'),
       ('سبز زارا',0,0,'#0e8c25'),
       ('زرد',0,0,'#e4e80c'),
       ('قرمز',0,0,'#cf2945'),
       ('سفید',0,0,'#e1e6e2');
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
       ('هودی یونیسکس',0,0),
       ('بافت',0,0),
       ('بافت زنانه',0,0),
       ('بافت مردانه',0,0),
       ('بافت یونیسکس',0,0),
       ('تی شرت',0,0),
       ('تی شرت یونیسکس',0,0),
       ('بلوز',0,0),
       ('بلوز زنانه',0,0),
       ('بلوز مردانه',0,0),
       ('بلوز یونیسکس',0,0),
       ('دورس',0,0),
       ('دورس زنانه',0,0),
       ('دورس مردانه',0,0),
       ('دورس یونیسکس',0,0);

# digikala
UPDATE category_tbl SET category_id=1 where id=2;
UPDATE category_tbl SET category_id=2 where id=3;
UPDATE category_tbl SET category_id=2 where id=4;
UPDATE category_tbl SET category_id=2 where id=5;

UPDATE category_tbl SET category_id=1 where id=6;
UPDATE category_tbl SET category_id=6 where id=7;
UPDATE category_tbl SET category_id=6 where id=8;
UPDATE category_tbl SET category_id=6 where id=9;

UPDATE category_tbl SET category_id=1 where id=10;
UPDATE category_tbl SET category_id=10 where id=11;

UPDATE category_tbl SET category_id=1 where id=12;
UPDATE category_tbl SET category_id=12 where id=13;
UPDATE category_tbl SET category_id=12 where id=14;
UPDATE category_tbl SET category_id=12 where id=15;

UPDATE category_tbl SET category_id=1 where id=16;
UPDATE category_tbl SET category_id=16 where id=17;
UPDATE category_tbl SET category_id=16 where id=18;
UPDATE category_tbl SET category_id=16 where id=19;


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
