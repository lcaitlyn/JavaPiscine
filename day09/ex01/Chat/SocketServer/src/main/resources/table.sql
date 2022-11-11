drop table if exists chat.users, chat.messages;
drop schema if exists chat;

create schema chat;

create table chat.users (
    id serial primary key,
    username text not null,
    password text not null
);

create table chat.messages (
    id serial primary key,
    message text not null,
    sender int not null references chat.users(id),
    time timestamp
);