package school21.spring.service.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import school21.spring.service.config.TestApplicationConfig;

@Component
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersServiceImplTest {
    private UsersService usersService;

    @BeforeEach
    void init() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        usersService = applicationContext.getBean("usersServiceJdbcTemplate", UsersServiceImpl.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test1@mail.ru","test2@mail.ru", "test3@mail.ru", "test4@mail.ru", "test5@mail.ru"})
    void usersServiceSuccessTest(String email) {
        Assertions.assertNotNull(usersService.signUp(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test1@mail.ru","test2@mail.ru", "test3@mail.ru", "test4@mail.ru", "test5@mail.ru"})
    void usersServiceExceptionTest(String email) {
        usersService.signUp(email);
        Assertions.assertThrowsExactly(RuntimeException.class, () -> usersService.signUp(email));
    }

    @Test
    void usersServiceNullTest() {
        Assertions.assertThrowsExactly(RuntimeException.class, () -> usersService.signUp(null));
    }
}
