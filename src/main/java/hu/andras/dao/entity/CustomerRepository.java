package hu.andras.dao.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer>{
    Collection<CustomerEntity> findByCustomerId(int CustomerId);

    Collection<CustomerEntity> findByCurrencyAndSegment(String Currency, String Segment);
}
