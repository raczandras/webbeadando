package hu.andras.dao;

import hu.andras.exceptions.UnknownProductException;
import hu.andras.model.Product;
import java.util.Collection;

public interface ProductDao {
    Collection<Product> readAll();

    void createProduct(Product product);
    void deleteProduct(Product product) throws UnknownProductException;

    void updateFirstMatch(Product product, Product updatedProduct) throws UnknownProductException;
}