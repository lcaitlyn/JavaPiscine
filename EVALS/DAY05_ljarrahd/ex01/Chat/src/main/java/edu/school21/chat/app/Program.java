package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

class Program {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig("/home/bulat/hikari.properties");
        HikariDataSource ds = new HikariDataSource(config);

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message ID");
        Long id = scanner.nextLong();
        Optional<Message> message = messagesRepository.findById(id);
        message.ifPresent(System.out::println);
    }
}