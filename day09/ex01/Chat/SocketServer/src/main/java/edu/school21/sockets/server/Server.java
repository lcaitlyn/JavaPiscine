package edu.school21.sockets.server;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.config.SocketsApplicationConfig;
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

@Component
@Parameters(separators = "=")
public class Server {
    @Parameter(names = "--port")
    private int port;

    private ServerSocket serverSocket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private UsersService usersService;

    @Autowired
    public Server(UsersService usersService) {
        this.usersService = usersService;
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

    public void start() {
        try {
            serverSocket = new ServerSocket(port);

//            Client client = new Client(null);
//            client.run();
//            client.join();
//            Client client2 = new Client(null);
//            client.run();
//            client.join();
            System.out.println(Thread.currentThread().getName());
            while (true) {
                Socket socket = serverSocket.accept();
//                System.out.println("thread");
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Client client = new Client(socket);
//                        client.run();
//                    }
//                });
//                new Client(socket).run();
//                new Thread(client);
//                client.run();
//                client.join();
                new Thread(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        writeToClient("Hello from Server!");

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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

//                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
