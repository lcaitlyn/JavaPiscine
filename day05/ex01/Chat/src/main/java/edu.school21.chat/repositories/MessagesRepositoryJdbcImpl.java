package main.java.edu.school21.chat.repositories;

import main.java.edu.school21.chat.models.Chatroom;
import main.java.edu.school21.chat.models.Message;
import main.java.edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;
    private final String query = "select * from chat.messages where id = ";

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        try {
            Connection connection = dataSource.getConnection(); // создаем подключение, используя dataSource
            Statement statement = connection.createStatement(); // объект, который умеет отправлять запрос в БД
            ResultSet resultSet = statement.executeQuery(query + id); // выполняем SQL запрос и кладем в resultSet

            if (resultSet.next()) {
                return Optional.of(new Message(
                        resultSet.getInt("id"),
                        new User(resultSet.getInt("author"), "test", "test"), // засунул сюда нового пользователя, логичнее было бы его вытащить из БД, но это сложна
                        new Chatroom(resultSet.getInt("room_id"), "test", new User(0, "chat_owner", null)), // тута тоже самое, что и с пользователем
                        resultSet.getString("message"),
                        resultSet.getTimestamp("time").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return Optional.empty();
    }
}


