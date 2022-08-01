package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    public MessagesRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }
    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        ResultSet resultSet = ds.getConnection().createStatement().executeQuery("SELECT * FROM message JOIN users ON message.author=users.id JOIN room ON message.room=room.id WHERE message.id = " + id);

        if (!resultSet.next())
            return Optional.empty();
        User author = new User(resultSet.getLong("author"), resultSet.getString("login"), resultSet.getString("password"));
        Chatroom chatroom = new Chatroom(resultSet.getLong("room"), resultSet.getString("name"));
        Message message = new Message(resultSet.getLong("id"), author, chatroom, resultSet.getString("text"), resultSet.getTimestamp("datetime").toLocalDateTime());
        return Optional.of(message);
    }
    private DataSource ds;
}