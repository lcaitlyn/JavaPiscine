package edu.school21.sockets.server;

import edu.school21.sockets.models.User;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client extends Thread {
    private Socket socket;
    private UsersServiceImpl usersService;
    private ServerSocket serverSocket;
    private BufferedWriter writer;
    private BufferedReader reader;

    public Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    synchronized public void run() {
//        System.out.println(Thread.currentThread().getName());
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writeToClient("Hello from Server!" + Thread.currentThread().getName());

            while (true) {
                String message = reader.readLine();
                if (message.equalsIgnoreCase("signUp")) {
                    signUp();
                    break;
                } else if (message.equalsIgnoreCase("singIn")) {
                    signIn();
                    break;
                }
                else {
                    writeToClient("Wrong command! Try \'signUp\'");
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                writer.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void signUp() {
        String username;
        String password;

        try {
            writeToClient("Enter username:");
            username = reader.readLine();

            writeToClient("Enter password:");
            password = reader.readLine();

            try {
                usersService.signUp(username, password);
                writeToClient("Successful!");
            } catch (RuntimeException e) {
                writeToClient(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void signIn() {
        String username;
        String password;

        try {
            writeToClient("Enter username:");
            username = reader.readLine();

            writeToClient("Enter password:");
            password = reader.readLine();

            try {
                User user = usersService
                usersService.signUp(username, password);
                writeToClient("Successful!");
            } catch (RuntimeException e) {
                writeToClient(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToClient(String message) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setUsersService(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }
}
