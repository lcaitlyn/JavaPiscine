package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsReposutoryJdbcImplTest {
    private DataSource dataSource;
    private ProductsReposutoryJdbcImpl productsReposutoryJdbc;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1, "apple", 100),
            new Product(2, "banana", 105),
            new Product(3, "strawberry", 90),
            new Product(4, "peach", 80),
            new Product(5, "melon", 110),
            new Product(6, "watermelon", 140),
            new Product(7, "cherry", 150),
            new Product(8, "blackberry", 70),
            new Product(9, "gooseberry", 87),
            new Product(10, "qiwi", 81));

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(7, "cherry", 150);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(7, "cherryCherryLady", 228);
    final Product EXPECTED_SAVED_PRODUCT = new Product(11, "cherryCherryLady", 228);


    @BeforeEach
    void init() {
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsReposutoryJdbc = new ProductsReposutoryJdbcImpl(dataSource);
    }

    @Test
    void getConnectionTest() throws SQLException {
        Assertions.assertNotNull(dataSource.getConnection());
    }

    @Test
    void findAllTest() throws Exception {
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS.get(i)., productsReposutoryJdbc.findAll().get(i));
        }
//        System.out.println(EXPECTED_FIND_ALL_PRODUCTS);
//        System.out.println(productsReposutoryJdbc.findAll());
//        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsReposutoryJdbc.findAll());

    }

    @Test
    void findByIdTest() throws Exception {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsReposutoryJdbc.findById(7L));
    }

    @Test
    void updateTest() throws Exception {
        productsReposutoryJdbc.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsReposutoryJdbc.findById(7L).get());
    }

    @Test
    void saveTest() throws Exception {
        productsReposutoryJdbc.save(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, productsReposutoryJdbc.findById(11L).get());
    }

    @Test
    void delete() throws Exception {
        EXPECTED_FIND_ALL_PRODUCTS.remove(10);
        productsReposutoryJdbc.delete(10L);
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsReposutoryJdbc.findAll());
    }

    @AfterEach
    void close() {

    }
}
