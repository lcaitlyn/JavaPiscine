package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

class Program {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig("/home/bulat/hikari.properties");
        HikariDataSource ds = new HikariDataSource(config);

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);

        User creator = new User(4L, "user", "user", new ArrayList(), new ArrayList());
        User author = creator;
        Chatroom room = new Chatroom(5L, "room", creator, new ArrayList());
        Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
        messagesRepository.save(message);
        System.out.println(message.getId());
    }
}