drop table if exists users;

create table users (
    id int identity primary key,
    email varchar(256) not null ,
    password varchar(256) not null
);
