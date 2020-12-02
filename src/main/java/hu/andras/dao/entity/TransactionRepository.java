package hu.andras.dao.entity;

import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.Collection;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    Collection<TransactionEntity> findByDateAndTimeAndCustomerAndCardIdAndGasStationAndProductAndAmountAndPrice(Date date, String time, CustomerEntity customerEntity,
                                                                                                                int cardId, GasStationEntity gasStationEntity,
                                                                                                                ProductEntity productEntity, int amount, double price);
}
