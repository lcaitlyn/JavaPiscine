package edu.school21.sockets.services;

import edu.school21.sockets.models.Chatroom;

import java.util.List;

public interface ChatroomService {
    boolean createChatroom(Chatroom chatroom);
    List<Chatroom> getChatrooms();
}