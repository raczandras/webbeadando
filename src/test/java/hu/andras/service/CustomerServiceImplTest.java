package hu.andras.service;

import hu.andras.dao.CustomerDao;
import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl service;

    @Mock
    private CustomerDao dao;

    @Test
    public void recordCustomerSuccessful(){
        Customer customer = getCustomer();
        service.recordCustomer(customer);

        verify(dao, times(1)).createCustomer(customer);
    }

    @Test
    public void deleteCustomerSuccessful() throws UnknownCustomerException {
        Customer customer = getCustomer();

        service.deleteCustomer(customer);
        verify(dao, times(1)).deleteCustomer(customer);
    }

    @Test
    public void deleteCustomerWithUnknownCustomer() throws UnknownCustomerException{
        doThrow(UnknownCustomerException.class).when(dao).deleteCustomer(any());

        assertThrows(UnknownCustomerException.class, ()->{
           service.deleteCustomer(getCustomer());
        });
    }

    @Test
    public void updateCustomerSuccessful() throws UnknownCustomerException{
        Customer customer = getCustomer();
        Customer updatedCustomer = getUpdatedCustomer();

        service.updateFirstMatch(customer, updatedCustomer);

        verify(dao, times(1)).updateFirstMatch(customer, updatedCustomer);
    }

    @Test
    public void updateCustomerWithUnknownCustomer() throws UnknownCustomerException{
        doThrow(UnknownCustomerException.class).when(dao).updateFirstMatch(any(), any());

        assertThrows(UnknownCustomerException.class, ()->{
           service.updateFirstMatch(getCustomer(), getUpdatedCustomer());
        });
    }

    @Test
    public void readAllCustomers(){
        when(dao.readAll()).thenReturn(getDefaultCustomers());

        Collection<Customer> actual = service.getAllCustomers();

        assertThat(getDefaultCustomers(), is(actual));
    }


    private Customer getCustomer(){
        return new Customer(
                "SME",
                "EUR"
        );
    }

    private Customer getUpdatedCustomer(){
        return new Customer(
                "HUN",
                "HUF"
        );
    }

    private Collection<Customer> getDefaultCustomers(){
        return Arrays.asList(
            new Customer(
                  "SME",
                  "EUR"
            ),
            new Customer(
                    "HUN",
                    "HUF"
            ),
            new Customer(
                    "ENG",
                    "GPB"
            ));
    }
}
