package hu.andras.dao.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface YearMonthRepository extends CrudRepository<YearMonthEntity, Integer> {
    Collection<YearMonthEntity> findByConsumptionAndKey_CustomerAndKey_Date(double Consumption, CustomerEntity customerEntity, int Date);
}
