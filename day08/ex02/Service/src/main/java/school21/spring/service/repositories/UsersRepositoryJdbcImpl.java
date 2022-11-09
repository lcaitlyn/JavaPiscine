package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("usersRepositoryJdbcImpl")
public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final DataSource dataSource;

    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("hikariDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        String query = "select * from users where id = ";

        try (Connection connection = dataSource.getConnection();
             Statement statement = dataSource.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query + id)) {

            if (resultSet.next()) {
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String query = "select * from users";
        List<User> userList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                ));
            }
            return userList;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(User entity) {
        String query = "insert into users (email, password) values (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String query = "update users set email = ? where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, entity.getEmail());
            statement.setLong(2, entity.getId());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "delete from users where id = ";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(query + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "select * from users where email = ";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query + email)) {

            if (resultSet.next()) {
                return Optional.of(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                ));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }
}
