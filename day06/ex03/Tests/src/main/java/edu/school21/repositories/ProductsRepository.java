package edu.school21.repositories;

import edu.school21.models.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductsRepository {
    List<Product> findAll() throws Exception;
    Optional<Product> findById(Long id) throws Exception;
    void update(Product product) throws Exception;
    void save(Product product) throws Exception;
    void delete (Long id) throws Exception;
}
