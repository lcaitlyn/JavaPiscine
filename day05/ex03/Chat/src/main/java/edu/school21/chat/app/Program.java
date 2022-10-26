package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.*;
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

    public static void main(String[] args) throws SQLException {
        MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl(makeHikariDataSource());

        Optional<Message> optionalMessage = messagesRepositoryJdbc.findById(3L);


        System.out.println();


        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();

            message.setText("Я изменил текст :D");
            message.setTime(LocalDateTime.now());

            messagesRepositoryJdbc.update(message);
        } else {
            System.err.println("Сообщение не найдено!");
        }

        optionalMessage = messagesRepositoryJdbc.findById(3L);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();

            System.out.println(message);
            System.out.println();

            System.out.println("Щас будет микротест, в котором должен вылезти exception!!!");

            message.setAuthor(new User(228, "Admin", "Admin"));

            messagesRepositoryJdbc.update(message);
        } else {
            System.err.println("Сообщение не найдено!");
        }


    }
}
