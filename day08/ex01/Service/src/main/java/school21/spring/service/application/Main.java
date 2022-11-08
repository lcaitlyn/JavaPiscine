package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

public class Main {
    public static void makeActions(UsersRepository usersRepository) {
        User[] users = {
                new User(1L, "123zx@mail.com"),
                new User(2L, "234xcv@mail.com"),
                new User(3L, "345cvbr@mail.com"),
                new User(4L, "456vbnrt@mail.com"),
                new User(5L, "567bnmrfrg@mail.com"),
                new User(6L, "678nm,rgrg@mail.com"),
                new User(7L, "789m,.@mail.com"),
                new User(8L, "890qweasdfadf@mail.com"),
                new User(9L, "987tyfyj123@mail.com"),
                new User(10L, "765ihk345@mail.com"),
        };

        System.out.println("Происходит добавление в таблицу...");
        for (User u : users)
            usersRepository.save(u);

        System.out.println("Пользователи:");
        for (User u : usersRepository.findAll())
            System.out.println(u);

        System.out.println("\nОбновляем 5ого пользователя...");
        usersRepository.update(new User(5L, "updatedEmail@mail.com"));

        System.out.println("\nИщем 5ого пользователя:");
        System.out.println(usersRepository.findById(5L));

        System.out.println("\nУдаляем 5ого пользователя");
        System.out.println(usersRepository.findById(5L));
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbcImpl", UsersRepositoryJdbcImpl.class);
        makeActions(usersRepository);

        for (Long i = 1L; i < 11; i++) {
            usersRepository.delete(i);
        }
        System.out.println();

        usersRepository = context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepositoryJdbcTemplateImpl.class);
        makeActions(usersRepository);
    }
}
