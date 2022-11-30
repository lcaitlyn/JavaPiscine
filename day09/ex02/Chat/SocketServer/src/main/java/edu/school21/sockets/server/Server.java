package edu.school21.sockets.server;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.models.Chatroom;
import edu.school21.sockets.repositories.ChatroomRepositoryImpl;
import edu.school21.sockets.repositories.MessageRepositoryImpl;
import edu.school21.sockets.services.ChatroomService;
import edu.school21.sockets.services.UsersService;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Parameters(separators = "=")
public class Server {
    @Parameter(names = "--port")
    private int port;
    public static List<Chatroom> chatrooms;
    private UsersService usersService;
    private MessageRepositoryImpl messageRepository;
    private ChatroomService chatroomService;

    public Server(UsersService usersService, MessageRepositoryImpl messageRepository, ChatroomService chatroomService) {
        this.usersService = usersService;
        this.messageRepository = messageRepository;
        this.chatroomService = chatroomService;
        chatrooms = new ArrayList<>();
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Server started...");

            while (true) {
                Socket socket = serverSocket.accept();
                Client client = new Client(socket, usersService, messageRepository, chatroomService);
                client.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
