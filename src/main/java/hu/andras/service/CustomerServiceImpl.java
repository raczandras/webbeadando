package hu.andras.service;

import hu.andras.dao.CustomerDao;
import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.model.Customer;
import hu.andras.model.CustomerOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Override
    public Collection<CustomerOut> getAllCustomers() {
        return customerDao.readAll();
    }

    @Override
    public void recordCustomer(Customer customer) {
        customerDao.createCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) throws UnknownCustomerException {
        customerDao.deleteCustomer(customer);
    }

    @Override
    public void updateFirstMatch(Customer original, Customer updated) throws UnknownCustomerException {
        customerDao.updateFirstMatch( original, updated);
    }
}
