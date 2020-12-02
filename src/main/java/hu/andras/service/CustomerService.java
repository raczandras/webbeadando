package hu.andras.service;

import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.model.Customer;

import java.util.Collection;

public interface CustomerService {
    Collection<Customer> getAllCustomers();

    void recordCustomer(Customer customer);
    void deleteCustomer(Customer customer) throws UnknownCustomerException;

    void updateFirstMatch(Customer original, Customer updated) throws UnknownCustomerException;
}
