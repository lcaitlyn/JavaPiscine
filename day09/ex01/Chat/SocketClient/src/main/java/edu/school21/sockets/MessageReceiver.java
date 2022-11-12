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
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                reader.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
