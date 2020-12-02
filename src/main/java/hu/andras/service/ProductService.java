package hu.andras.service;

import hu.andras.exceptions.UnknownProductException;
import hu.andras.model.Product;

import java.util.Collection;

public interface ProductService {
    Collection<Product> getAllProducts();

    void recordProduct(Product product);
    void deleteProduct(Product product) throws UnknownProductException;

    void updadeFirstMatch(Product product, Product updatedProduct) throws UnknownProductException;
}
