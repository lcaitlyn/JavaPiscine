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

        User user = new User(-1L, "Boba", "Fett", 28);
        Car car = new Car(-1, "BMW", 220);
        TestClass testClass = new TestClass(
                1, "length", "noLength", 15, true,
                16L, 22.8F, 22.1);

        ormManager.save(user);
        ormManager.save(car);
        ormManager.save(testClass);

        System.out.println(ormManager.findById(user.getId(), user.getClass()));
        System.out.println(ormManager.findById(1L, car.getClass()));
        System.out.println(ormManager.findById(1L, testClass.getClass()));

        user.setFirstName("Darth");
        user.setLastName("Vaider");
        user.setAge(32);
        ormManager.update(user);

        Car mercedes = new Car(1, "Mercedes", 220);
        ormManager.update(mercedes);

        TestClass testClass1 = new TestClass(
                1, "-length", "-noLength", -15, false,
                -16L, -22.8F, -22.1);
        ormManager.update(testClass1);

        System.out.println(ormManager.findById(user.getId(), user.getClass()));
        System.out.println(ormManager.findById(1L, car.getClass()));
        System.out.println(ormManager.findById(1L, testClass.getClass()));

        ormManager.update(testClass);
        System.out.println(ormManager.findById(1L, testClass.getClass()));

    }
}
