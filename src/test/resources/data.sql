-- Password : PasswordA123
-- encrypted using https://www.javainuse.com/onlineBcrypt
set mode Oracle;

select user_seq.NEXTVAL from dual;

INSERT INTO user_tbl (u_id, u_email, u_first_name, u_last_name, u_password, u_username, email_verified, deleted)
VALUES (user_seq.NEXTVAL, 'UserA@junit.com', 'UserAFirstName', 'UserALastName', '$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2', 'UserA', true, false)
     , (user_seq.NEXTVAL, 'UserB@junit.com', 'UserBFirstName', 'UserBLastName', '$2a$10$TlYbg57fqOy/1LJjispkjuSIvFJXbh3fy0J9fvHnCpuntZOITAjVG', 'UserB', false , false);
