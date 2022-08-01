CREATE TABLE users (id BIGSERIAL PRIMARY KEY, login VARCHAR(50) UNIQUE NOT NULL, password VARCHAR(50));

CREATE TABLE room (id BIGSERIAL PRIMARY KEY NOT NULL, name VARCHAR(100) NOT NULL, owner BIGSERIAL REFERENCES users(id) ON DELETE CASCADE NOT NULL);

CREATE TABLE message (id BIGSERIAL PRIMARY KEY NOT NULL, author BIGSERIAL REFERENCES users(id) ON DELETE CASCADE, room BIGSERIAL REFERENCES room(id) ON DELETE CASCADE, text text NULL, datetime timestamp NULL);

CREATE TABLE rooms_of_users (user_id BIGSERIAL REFERENCES users(id) ON DELETE CASCADE NOT NULL, chat_room_id BIGSERIAL REFERENCES room(id) ON DELETE CASCADE NOT NULL);
