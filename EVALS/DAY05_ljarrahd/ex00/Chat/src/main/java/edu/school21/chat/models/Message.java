package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

class Message {
    private Long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime datetime;

    Message(User author, Chatroom room, String text, LocalDateTime datetime) {
        this.author = author;
        this.room = room;
        this.text = text;
        this.datetime = datetime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && author.equals(message.author) && room.equals(message.room) && text.equals(message.text) && datetime.equals(message.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, datetime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", chatroom=" + room +
                ", text='" + text + '\'' +
                ", messageDateTime=" + datetime +
                '}';
    }
}