

DROP DATABASE IF EXISTS lunch;

CREATE DATABASE lunch;

USE lunch;

DROP TABLE IF EXISTS member;

CREATE TABLE member(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)  UNIQUE,
   state TINYINT(1) NOT NULL DEFAULT 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO member (name) VALUES ('你好');
INSERT INTO member (name) VALUES ('1');
INSERT INTO member (name) VALUES ('2');
INSERT INTO member (name) VALUES ('3');
INSERT INTO member (name) VALUES ('4');
INSERT INTO member (name) VALUES ('5');




