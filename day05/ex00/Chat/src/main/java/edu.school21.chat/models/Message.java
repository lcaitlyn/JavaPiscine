package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;
import edu.school21.chat.models.Chatroom;

public class Message {
    private int id;
    private User author;
    private Chatroom room_id;
    private String text;
    private LocalDateTime time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (!Objects.equals(author, message.author)) return false;
        if (!Objects.equals(room_id, message.room_id)) return false;
        if (!Objects.equals(text, message.text)) return false;
        return Objects.equals(time, message.time);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (room_id != null ? room_id.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", room_id='" + room_id + '\'' +
                ", text='" + text + '\'' +
                ", time=" + time +
                '}';
    }
}