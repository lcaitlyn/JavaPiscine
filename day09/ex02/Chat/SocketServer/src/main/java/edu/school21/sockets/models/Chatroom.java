package edu.school21.sockets.models;

import edu.school21.sockets.server.Client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Chatroom {
    private Long id;
    private String name;
    private Long ownerId;
    private User owner;

    public Chatroom() {
    }

    public Chatroom(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }
//    private static List<Message> messageList = new ArrayList<>();
    private static List<Client> clientList = new ArrayList<>();

    public static void addClient(Client client) {
        clientList.add(client);
    }

    public static List<Client> getClientList() {
        return clientList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


}
