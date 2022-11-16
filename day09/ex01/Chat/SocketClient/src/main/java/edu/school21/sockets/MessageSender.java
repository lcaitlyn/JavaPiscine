package edu.school21.sockets;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class MessageSender extends Thread {
    BufferedWriter writer;

    public MessageSender(BufferedWriter writer) {
        this.writer = writer;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        while (!text.equalsIgnoreCase("exit")) {
            writeToServer(text);
            text = scanner.nextLine();
        }
        System.out.println("You have left the chat");

        closeWriter();
        System.exit(0);
    }

    private void writeToServer(String message) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Server is down...");
            closeWriter();
            System.exit(0);
        }
    }

    private void closeWriter() {
        try {
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
