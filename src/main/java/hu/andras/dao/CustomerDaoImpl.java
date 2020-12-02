package hu.andras.dao;

import hu.andras.dao.entity.CustomerEntity;
import hu.andras.dao.entity.CustomerRepository;
import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.model.Customer;
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
public class CustomerDaoImpl implements CustomerDao {
    private final CustomerRepository customerRepository;

    @Override
    public Collection<Customer> readAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(entity -> new Customer(
                        entity.getSegment(),
                        entity.getCurrency()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createCustomer(Customer customer) {
        CustomerEntity customerEntity;

        customerEntity = CustomerEntity.builder()
                .segment(customer.getSegment())
                .currency(customer.getCurrency())
                .build();

        log.info("CustomerEntity: {}", customerEntity);
        try{
            customerRepository.save(customerEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }

    }

    @Override
    public void deleteCustomer(Customer customer) throws UnknownCustomerException {

        Optional<CustomerEntity> customerEntity = customerRepository.findByCurrencyAndSegment(customer.getCurrency(), customer.getSegment())
                .stream()
                .filter(entity -> entity.getCurrency().equals(customer.getCurrency()) && entity.getSegment().equals(customer.getSegment()))
                .findFirst();

        if(!customerEntity.isPresent()){
            throw new UnknownCustomerException("Unknown Customer: " + customer.toString());
        }

        log.info("CustomerEntity: {}", customerEntity);
        try{
            customerRepository.delete(customerEntity.get());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(Customer original, Customer updated) throws UnknownCustomerException {
        Optional<CustomerEntity> customerEntity = customerRepository.findByCurrencyAndSegment(original.getCurrency(), original.getSegment())
                .stream()
                .filter(entity -> entity.getCurrency().equals(original.getCurrency()) && entity.getSegment().equals(original.getSegment()))
                .findFirst();

        if( !customerEntity.isPresent()){
            throw new UnknownCustomerException("Unknown Customer: " + original.toString());
        }

        log.info("EREDETI: " + customerEntity.toString());
        customerEntity.get().setSegment(updated.getSegment());
        customerEntity.get().setCurrency(updated.getCurrency());
        log.info("UPDATELT: " + customerEntity.toString());

        try{
            customerRepository.save(customerEntity.get());
        } catch( Exception e){
            System.out.println(e.getMessage());
        }
    }

}
