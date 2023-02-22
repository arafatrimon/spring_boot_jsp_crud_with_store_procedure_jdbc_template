CREATE TABLE IF NOT EXISTS EMPLOYEE(
                       ID BIGINT  PRIMARY KEY AUTO_INCREMENT,
                       NAME VARCHAR(50),
                       FATHER_NAME VARCHAR(50),
                       MOTHER_NAME VARCHAR(255),
                       GENDER VARCHAR(255),
                       AGE VARCHAR(255),
                       DESIGNATION VARCHAR(255),
                       NID_NUMBER VARCHAR(255),
                       DATE_OF_BIRTH VARCHAR(255),
                       CONTACT_NUMBER VARCHAR(255),
                       ADDRESS VARCHAR(255)
);
--     /;
--
-- CREATE TABLE PERSON_HISTORY AS (SELECT * FROM PERSON) WITH NO DATA;