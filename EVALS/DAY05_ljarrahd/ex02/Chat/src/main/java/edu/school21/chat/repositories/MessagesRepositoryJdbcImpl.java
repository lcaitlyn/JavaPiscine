package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    public MessagesRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }
    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        ResultSet resultSet = ds.getConnection().createStatement().executeQuery(
                "SELECT * FROM message JOIN users ON message.author=users.id JOIN room ON message.room=room.id WHERE message.id = " + id);

        if (!resultSet.next())
            return Optional.empty();
        User author = new User(
                resultSet.getLong("author"),
                resultSet.getString("login"),
                resultSet.getString("password")
        );
        Chatroom chatroom = new Chatroom(
                resultSet.getLong("room"),
                resultSet.getString("name")
        );
        Message message = new Message(
                resultSet.getLong("id"),
                author,
                chatroom,
                resultSet.getString("text"),
                resultSet.getTimestamp("datetime").toLocalDateTime()
        );
        return Optional.of(message);
    }

    @Override
    public void save(Message message) throws NotSavedSubEntityException, SQLException {
        Long authorId = message.getAuthor().getId();
        Long roomId = message.getRoom().getId();
        if (authorId == null || roomId == null)
            throw new NotSavedSubEntityException();
        PreparedStatement query = ds.getConnection().prepareStatement(
                "INSERT INTO message (author, room, text, datetime) VALUES (?, ?, ?, ?) RETURNING id"
        );
        query.setLong(1, authorId);
        query.setLong(2, roomId);
        query.setString(3, message.getText());
        query.setTimestamp(4, Timestamp.valueOf(message.getDatetime()));
        try {
            ResultSet resultSet = query.executeQuery();
            resultSet.next();
            message.setId(resultSet.getLong("id"));
        } catch (org.postgresql.util.PSQLException e) {
            throw new NotSavedSubEntityException();
        }
    }

    private DataSource ds;
}