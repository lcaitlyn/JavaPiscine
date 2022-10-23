package main.java.edu.school21.chat.repositories;

import main.java.edu.school21.chat.models.User;

import java.util.List;

public interface UsersRepository {
    List<User> findAll(int page, int size);
}
