package edu.school21.sockets.server;

import edu.school21.sockets.models.Chatroom;
import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.ChatroomRepositoryImpl;
import edu.school21.sockets.repositories.MessageRepositoryImpl;
import edu.school21.sockets.services.ChatroomService;
import edu.school21.sockets.services.UsersService;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

public class Client extends Thread {
    private Socket socket;
    private UsersService usersService;
    private ChatroomService chatroomService;
    private MessageRepositoryImpl messageRepository;
    private BufferedWriter writer;
    private BufferedReader reader;
    private User user;
    private Chatroom chatroom;
    private boolean isOnline = false;

    public Client(Socket socket, UsersService usersService, MessageRepositoryImpl messageRepository, ChatroomService chatroomService) {
        this.socket = socket;
        this.usersService = usersService;
        this.messageRepository = messageRepository;
        this.chatroomService = chatroomService;
    }

    @Override
    synchronized public void run() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writeToClient("Hello from Server!");
            writeToClient("Available commands: signIn signUp exit");

            userMenu();
            roomMenu();

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

    private boolean userMenu() {
        boolean status = true;

        try {
            while (status) {
                writeToClient("1. Sign up");
                writeToClient("2. Sign in");
                writeToClient("3. Exit");
                switch (reader.readLine().toLowerCase()) {
                    case "1":
                    case "sign up":
                        if (!signUp())
                            return false;
                    case "2":
                    case "sign in":
                        if (!signIn())
                            break;
                        status = false;
                        break;
                    case "exit":
                    case "3":
                        writeToClient("You have left the chat messenger...");
                        return false;
                    default:
                        writeToClient("Wrong command! Try 'Sign Up / Sign In / Exit' or number");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void roomMenu() {
        if (user == null)
            return;

        boolean status = true;

        try {
            while (status) {
                writeToClient("1. Create room");
                writeToClient("2. Choose room");
                writeToClient("3. Exit");
                switch (reader.readLine().toLowerCase()) {
                    case "1":
                    case "create room":
                        if (!createRoom())
                            break;
                    case "2":
                    case "choose room":
                        if (!chooseRoom())
                            break;
                        startMessenger();
                        status = false;
                        break;
                    case "exit":
                    case "3":
                        writeToClient("You have left the chat");
                        return;
                    default:
                        writeToClient("Wrong command! Try 'Create room / Choose room / Exit' or number");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean createRoom() {
        try {
            writeToClient("Creating new room...");
            Chatroom newChat = new Chatroom(getInfo("chatroom name"), user);

            chatroomService.createChatroom(newChat);
            Server.chatrooms.add(newChat);
            writeToClient("Chatroom was created");
            return true;
        } catch (RuntimeException e) {
            writeToClient(e.getMessage());
            return false;
        }
    }

    private boolean chooseRoom() {

        if (Server.chatrooms == null || Server.chatrooms.size() == 0) {
            writeToClient("There are not chat rooms. You need create the one");
            return false;
        }

        writeToClient("Choose chatroom:");
        int i = 0;
        for (Chatroom c : Server.chatrooms) {
            writeToClient(c.getId() + " " + c.getName());
        }

        while (true) {
            try {
                i = Integer.parseInt(getInfo("id"));

                for (Chatroom c : Server.chatrooms) {
                    if (c.getId() == i) {
                        chatroom = c;
                        chatroom.addClient(this);
                    }
                }

                if (chatroom != null)
                    break;

                writeToClient("Error! There is no same id!");
            } catch (NumberFormatException e) {
                writeToClient("Error! The number was expected!");
            }
        }
        return true;
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
            isOnline = true;
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
                Message message = new Message(text, user, chatroom, LocalDateTime.now());
                messageRepository.save(message);

                writeToChatroom(message.toString());
                text = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    public void writeToClient(String message) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToChatroom(String message) {
        for (Client client : Chatroom.getClientList()) {

            if (client.socket.isConnected() && client.isOnline)
                client.writeToClient(message);
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
