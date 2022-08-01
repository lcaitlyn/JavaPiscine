INSERT INTO users(login, password) VALUES ('julian', 'julian_password');
INSERT INTO users(login, password) VALUES ('dean', 'dean_password');
INSERT INTO users(login, password) VALUES ('denise', 'denise_password');
INSERT INTO users(login, password) VALUES ('john', 'john_password');
INSERT INTO users(login, password) VALUES ('cheryl', 'cheryl_password');

INSERT INTO room(name, owner) VALUES ('private', '1');
INSERT INTO room(name, owner) VALUES ('common', '1');
INSERT INTO room(name, owner) VALUES ('general', '2');
INSERT INTO room(name, owner) VALUES ('vip', '1');
INSERT INTO room(name, owner) VALUES ('random', '3');

INSERT INTO message(author, room, text, datetime) VALUES ('1', '1', 'julian private text', '2022-06-25');
INSERT INTO message(author, room, text, datetime) VALUES ('1', '2', 'julian common text', '2022-06-30');
INSERT INTO message(author, room, text, datetime) VALUES ('2', '1', 'dean private text', '2022-06-29');
INSERT INTO message(author, room, text, datetime) VALUES ('2', '2', 'dean common text', '2022-06-28');
INSERT INTO message(author, room, text, datetime) VALUES ('4', '4', 'john vip text', '2022-06-27');
INSERT INTO message(author, room, text, datetime) VALUES ('1', '3', 'julian general', '2022-06-26');

INSERT INTO rooms_of_users(user_id, chat_room_id) VALUES ('1', '1');
INSERT INTO rooms_of_users(user_id, chat_room_id) VALUES ('1', '2');
INSERT INTO rooms_of_users(user_id, chat_room_id) VALUES ('2', '1');
INSERT INTO rooms_of_users(user_id, chat_room_id) VALUES ('2', '2');
INSERT INTO rooms_of_users(user_id, chat_room_id) VALUES ('3', '1');
INSERT INTO rooms_of_users(user_id, chat_room_id) VALUES ('3', '2');
INSERT INTO rooms_of_users(user_id, chat_room_id) VALUES ('4', '4');