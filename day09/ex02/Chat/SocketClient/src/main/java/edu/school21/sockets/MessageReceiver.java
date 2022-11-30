package edu.school21.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class MessageReceiver extends Thread {
    private BufferedReader reader;

    public MessageReceiver(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {

        try {
            while (true) {
                String text = reader.readLine();

                if (text == null) {
                    System.out.println("Server is down...");
                    Main.sender.interrupt();
                    System.exit(0);
                }

                System.out.println(text);
            }
        } catch (Exception e) {
            try {
                reader.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        }
    }
}
