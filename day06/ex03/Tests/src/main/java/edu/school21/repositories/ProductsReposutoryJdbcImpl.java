package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsReposutoryJdbcImpl implements ProductsRepository {
    private DataSource dataSource;

    public ProductsReposutoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> productList = new LinkedList<>();
        String query = "select * from products";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(query);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                productList.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) throws Exception {
        String query = "select * from products where id = ";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(query + id);

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next())
                return Optional.of(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")
                ));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void update(Product product) throws Exception {
        String query = "update products set name = ?, price = ? where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setInt(3, product.getId());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) throws Exception {
        String query = "insert into products (name, price) values (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        String query = "delete from products where id = ";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(query + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
