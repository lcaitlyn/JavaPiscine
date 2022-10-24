package main.java.edu.school21.chat.repositories;

import main.java.edu.school21.chat.models.User;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // LIMIT - сколько вывести OFFSET - на сколько смещение
    // подробнее тута: https://sqlbolt.com/lesson/filtering_sorting_query_results
    // select * from chat.users limit 2 offset 2; <-- вот такую штуку замутить

    @Override
    public List<User> findAll(int page, int size) {
        List<User> usersList = new LinkedList<User>();

        String query = "select * from chat.users limit ? offset ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, size); // limit
            statement.setLong(2, (long) page * size); // offset

            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                usersList.add(new User(
                                resultSet.getInt("id"),
                                resultSet.getString("login"),
                                resultSet.getString("password")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usersList;
    }
}
