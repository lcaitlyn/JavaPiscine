package main.java.edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime time;

    public Message(int id, User author, Chatroom room, String text, LocalDateTime time) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (!Objects.equals(author, message.author)) return false;
        if (!Objects.equals(room, message.room)) return false;
        if (!Objects.equals(text, message.text)) return false;
        return Objects.equals(time, message.time);
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author.getId() +
                ", room=" + room.getId() +
                ", text='" + text + '\'' +
                ", time=" + time.toLocalTime() + " " + time.toLocalDate() +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}