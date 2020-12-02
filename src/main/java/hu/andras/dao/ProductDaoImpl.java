package hu.andras.dao;

import hu.andras.dao.entity.ProductEntity;
import hu.andras.dao.entity.ProductRepository;
import hu.andras.exceptions.UnknownProductException;
import hu.andras.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {
    private final ProductRepository productRepository;

    @Override
    public Collection<Product> readAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(entity -> new Product(
                        entity.getDescription()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createProduct(Product product) {
        ProductEntity productEntity;

        productEntity = ProductEntity.builder()
                .description(product.getDescription())
                .build();

        log.info("ProductEntity: {}", productEntity);
        try{
            productRepository.save(productEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteProduct(Product product) throws UnknownProductException {
        Optional<ProductEntity> productEntity = productRepository.findByDescription(product.getDescription())
                .stream()
                .filter(entity -> entity.getDescription().equals(product.getDescription()))
                .findFirst();
        if(!productEntity.isPresent()){
            throw new UnknownProductException("Unknown Product: " + product.getDescription());
        }
        log.info("ProductEntity: {}", productEntity);
        try{
            productRepository.delete(productEntity.get());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(Product product, Product updatedProduct) throws UnknownProductException {
        Optional<ProductEntity> productEntity = productRepository.findByDescription(product.getDescription())
                .stream()
                .filter(entity -> entity.getDescription().equals(product.getDescription()))
                .findFirst();

        if(!productEntity.isPresent()){
            throw new UnknownProductException("Unknown Product: " + product.getDescription());
        }

        log.info("EREDETI: " + productEntity);
        productEntity.get().setDescription(updatedProduct.getDescription());
        log.info("UPDATELT: " + productEntity);

        try{
            productRepository.save(productEntity.get());
        } catch( Exception e){
            log.error(e.getMessage());
        }
    }
}
