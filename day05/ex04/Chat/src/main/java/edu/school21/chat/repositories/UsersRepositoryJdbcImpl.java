package main.java.edu.school21.chat.repositories;

import main.java.edu.school21.chat.models.User;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // LIMIT - сколько вывести OFFSET - на сколько смещение
    // подробнее тута: https://sqlbolt.com/lesson/filtering_sorting_query_results
    // select * from chat.users limit 2 offset 2; <-- вот такую штуку замутить

    @Override
    public List<User> findAll(int page, int size) {
        return null;
    }
}
