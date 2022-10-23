package main.java.edu.school21.chat.repositories;

import main.java.edu.school21.chat.exception.NotSavedSubEntityException;
import main.java.edu.school21.chat.models.Message;

import java.sql.SQLException;
import java.util.Optional;

public interface MessagesRepository {
    Optional<Message> findById(Long id) throws SQLException;
    boolean save(Message message) throws NotSavedSubEntityException;
    boolean update(Message message) throws SQLException;
}
