package main.java.edu.school21.chat.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms = new LinkedList<>();
    private List<Chatroom> activeRooms = new LinkedList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!Objects.equals(login, user.login)) return false;
        if (!Objects.equals(password, user.password)) return false;
        if (!Objects.equals(createdRooms, user.createdRooms)) return false;
        return Objects.equals(activeRooms, user.activeRooms);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (createdRooms != null ? createdRooms.hashCode() : 0);
        result = 31 * result + (activeRooms != null ? activeRooms.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", activeRooms=" + activeRooms +
                '}';
    }
}