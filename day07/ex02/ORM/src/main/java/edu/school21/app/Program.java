package edu.school21.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.models.User;
import edu.school21.processor.OrmManager;

public class Program {
    public static HikariDataSource makeDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariDataSource.setUsername("postgres");
        hikariDataSource.setPassword("");

        return hikariDataSource;
    }

    public static void main(String[] args) {
        OrmManager ormManager = new OrmManager(makeDataSource());

        User user = new User(228L, "Valera", "Valerian", 228);



    }
}
