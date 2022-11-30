drop table if exists chat.users, chat.rooms, chat.messages;
drop schema if exists chat;


create schema chat;

create table chat.users
(
    id          serial primary key,
    username    text unique not null,
    password    text        not null
);

create table chat.rooms
(
    id          serial primary key,
    name        text not null,
    ownerId int not null references chat.users (id)
);

create table chat.messages
(
    id          serial primary key,
    roomId      int  not null references chat.rooms (id),
    author      int  not null references chat.users (id),
    message     text not null,
    time        timestamp
);