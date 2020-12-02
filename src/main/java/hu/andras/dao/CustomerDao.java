package hu.andras.dao;

import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.model.Customer;
import java.util.Collection;

public interface CustomerDao {
    Collection<Customer> readAll();
    void createCustomer(Customer customer);
    void deleteCustomer(Customer customer) throws UnknownCustomerException;

    void updateFirstMatch(Customer original, Customer updated) throws UnknownCustomerException;
}
