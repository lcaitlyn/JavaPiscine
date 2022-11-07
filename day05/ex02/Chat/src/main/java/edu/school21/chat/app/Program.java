package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static DataSource makeHikariDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariDataSource.setUsername("postgres");
        hikariDataSource.setPassword("");

        return hikariDataSource;
    }

    public static void main(String[] args) {
        User admin = new User(1, "Admin", "Admin");
        Chatroom adminChatRoom = new Chatroom(1, "AdminChatRoom", admin);
        Message message = new Message(-1, admin, adminChatRoom, "Я тут админ!!!", LocalDateTime.now());

        MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl(makeHikariDataSource());

        messagesRepositoryJdbc.save(message);

        System.out.println("ID сообщения в БД = " + message.getId());
    }
}
