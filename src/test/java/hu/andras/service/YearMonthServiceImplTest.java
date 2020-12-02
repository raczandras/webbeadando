package hu.andras.service;

import hu.andras.dao.YearMonthDao;
import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.exceptions.UnknownYearMonthException;
import hu.andras.model.YearMonth;
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
public class YearMonthServiceImplTest {

    @InjectMocks
    private YearMonthServiceImpl service;

    @Mock
    private YearMonthDao dao;

    @Test
    public void recordYearMonthSuccessful() throws UnknownCustomerException {
        YearMonth yearMonth = getYearMonth();
        service.recordYearMonth(yearMonth);

        verify(dao, times(1)).createYearMonth(yearMonth);
    }

    @Test
    public void recordYearMonthWithUnknownCustomer() throws UnknownCustomerException{
        doThrow(UnknownCustomerException.class).when(dao).createYearMonth(any());

        assertThrows(UnknownCustomerException.class, ()->{
            service.recordYearMonth(getYearMonth());
        });
    }

    @Test
    public void deleteYearMonthSuccessful() throws UnknownYearMonthException, UnknownCustomerException {
        YearMonth yearMonth = getYearMonth();

        service.deleteYearMonth(yearMonth);
        verify(dao, times(1)).deleteYearMonth(yearMonth);
    }

    @Test
    public void deleteYearMonthWithUnknownProduct() throws UnknownCustomerException, UnknownYearMonthException{
        doThrow(UnknownCustomerException.class).when(dao).deleteYearMonth(any());

        assertThrows(UnknownCustomerException.class, ()->{
           service.deleteYearMonth(getYearMonth());
        });
    }

    @Test
    public void deleteYearMonthWithUnknownYearMonth() throws UnknownYearMonthException, UnknownCustomerException {
        doThrow(UnknownYearMonthException.class).when(dao).deleteYearMonth(any());

        assertThrows(UnknownYearMonthException.class, ()->{
            service.deleteYearMonth(getYearMonth());
        });
    }

    @Test
    public void updateYearMonthSuccessful() throws UnknownYearMonthException, UnknownCustomerException{
        YearMonth yearMonth = getYearMonth();
        double updatedConsumption = 500.55;

        service.updateFirstMatch(yearMonth, updatedConsumption);

        verify(dao, times(1)).updateFirstMatch(yearMonth, updatedConsumption );
    }

    @Test
    public void updateYearMonthWithUnknownYearMonth() throws UnknownYearMonthException, UnknownCustomerException{
        doThrow(UnknownYearMonthException.class).when(dao).updateFirstMatch(any(), anyDouble());
        double updatedConsumption = 500.55;

        assertThrows(UnknownYearMonthException.class, ()->{
            service.updateFirstMatch( getYearMonth(), updatedConsumption);
        });
    }

    @Test
    public void updateYearMonthWithUnknownCustomer() throws UnknownYearMonthException, UnknownCustomerException{
        doThrow(UnknownCustomerException.class).when(dao).updateFirstMatch(any(), anyDouble());
        double updatedConsumption = 500.55;

        assertThrows(UnknownCustomerException.class, ()->{
            service.updateFirstMatch( getYearMonth(), updatedConsumption);
        });
    }

    @Test
    public void readAllYearMonths(){
        when(dao.readAll()).thenReturn(getDefaultYearMonths());

        Collection<YearMonth> actual = service.getAllYearMonths();

        assertThat(getDefaultYearMonths(), is(actual));
    }


    private YearMonth getYearMonth(){
        return new YearMonth(
                5,
                201202,
                500.55
        );
    }

    private Collection<YearMonth> getDefaultYearMonths(){
        return Arrays.asList(
                new YearMonth(
                        7,
                        200905,
                        60.12
                ),
                new YearMonth(
                        5,
                        201202,
                        500.55
                ),
                new YearMonth(
                        2,
                        190909,
                        113.45
                ));
    }
}
