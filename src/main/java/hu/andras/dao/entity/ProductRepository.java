package hu.andras.dao.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    Collection<ProductEntity> findByDescription(String description);
}
