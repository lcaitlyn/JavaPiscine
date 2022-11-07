package edu.school21.chat.models;

import java.util.*;

public class Chatroom {
    private int id;
    private String name;
    private User chatOwner;
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
        return Objects.hash(id, name, chatOwner, messageList);
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