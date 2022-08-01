package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Message;

import java.sql.SQLException;
import java.util.Optional;

public interface MessagesRepository {
    Optional<Message> findById(Long id) throws SQLException;

    void save(Message message) throws NotSavedSubEntityException, SQLException;

    Message update(Message message) throws SQLException;
}