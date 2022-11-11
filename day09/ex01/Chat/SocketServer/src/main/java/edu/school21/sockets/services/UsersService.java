package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import org.springframework.stereotype.Component;

public interface UsersService {
    void signUp(String username, String password);
    User signIn(String username, String password);
}
