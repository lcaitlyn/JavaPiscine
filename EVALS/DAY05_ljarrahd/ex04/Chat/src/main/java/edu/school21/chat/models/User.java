package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    public User(Long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> socializedRooms){
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.socializedRooms = socializedRooms;
    }
    public User(Long id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
    }

    private Long id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> socializedRooms;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }
    public void insertCreatedRoom(Chatroom chatroom) {
        if (this.createdRooms == null) {
            this.createdRooms = new ArrayList<>();
        }
        this.createdRooms.add(chatroom);
    }
    public void insertSocializedRoom(Chatroom chatroom) {
        this.socializedRooms.add(chatroom);
    }
    public List<Chatroom> getSocializedRooms() {
        return socializedRooms;
    }

    public void setSocializedRooms(List<Chatroom> socializedRooms) {
        this.socializedRooms = socializedRooms;
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
        User user = (User) o;
        return id.equals(user.id) && login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, socializedRooms);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ",login=\"" + login + '\"' +
                ",password=\"" + password + '\"' +
                ",createdRooms=" + createdRooms +
                ",rooms=" + socializedRooms +
                '}';
    }
}