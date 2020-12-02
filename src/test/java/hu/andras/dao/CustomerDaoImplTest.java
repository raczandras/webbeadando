package hu.andras.dao;

import hu.andras.dao.entity.CustomerRepository;
import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerDaoImplTest {

    @Spy
    @InjectMocks
    private CustomerDaoImpl dao;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void createCustomerSuccessfull(){
        dao.createCustomer(getCustomer());

        verify(customerRepository,times(1)).save(any());
    }

    @Test
    public void deleteCustomerWithUnknownCustomer() throws UnknownCustomerException{
        doThrow(UnknownCustomerException.class).when(dao).deleteCustomer(any());

        assertThrows(UnknownCustomerException.class, ()->{
           dao.deleteCustomer(getCustomer());
        });
    }

    @Test
    public void updateCustomerWithUnknownCustomer() throws UnknownCustomerException{
        doThrow(UnknownCustomerException.class).when(dao).updateFirstMatch(any(), any());

        assertThrows(UnknownCustomerException.class, ()->{
           dao.updateFirstMatch(getCustomer(), getCustomer());
        });
    }

    private Customer getCustomer(){
        return new Customer(
                "HUN",
                "HUF"
        );
    }
}
