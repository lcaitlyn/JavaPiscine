package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.repositories.UsersRepository;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersServiceImplTest {
    private final String RIGHT_LOGIN = "right_login";
    private final String WRONG_LOGIN = "wrong_login";
    private final String RIGHT_PASS = "right_pass";
    private final String WRONG_PASS = "wrong_pass";

    private User user;
    private UsersRepository usersRepositoryMock = Mockito.mock(UsersRepository.class);
    private UsersServiceImpl usersServiceMock = new UsersServiceImpl(usersRepositoryMock);

    @BeforeEach
    void beforeEach() {
        user = new User(228, RIGHT_LOGIN, RIGHT_PASS, false);
        Mockito.when(usersRepositoryMock.findByLogin(RIGHT_LOGIN)).thenReturn(user);
        Mockito.when(usersRepositoryMock.findByLogin(WRONG_LOGIN)).thenReturn(null);
    }

    @Test
    void authenticateEntityNotFoundExceptionTest() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> usersServiceMock.authenticate(WRONG_LOGIN, WRONG_PASS));
    }

    @Test
    void authenticateAlreadyAuthenticatedExceptionTest() {
        User authenticateUser = user;
        authenticateUser.setAuthenticated(true);

        Assertions.assertThrows(AlreadyAuthenticatedException.class, () -> usersServiceMock.authenticate(RIGHT_LOGIN, RIGHT_PASS));
    }

    @Test
    void authenticateFalseWrongPasswordTest() throws Exception {
        Assertions.assertFalse(usersServiceMock.authenticate(RIGHT_LOGIN, WRONG_PASS));
    }

    @Test
    void authenticateTrueWrongPasswordTest() throws Exception {
        Assertions.assertTrue(usersServiceMock.authenticate(RIGHT_LOGIN, RIGHT_PASS));
    }
}
