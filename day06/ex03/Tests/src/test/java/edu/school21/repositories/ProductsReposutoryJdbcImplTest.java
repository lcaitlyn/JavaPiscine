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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProductsReposutoryJdbcImplTest {
    private DataSource dataSource;
    private ProductsReposutoryJdbcImpl productsReposutoryJdbc;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0, "apple", 100),
            new Product(1, "banana", 105),
            new Product(2, "strawberry", 90),
            new Product(3, "peach", 80),
            new Product(4, "melon", 110),
            new Product(5, "watermelon", 140),
            new Product(6, "cherry", 150),
            new Product(7, "blackberry", 70),
            new Product(8, "gooseberry", 87),
            new Product(9, "qiwi", 81));

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(6, "cherry", 150);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(6, "cherryCherryLady", 228);
    final Product EXPECTED_SAVED_PRODUCT = new Product(10, "cherryCherryLady", 228);

    public static void assertProducts(Product expected, Product actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
    }

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
        List<Product> actualList = productsReposutoryJdbc.findAll();

        for (int i = 0; i < 10; i++) {
            assertProducts(EXPECTED_FIND_ALL_PRODUCTS.get(i), actualList.get(i));
        }
    }

    @Test
    void findByIdTest() throws Exception {
        assertProducts(EXPECTED_FIND_BY_ID_PRODUCT, productsReposutoryJdbc.findById(6L).get());
    }

    @Test
    void updateTest() throws Exception {
        productsReposutoryJdbc.update(EXPECTED_UPDATED_PRODUCT);

        assertProducts(EXPECTED_UPDATED_PRODUCT, productsReposutoryJdbc.findById(6L).get());
    }

    @Test
    void saveTest() throws Exception {
        productsReposutoryJdbc.save(EXPECTED_UPDATED_PRODUCT);

        assertProducts(EXPECTED_SAVED_PRODUCT, productsReposutoryJdbc.findById(10L).get());
    }

    @Test
    void delete() throws Exception {
        LinkedList<Product> expectedList = new LinkedList<Product>();
        expectedList.addAll(EXPECTED_FIND_ALL_PRODUCTS);

        expectedList.removeLast();
        productsReposutoryJdbc.delete(9L);

        List<Product> actualList = productsReposutoryJdbc.findAll();

        for (int i = 0; i < expectedList.size(); i++) {
            assertProducts(expectedList.get(i), actualList.get(i));
        }
    }
}
