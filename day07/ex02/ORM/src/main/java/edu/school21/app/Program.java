package edu.school21.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.models.Car;
import edu.school21.models.TestClass;
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
        Car car = new Car(228, "Audi", 228);
        TestClass testClass = new TestClass(
                1, "pohui", "pohui2", 228, true,
                228L, 228F, 228.1);

        ormManager.save(user);
        ormManager.save(car);
        ormManager.save(testClass);

        user.setFirstName("NoValera");
        user.setLastName("NoValerian");
        user.setAge(332);

        ormManager.update(user);




    }
}
