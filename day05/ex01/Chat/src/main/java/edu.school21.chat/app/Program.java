package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
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
        Scanner scanner = new Scanner(System.in);
        Optional<Message> message;
        MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl(makeHikariDataSource());

        try {
            System.out.println("Введите ID сообщения:");
            message = messagesRepositoryJdbc.findById(scanner.nextLong());

            if (message.isPresent()) {
                System.out.println(message.get());
            } else {
                System.err.println("Сообщение не найдено!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
