package edu.school21.sockets.services;

import edu.school21.sockets.models.Chatroom;
import edu.school21.sockets.repositories.ChatroomRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class ChatroomServiceImpl implements ChatroomService {
    private ChatroomRepositoryImpl chatroomRepository;

    public ChatroomServiceImpl(ChatroomRepositoryImpl chatroomRepository) {
        this.chatroomRepository = chatroomRepository;
    }

    @Override
    public boolean createChatroom(Chatroom chatroom) {
        if (chatroom == null
            || chatroom.getName() == null
            || chatroom.getOwner() == null
            || chatroom.getOwner().getId() == null)
            return false;

        chatroomRepository.save(chatroom);
        return true;
    }

    @Override
    public List<Chatroom> getChatrooms() {
        return chatroomRepository.findAll();
    }
}