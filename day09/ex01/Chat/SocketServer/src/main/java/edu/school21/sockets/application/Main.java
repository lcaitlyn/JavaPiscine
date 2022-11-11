package edu.school21.sockets.application;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.server.Client;
import edu.school21.sockets.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Parameters
public class Main {
    public static void main(String[] args) {
        if (args.length != 1)
            throw new RuntimeException("Error! Add argument: java - jar target/socket-server.jar --port=8081");

        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        Server server = context.getBean(Server.class);

        JCommander.newBuilder().addObject(server).build().parse(args);
        server.start();
    }
}