package hu.andras.service;

import hu.andras.dao.ProductDao;
import hu.andras.exceptions.UnknownProductException;
import hu.andras.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService {

    private final ProductDao productDao;

    @Override
    public Collection<Product> getAllProducts() {
        return productDao.readAll();
    }

    @Override
    public void recordProduct(Product product) {
        productDao.createProduct(product);
    }

    @Override
    public void deleteProduct(Product product) throws UnknownProductException {
        productDao.deleteProduct(product);
    }

    @Override
    public void updadeFirstMatch(Product product, Product updatedProduct) throws UnknownProductException {
        productDao.updateFirstMatch(product, updatedProduct);
    }
}
