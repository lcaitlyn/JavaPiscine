package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Chatroom;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ChatroomRepositoryImpl implements CrudRepository<Chatroom> {
    private final JdbcTemplate jdbcTemplate;

    public ChatroomRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Chatroom findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM chat.rooms WHERE id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Chatroom.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Chatroom> findAll() {
        String alQuery = "SELECT * FROM chat.rooms";
        List<Chatroom> chatrooms = jdbcTemplate.query(alQuery, new BeanPropertyRowMapper<>(Chatroom.class));
        return chatrooms.isEmpty() ? null : chatrooms;
    }

    @Override
    public void save(Chatroom entity) {
        jdbcTemplate.update("INSERT INTO chat.rooms (name, ownerid) values (?, ?)",
                entity.getName(), entity.getOwner().getId());
    }

    @Override
    public void update(Chatroom entity) {
        jdbcTemplate.update("UPDATE chat.rooms SET name=?, ownerid=? WHERE id=?",
                entity.getName(), entity.getOwner().getId(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM chat.rooms WHERE id=?", id);
    }
}
