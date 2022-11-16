package edu.school21.sockets.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Message {
    private Long id;
    private String text;
    private User sender;
    private LocalDateTime timestamp;

    public Message(String text, User sender, LocalDateTime timestamp) {
        this.text = text;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("%s: %s [%s]", sender.getUsername(), getText(),
                getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss, dd MMMM yyyy")));
    }
}
