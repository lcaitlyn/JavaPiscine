package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class MessageRepositoryImpl implements CrudRepository<Message> {
    private final JdbcTemplate jdbcTemplate;

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
        jdbcTemplate.update("INSERT INTO chat.messages (message, roomid, author, time) values (?, ?, ?, ?)",
                entity.getText(), entity.getChatroom().getId(), entity.getSender().getId(), entity.getTimestamp());
    }

    @Override
    public void update(Message entity) {
        jdbcTemplate.update("UPDATE chat.messages SET message=?, roomid=? ,author=?, time=? WHERE id = ?",
                entity.getText(), entity.getChatroom().getId(), entity.getSender().getId(), entity.getTimestamp());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM chat.messages WHERE id = ?", id);
    }
}
