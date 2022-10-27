package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class EmbeddedDataSourceTest {
    private DataSource dataSource;

    @BeforeEach
    void init() {
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Test
    void getConnectionTest() throws SQLException {
        Assertions.assertNotNull(dataSource.getConnection());
    }
}
