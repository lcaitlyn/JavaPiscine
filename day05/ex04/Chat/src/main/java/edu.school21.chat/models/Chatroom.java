package edu.school21.chat.models;

import java.util.*;

public class Chatroom {
    private long id;
    private String name;
    private User chatOwner;
    List<Message> messageList = new LinkedList<Message>();

    public Chatroom(int id, String name, User chatOwner) {
        this.id = id;
        this.name = name;
        this.chatOwner = chatOwner;
    }

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
        int result = (int) id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (chatOwner != null ? chatOwner.hashCode() : 0);
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getChatOwner() {
        return chatOwner;
    }

    public void setChatOwner(User chatOwner) {
        this.chatOwner = chatOwner;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}