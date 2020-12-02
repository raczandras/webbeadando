package hu.andras.dao;

import hu.andras.dao.entity.*;
import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.exceptions.UnknownYearMonthException;
import hu.andras.model.YearMonth;
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
public class YearMonthDaoImpl implements YearMonthDao {
    private final YearMonthRepository yearMonthRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Collection<YearMonth> readAll() {
        return StreamSupport.stream(yearMonthRepository.findAll().spliterator(), false)
                .map(entity -> new YearMonth(
                        entity.getKey().getCustomer().getCustomerId(),
                        entity.getKey().getDate(),
                        entity.getConsumption()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createYearMonth(YearMonth yearMonth) throws UnknownCustomerException {
        YearMonthEntity yearMonthEntity;

        yearMonthEntity = YearMonthEntity.builder()
                .key( new YearMonthEntity.Key(queryCustomer(yearMonth.getCustomerId()), yearMonth.getDate() ))
                .consumption(yearMonth.getConsumption())
                .build();
        try{
            yearMonthRepository.save(yearMonthEntity);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }

    }

    protected CustomerEntity queryCustomer(int customerId) throws UnknownCustomerException {
        Optional<CustomerEntity> customerEntity = customerRepository.findByCustomerId(customerId).stream()
                .filter(entity -> entity.getCustomerId() == customerId)
                .findFirst();

        if(!customerEntity.isPresent()){
            throw new UnknownCustomerException("Unknown CustomerID: " + customerId);
        }
        return customerEntity.get();
    }

    @Override
    public void deleteYearMonth(YearMonth yearMonth) throws UnknownCustomerException, UnknownYearMonthException {
        Optional<YearMonthEntity> yearMonthEntity = yearMonthRepository.findByConsumptionAndKey_CustomerAndKey_Date(
                yearMonth.getConsumption(), queryCustomer(yearMonth.getCustomerId()) ,yearMonth.getDate())
                .stream()
                .filter(entity -> entity.getConsumption() == yearMonth.getConsumption() &&
                        entity.getKey().getCustomer().getCustomerId() == yearMonth.getCustomerId() &&
                        entity.getKey().getDate() == yearMonth.getDate())
                .findFirst();

        if(!yearMonthEntity.isPresent()){
            throw new UnknownYearMonthException("Unknown YearMonth: " + yearMonth.toString());
        }
        log.info("YearMonthEntity: {}", yearMonthEntity);

        try{
            yearMonthRepository.delete(yearMonthEntity.get());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(YearMonth yearMonth, double updatedConsumption) throws UnknownCustomerException, UnknownYearMonthException {
        Optional<YearMonthEntity> yearMonthEntity = yearMonthRepository.findByConsumptionAndKey_CustomerAndKey_Date(
                yearMonth.getConsumption(), queryCustomer(yearMonth.getCustomerId()) ,yearMonth.getDate())
                .stream()
                .filter(entity -> entity.getConsumption() == yearMonth.getConsumption() &&
                        entity.getKey().getCustomer().getCustomerId() == yearMonth.getCustomerId() &&
                        entity.getKey().getDate() == yearMonth.getDate())
                .findFirst();

        if(!yearMonthEntity.isPresent()){
            throw new UnknownYearMonthException("Unknown YearMonth: " + yearMonth.toString());
        }

        log.info("EREDETI: " + yearMonthEntity);
        yearMonthEntity.get().setConsumption(updatedConsumption);

        try{
            yearMonthRepository.save(yearMonthEntity.get());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }
}
