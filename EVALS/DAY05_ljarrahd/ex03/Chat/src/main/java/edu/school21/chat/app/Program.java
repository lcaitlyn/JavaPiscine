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
import java.util.Optional;

class Program {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig("/home/bulat/hikari.properties");
        HikariDataSource ds = new HikariDataSource(config);

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
        Optional<Message> messageOptional = messagesRepository.findById(1L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setAuthor(new User(3L, "asdf", "asdf"));
            message.setText("Bye");
            message.setDatetime(null);
            message = messagesRepository.update(message);
            System.out.println(message);
        }
    }
}