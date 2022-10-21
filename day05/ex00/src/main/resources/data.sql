insert into chat.users (login, password) values ('Андрей', 'andrey');
insert into chat.users (login, password) values ('Антон', 'anton');
insert into chat.users (login, password) values ('Маша', 'masha');
insert into chat.users (login, password) values ('Саша', 'sasha');
insert into chat.users (login, password) values ('Катя', 'katya');

insert into chat.rooms (chat_owner, chat_name) values (1, 'chat1');
insert into chat.rooms (chat_owner, chat_name) values (2, 'chat2');

insert into chat.messages (id, room_id, author, message, time) values (1, 1, 1, 'hello boy$', '1-1-2000 16:00:00');
insert into chat.messages (id, room_id, author, message, time) values (2, 1, 2, 'hi boi', '1-1-2000 16:01:00');
insert into chat.messages (id, room_id, author, message, time) values (3, 1, 4, 'i am boy?', '1-1-2000 16:02:00');
insert into chat.messages (id, room_id, author, message, time) values (4, 2, 4, 'i am girl?', '1-1-2000 16:02:30');
insert into chat.messages (id, room_id, author, message, time) values (5, 2, 4, 'no you are not', '1-1-2000 16:03:00');
insert into chat.messages (id, room_id, author, message, time) values (6, 2, 5, 'what are you doing in out chat??', '1-1-2000 16:03:10');