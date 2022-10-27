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

    // крч нужно mock() вот эту UsersRepository! Именно интерфейс!
    // Если mock() UserServiceImpl class, то можно будет только мокнуть его методы
    // Но в методе authenticate this->update и this->findByLogin НЕ БУДУТ мокнуты!!!
    // К ним не будут применятся правила.
    // короче если устал делать эту шляпу, то пиши мне


    // ахуенно после этих строк 👆 все поломалось нахуй. вообще хуй пойми как
    // перечитал самый гениальный сабджект и оказывается UserServiceImpl
    // не имплементится от интерфейса Ахуеть да?
    // короче у меня сгорела жопа. сори за мат. хотя кто это вообще читает 😌


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
