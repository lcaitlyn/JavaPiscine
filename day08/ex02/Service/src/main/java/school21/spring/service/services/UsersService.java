package school21.spring.service.services;

import org.springframework.stereotype.Component;

@Component
public interface UsersService {
    String signUp(String email);
}
