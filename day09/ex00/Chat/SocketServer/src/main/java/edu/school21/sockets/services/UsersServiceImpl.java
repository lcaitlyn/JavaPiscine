package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(String username, String password) {
        if (username == null || username.isEmpty())
            throw new RuntimeException("Error! Username can't be null or empty!");

        if (password == null || password.isEmpty())
            throw new RuntimeException("Error! Password can't be null or empty!");

        if (usersRepository.findByUsername(username).isPresent())
            throw new RuntimeException("Error! Username is already exists!");

        usersRepository.save(new User(username, passwordEncoder.encode(password)));
    }
}
