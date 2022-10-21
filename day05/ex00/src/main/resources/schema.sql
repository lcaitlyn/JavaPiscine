drop schema if exists chat;
drop table if exists chat.users, chat.rooms, chat.messages;

create schema chat;

create table chat.users (
    id          serial primary key,
    login       text unique not null,
    password    text not null
);

create table chat.rooms (
    id          serial primary key,
    chat_name   text not null,
    chat_owner  int not null references chat.users(id)
);

create table chat.messages (
    id          serial primary key,
    room_id     int not null references chat.rooms(id),
    author      int not null references chat.users(id),
    message     text not null,
    time        timestamp
);