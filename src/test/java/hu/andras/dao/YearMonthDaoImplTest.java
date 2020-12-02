package hu.andras.dao;

import hu.andras.dao.entity.CustomerEntity;
import hu.andras.dao.entity.CustomerRepository;
import hu.andras.dao.entity.YearMonthRepository;
import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.exceptions.UnknownYearMonthException;
import hu.andras.model.YearMonth;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class YearMonthDaoImplTest {

    @Spy
    @InjectMocks
    private YearMonthDaoImpl dao;
    @Mock
    private YearMonthRepository yearMonthRepository;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void createYearMonthSuccessfull() throws UnknownCustomerException{
        doReturn(CustomerEntity.builder().customerId(1).build())
                .when(dao).queryCustomer(anyInt());

        dao.createYearMonth(getYearMonth());

        verify(yearMonthRepository,times(1)).save(any());
    }

    @Test
    public void createYearMonthWithUnknownCustomer() throws UnknownCustomerException{
        doThrow(UnknownCustomerException.class).when(dao).queryCustomer(anyInt());

        assertThrows(UnknownCustomerException.class, ()->{
                dao.createYearMonth(getYearMonth());
        });
    }


    @Test
    public void deleteYearMonthWithUnknownCustomer() throws UnknownYearMonthException, UnknownCustomerException{
        doThrow(UnknownCustomerException.class).when(dao).queryCustomer(anyInt());

        assertThrows(UnknownCustomerException.class, ()->{
            dao.deleteYearMonth(getYearMonth());
        });
    }

    @Test
    public void deleteYearMonthWithUnknownYearMonth() throws UnknownYearMonthException, UnknownCustomerException{
        doThrow(UnknownYearMonthException.class).when(dao).deleteYearMonth(any());

        assertThrows(UnknownYearMonthException.class, ()->{
            dao.deleteYearMonth(getYearMonth());
        });
    }

    @Test
    public void updateYearMonthWithUnknownCustomer() throws UnknownCustomerException, UnknownYearMonthException{
        doThrow(UnknownYearMonthException.class).when(dao).updateFirstMatch(any(), anyDouble());

        double updatedConsumption = 500.55;

        assertThrows(UnknownYearMonthException.class, ()->{
           dao.updateFirstMatch(getYearMonth(), updatedConsumption);
        });
    }

    @Test
    public void updateYearMonthWithUnknownYearMonth() throws UnknownYearMonthException, UnknownCustomerException{
        doThrow(UnknownCustomerException.class).when(dao).queryCustomer(anyInt());

        double updatedConsumption = 500.55;

        assertThrows(UnknownCustomerException.class, ()->{
           dao.updateFirstMatch(getYearMonth(), updatedConsumption);
        });
    }

    private YearMonth getYearMonth(){
        return new YearMonth(
                5,
                201207,
                528.30
        );
    }

}
