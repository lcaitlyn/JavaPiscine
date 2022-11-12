package edu.school21.sockets;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.*;
import java.net.Socket;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = "--server-port")
    private static int port;

    public static void main(String[] args) {
        if (args.length != 1)
            throw new RuntimeException("Error! Add argument: java - jar target/socket-server.jar --server-port=8081");

        JCommander.newBuilder().addObject(new Main()).build().parse(args);

        try {
            Socket socket = new Socket("localhost", port);
            MessageReceiver receiver = new MessageReceiver(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            MessageSender sender = new MessageSender(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            receiver.start();
            sender.start();

            receiver.join();
            sender.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}