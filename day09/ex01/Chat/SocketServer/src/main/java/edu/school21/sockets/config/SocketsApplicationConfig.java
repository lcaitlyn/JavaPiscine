package edu.school21.sockets.config;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.sockets.repositories.MessageRepositoryImpl;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import edu.school21.sockets.server.Client;
import edu.school21.sockets.server.Server;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("edu.school21.sockets")
public class SocketsApplicationConfig {
    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.driver.name}")
    private String driverName;

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);

        return hikariDataSource;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UsersRepository usersRepository() {
        return new UsersRepositoryImpl(hikariDataSource());
    }

    @Bean
    UsersService usersService() {
        return new UsersServiceImpl(usersRepository(), bCryptPasswordEncoder());
    }

    @Bean()
    MessageRepositoryImpl messageRepository() {
        return new MessageRepositoryImpl(hikariDataSource());
    }

    @Bean
    Server server() {
        return new Server(usersService(), messageRepository());
    }
}
