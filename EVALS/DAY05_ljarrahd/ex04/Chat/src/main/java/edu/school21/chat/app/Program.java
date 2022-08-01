package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.List;

class Program {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig("/home/bulat/hikari.properties");
        HikariDataSource ds = new HikariDataSource(config);

        UsersRepositoryJdbcImpl usersRepository = new UsersRepositoryJdbcImpl(ds);
        List<User> users = usersRepository.findAll(1, 3);
        for (User user : users)
            System.out.println("User = " + user);
    }
}