DROP TABLE IF EXISTS simple_user;CREATE TABLE simple_user (id SERIAL PRIMARY KEY,first_name VARCHAR(10),last_name VARCHAR(10),age INT);
DROP TABLE IF EXISTS car;

CREATE TABLE car (
                     id SERIAL PRIMARY KEY,
                     name VARCHAR(10),
                     speed INT
);
