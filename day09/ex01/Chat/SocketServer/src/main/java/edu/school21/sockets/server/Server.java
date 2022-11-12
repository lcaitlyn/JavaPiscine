package edu.school21.sockets.server;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.MessageRepositoryImpl;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

@Component
@Parameters(separators = "=")
public class Server {
    @Parameter(names = "--port")
    private int port;

    private ServerSocket serverSocket;
    private static MessageRepositoryImpl messageRepository;
    private static LinkedList<Client> list;
    private UsersService usersService;

    @Autowired
    public Server(UsersService usersService) {
        this.usersService = usersService;
    }

    @Autowired
    public static void setMessageRepository(MessageRepositoryImpl messageRepository) {
        Server.messageRepository = messageRepository;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println(Thread.currentThread().getName());
            while (true) {
                Socket socket = serverSocket.accept();
                Client client = new Client(socket, usersService);
                client.start();
//                list.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageToAllClients(Message message) {
        for (Client client : list) {
            if (client.getSocket().isConnected()) {
                messageRepository.save(message);
                client.writeToClient(message.getText());
            } else
                list.remove(client);
        }
    }
}
