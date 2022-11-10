package edu.school21.sockets.server;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Parameters(separators = "=")
public class Server {
    @Parameter(names = "--port")
    private int port;
    private ServerSocket serverSocket;
    private OutputStreamWriter writer;

    public Server() {

    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();
                writer = new OutputStreamWriter(socket.getOutputStream());

                writer.write("Hello from Server!\n");
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
