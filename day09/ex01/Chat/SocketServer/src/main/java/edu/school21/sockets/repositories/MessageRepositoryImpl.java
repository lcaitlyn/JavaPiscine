package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class MessageRepositoryImpl implements CrudRepository<Message> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Message findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM chat.messages WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Message.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Message> findAll() {
        String alQuery = "SELECT * FROM chat.messages";
        List<Message> messages = jdbcTemplate.query(alQuery, new BeanPropertyRowMapper<>(Message.class));
        return messages.isEmpty() ? null : messages;
    }

    @Override
    public void save(Message entity) {
        jdbcTemplate.update("INSERT INTO chat.messages (message, sender, time) values (?, ?, ?) RETURNING id",
                entity.getText(), entity.getSender().getId(), entity.getTimestamp());
    }

    @Override
    public void update(Message entity) {
        jdbcTemplate.update("UPDATE chat.messages SET message=?, sender=?, time=? WHERE id = ?",
                entity.getText(), entity.getSender().getId(), entity.getTimestamp());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM chat.messages WHERE id = ?", id);
    }
}
