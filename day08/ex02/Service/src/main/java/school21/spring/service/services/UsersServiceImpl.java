package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.repositories.UsersRepository;

import javax.sql.DataSource;

@Component
public class UsersServiceImpl implements UsersService {
    @Autowired
    @Qualifier("usersRepositoryJdbcImpl")
    UsersRepository usersRepository;

    @Override
    public String signUp(String email) {
        String p
    }
}
