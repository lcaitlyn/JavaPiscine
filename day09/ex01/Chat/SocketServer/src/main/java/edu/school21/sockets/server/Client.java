package edu.school21.sockets.server;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client extends Thread {
    private Socket socket;
    private UsersService usersService;
    private BufferedWriter writer;
    private BufferedReader reader;
    private User user;

    public Client(Socket socket, UsersService usersService) {
        this.socket = socket;
        this.usersService = usersService;
    }

    @Autowired
    public void setUsersService(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    @Override
    synchronized public void run() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writeToClient("Hello from Server! " + Thread.currentThread().getName());

            while (true) {
                switch (reader.readLine().toLowerCase()) {
                    case "signup":
                        signUp();
                    case "signin":
                        signIn();
                        Thread receiver = startReceiveMessages();
                        receiver.start();
                        startSendMessages();
                        receiver.interrupt();
                        break;
                    case "exit":
                        writeToClient("You have left the chat");
                        return;
                    default:
                        writeToClient("Wrong command! Try 'signUp / signIn / exit'");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
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

    private void signUp() {
        try {
            writeToClient("Registration...");
            usersService.signUp(getInfo("username"), getInfo("password"));
            writeToClient("Successful sign Up!");
        } catch (RuntimeException e) {
            writeToClient(e.getMessage());
        }
    }

    private void signIn() {
        try {
            writeToClient("Logging in...");
            user = usersService.signIn(getInfo("username"), getInfo("password"));
            writeToClient("Successful sign In!");
        } catch (Exception e) {
            writeToClient(e.getMessage());
        }
    }

    private Thread startReceiveMessages() {
        return new Thread(() -> {
            try {
                System.out.println(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void startSendMessages() {
        try (Scanner scanner = new Scanner(System.in)) {
            String text = "";

            writeToClient("Start messaging");

            while (!text.equalsIgnoreCase("exit")) {
                text = scanner.nextLine();
                Message message = new Message(text, user, LocalDateTime.now());
                Server.sendMessageToAllClients(message);
            }
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
