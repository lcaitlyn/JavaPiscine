package school21.spring.service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;

import javax.sql.DataSource;


@Configuration
@ComponentScan("school21.spring.service")
public class TestApplicationConfig {
    @Bean
    DataSource dataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("table.sql")
                .build();

        return dataSource;
    }

    @Bean("usersServiceJdbc")
    UsersService usersServiceJdbc(DataSource dataSource) {
        return new UsersServiceImpl(new UsersRepositoryJdbcImpl(dataSource));
    }

    @Bean("usersServiceJdbcTemplate")
    UsersService usersServiceJdbcTemplate(DataSource dataSource) {
        return new UsersServiceImpl(new UsersRepositoryJdbcTemplateImpl(dataSource));
    }

//    @Bean("hsqlDataSource")
//    DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.HSQL)
//                .addScript("table.sql")
//                .build();
//    }
//
//    @Bean("usersRepositoryJdbc")
//    UsersRepositoryJdbcImpl usersRepositoryJdbc() {
//        return new UsersRepositoryJdbcImpl(dataSource());
//    }
//
//    @Bean("usersRepositoryJdbcTemplate")
//    UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate() {
//        return new UsersRepositoryJdbcTemplateImpl(dataSource());
//    }
//
//    @Bean("usersServiceJdbc")
//    UsersServiceImpl usersServiceJdbc() {
//        return new UsersServiceImpl(usersRepositoryJdbc());
//    }
//
//    @Bean("usersServiceJdbcTemplate")
//    UsersServiceImpl usersServiceJdbcTemplate() {
//        return new UsersServiceImpl(usersRepositoryJdbcTemplate());
//    }
}
