package main.java.edu.school21.chat.models;

import java.util.*;

public class Chatroom {
    private int id;
    private String name;
    private int chatOwner;
    List<Message> messageList = new LinkedList<Message>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chatroom chatroom = (Chatroom) o;

        if (id != chatroom.id) return false;
        if (chatOwner != chatroom.chatOwner) return false;
        if (!Objects.equals(name, chatroom.name)) return false;
        return Objects.equals(messageList, chatroom.messageList);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + chatOwner;
        result = 31 * result + (messageList != null ? messageList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chatOwner=" + chatOwner +
                ", messageList=" + messageList +
                '}';
    }
}