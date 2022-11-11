package edu.school21.sockets;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = "--server-port")
    private static int port;

    private static void writeToServer(BufferedWriter writer, String message) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1)
            throw new RuntimeException("Error! Add argument: java - jar target/socket-server.jar --server-port=8081");

        JCommander.newBuilder().addObject(new Main()).build().parse(args);
            // autoclosable
        try (Socket socket = new Socket("localhost", port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             Scanner scanner = new Scanner(System.in);) {

            System.out.println(reader.readLine());

            while (true) {
                String message = scanner.nextLine();
                writeToServer(writer, message);
                message = reader.readLine();
                System.out.println(message);
                if (message.equalsIgnoreCase("Enter username:"))
                    break;
            }

            writeToServer(writer, scanner.nextLine());
            System.out.println(reader.readLine());
            writeToServer(writer, scanner.nextLine());
            System.out.println(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}