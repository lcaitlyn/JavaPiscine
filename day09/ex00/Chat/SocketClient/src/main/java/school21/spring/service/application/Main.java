package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersServiceImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UsersServiceImpl usersService = applicationContext.getBean("usersServiceImpl", UsersServiceImpl.class);
        System.out.println(usersService.signUp("test1@mail.ru"));
        System.out.println(usersService.signUp("test2@mail.ru"));
        System.out.println(usersService.signUp("test3@mail.ru"));
        try {
            System.out.println(usersService.signUp("test3@mail.ru"));
        } catch (Exception e) {
            System.err.println("Нельзя добавить! Так как такой email существуют");
        }
    }
}