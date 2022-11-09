package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import javax.sql.DataSource;
import java.util.Random;

@Component
public class UsersServiceImpl implements UsersService {
    @Autowired
    @Qualifier("usersRepositoryJdbcImpl")
    UsersRepository usersRepository;

    @Override
    public String signUp(String email) {
        Random random = new Random();
        char [] password = new char[10];

        for (int i = 0; i < 10; i++)
            password[i] = (char) (random.nextInt(89) + 33);

        usersRepository.save(new User(email, String.copyValueOf(password)));
        return String.copyValueOf(password);
    }
}
