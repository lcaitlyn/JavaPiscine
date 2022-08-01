package edu.school21.chat.models;

import java.util. List;
import java.util.Objects;

class Chatroom {
    Chatroom(String name, User owner,  List<Message> messages) {
        this.name = name;
        this.owner = owner;
        this.messages = messages;
    }

    private Long id;
    private String name;
    private User owner;
    private  List<Message> messages;

    public  List<Message> getMessages() {
        return messages;
    }

    public void setMessages( List<Message> messages) {
        this.messages = messages;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Chatroom chatroom = (Chatroom) o;
        return id == chatroom.id && name.equals(chatroom.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, messages);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", messages=" + messages +
                '}';
    }
}