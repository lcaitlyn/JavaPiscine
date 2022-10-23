package main.java.edu.school21.chat.repositories;

import main.java.edu.school21.chat.exception.NotSavedSubEntityException;
import main.java.edu.school21.chat.models.Chatroom;
import main.java.edu.school21.chat.models.Message;
import main.java.edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;
    private final String query = "select * from chat.messages where id = ";

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        // закинул все в try (), чтобы всё автоматически закрылось(.close()),
        // работает потому что все классы наследуются от AutoClosable
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query + id);) {

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


    //insert into chat.messages (room_id, author, message, time) values (1, 1, 'hello boy$', '1-1-2000 16:00:00');
    @Override
    public boolean save(Message message) throws NotSavedSubEntityException {
        String query =
                "insert into chat.messages (room_id, author, message, time) values (" +
                message.getRoom().getId() + ", " +
                message.getAuthor().getId() + ", '" +
                message.getText() + "', '" +
                Timestamp.valueOf(message.getTime()) +
        "');";

        // Statement.RETURN_GENERATED_KEYS - для того чтобы вернуло значения запроса
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            message.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }

        return true;
    }
}



