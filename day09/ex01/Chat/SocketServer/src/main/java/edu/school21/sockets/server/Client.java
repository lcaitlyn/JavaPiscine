package edu.school21.sockets.server;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.MessageRepositoryImpl;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client extends Thread {
    private Socket socket;
    private UsersService usersService;
    private MessageRepositoryImpl messageRepository;
    private BufferedWriter writer;
    private BufferedReader reader;
    private User user;

    public Client(Socket socket, UsersService usersService, MessageRepositoryImpl messageRepository) {
        this.socket = socket;
        this.usersService = usersService;
        this.messageRepository = messageRepository;
    }

    @Override
    synchronized public void run() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writeToClient("Hello from Server! " + Thread.currentThread().getName());
            writeToClient("Available commands: signIn signUp exit");

            while (true) {
                switch (reader.readLine().toLowerCase()) {
                    case "signup":
                        if (!signUp())
                            break;
                    case "signin":
                        if (!signIn())
                            break;
                        startMessenger();
                        break;
                    case "exit":
                        writeToClient("You have left the chat");
                        return;
                    default:
                        writeToClient("Wrong command! Try 'signUp / signIn / exit'");
                }
            }

        } catch (Exception e) {
            System.out.println("Client '" + Thread.currentThread().getName() + "' has disconnected");
        } finally {
            try {
                socket.close();
                writer.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getInfo(String info) {
        try {
            writeToClient(String.format("Enter %s:", info));
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean signUp() {
        try {
            writeToClient("Registration...");
            usersService.signUp(getInfo("username"), getInfo("password"));
            writeToClient("Successful sign Up!");
            return true;
        } catch (RuntimeException e) {
            writeToClient(e.getMessage());
            return false;
        }
    }

    private boolean signIn() {
        try {
            writeToClient("Logging in...");
            user = usersService.signIn(getInfo("username"), getInfo("password"));
            writeToClient("Successful sign In!");
            return true;
        } catch (Exception e) {
            writeToClient(e.getMessage());
            return false;
        }
    }

    private void startMessenger() {
        try {
            writeToClient("Start messaging...");
            String text = reader.readLine();
            while (!text.equalsIgnoreCase("exit")) {
                messageRepository.save(new Message(text, user, LocalDateTime.now()));

                for (Client c : Server.getList()) {
                    if (c.getSocket().isConnected())
                        c.writeToClient(text);
                    else
                        Server.getList().remove(c);
                }
                text = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToClient(String message) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
