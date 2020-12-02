package hu.andras.dao;

import hu.andras.dao.entity.*;
import hu.andras.exceptions.*;
import hu.andras.model.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TransactionDaoImplTest {

    @Spy
    @InjectMocks
    private TransactionDaoImpl dao;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    public void createTransactionSuccessfull() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, WrongTimeFormatException{

        doReturn(CustomerEntity.builder().customerId(1).build())
                .when(dao).queryCustomer(anyInt());

        doReturn(GasStationEntity.builder().gasStationId(1).build())
                .when(dao).queryGasStation(anyInt());

        doReturn(ProductEntity.builder().productId(2).build())
                .when(dao).queryProduct(anyString());

        dao.createTransaction(getTransaction());

        verify(transactionRepository,times(1)).save(any());

    }

    @Test
    public void createTransactionWithWrongTimeFormat() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, WrongTimeFormatException{

        doThrow(WrongTimeFormatException.class).when(dao).checkTimeFormat(anyString());

        assertThrows(WrongTimeFormatException.class, ()->{
           dao.createTransaction(getWrongTimedTransaction());
        });
    }

    @Test
    public void createTransactionWithUnknownProduct() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, WrongTimeFormatException{
        doReturn(CustomerEntity.builder().customerId(1).build())
                .when(dao).queryCustomer(anyInt());

        doReturn(GasStationEntity.builder().gasStationId(1).build())
                .when(dao).queryGasStation(anyInt());

        doThrow(UnknownProductException.class).when(dao).queryProduct(anyString());

        assertThrows(UnknownProductException.class, ()->{
           dao.createTransaction(getTransaction());
        });
    }

    @Test
    public void createTransactionWithUnknownCustomer() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, WrongTimeFormatException{

        doThrow(UnknownCustomerException.class).when(dao).queryCustomer(anyInt());

        assertThrows(UnknownCustomerException.class, ()->{
           dao.createTransaction(getTransaction());
        });
    }

    @Test
    public void createTransactionWithUnknownGasStation() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, WrongTimeFormatException{
        doReturn(CustomerEntity.builder().customerId(1).build())
                .when(dao).queryCustomer(anyInt());

        doThrow(UnknownGasStationException.class).when(dao).queryGasStation(anyInt());

        assertThrows(UnknownGasStationException.class, ()->{
            dao.createTransaction(getTransaction());
        });
    }


    @Test
    public void deleteTransactionWithWrongTimeFormat() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException{
        doThrow(WrongTimeFormatException.class).when(dao).checkTimeFormat(anyString());

        assertThrows(WrongTimeFormatException.class, ()->{
            dao.deleteTransaction(getWrongTimedTransaction());
        });
    }

    @Test
    public void deleteTransactionWithUnknownProduct() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException{
        doReturn(CustomerEntity.builder().customerId(1).build())
                .when(dao).queryCustomer(anyInt());

        doReturn(GasStationEntity.builder().gasStationId(1).build())
                .when(dao).queryGasStation(anyInt());

        doThrow(UnknownProductException.class).when(dao).queryProduct(anyString());

        assertThrows(UnknownProductException.class, ()->{
            dao.deleteTransaction(getTransaction());
        });
    }

    @Test
    public void deleteTransactionWithUnknownCustomer() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException{
        doThrow(UnknownCustomerException.class).when(dao).queryCustomer(anyInt());

        assertThrows(UnknownCustomerException.class, ()->{
            dao.deleteTransaction(getTransaction());
        });
    }

    @Test
    public void deleteTransactionWithUnknownGasStation() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException{
        doReturn(CustomerEntity.builder().customerId(1).build())
                .when(dao).queryCustomer(anyInt());

        doThrow(UnknownGasStationException.class).when(dao).queryGasStation(anyInt());

        assertThrows(UnknownGasStationException.class, ()->{
            dao.deleteTransaction(getTransaction());
        });
    }

    @Test
    public void deleteTransactionWithUnknownTransaction() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException{
        doThrow(UnknownTransactionException.class).when(dao).deleteTransaction(any());

        assertThrows(UnknownTransactionException.class, ()->{
            dao.deleteTransaction(getTransaction());
        });
    }

    @Test
    public void updateTransactionWithWrongTimeFormat() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException{
        doThrow(WrongTimeFormatException.class).when(dao).checkTimeFormat(anyString());

        assertThrows(WrongTimeFormatException.class, ()->{
            dao.updateFirstMatch(getWrongTimedTransaction(), getTransaction());
        });
    }

    @Test
    public void updateTransactionWithUnknownProduct() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException{
        doReturn(CustomerEntity.builder().customerId(1).build())
                .when(dao).queryCustomer(anyInt());

        doReturn(GasStationEntity.builder().gasStationId(1).build())
                .when(dao).queryGasStation(anyInt());

        doThrow(UnknownProductException.class).when(dao).queryProduct(anyString());

        assertThrows(UnknownProductException.class, ()->{
            dao.updateFirstMatch(getTransaction(), getTransaction());
        });
    }

    @Test
    public void updateTransactionWithUnknownCustomer() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException{
        doThrow(UnknownCustomerException.class).when(dao).queryCustomer(anyInt());

        assertThrows(UnknownCustomerException.class, ()->{
            dao.updateFirstMatch(getTransaction(), getTransaction());
        });
    }

    @Test
    public void updateTransactionWithUnknownGasStation() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException{
        doReturn(CustomerEntity.builder().customerId(1).build())
                .when(dao).queryCustomer(anyInt());

        doThrow(UnknownGasStationException.class).when(dao).queryGasStation(anyInt());

        assertThrows(UnknownGasStationException.class, ()->{
            dao.updateFirstMatch(getTransaction(), getTransaction());
        });
    }

    @Test
    public void updateTransactionWithUnknownTransaction() throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException{
        doThrow(UnknownTransactionException.class).when(dao).updateFirstMatch(any(), any());

        assertThrows(UnknownTransactionException.class, ()->{
            dao.updateFirstMatch(getTransaction(), getTransaction());
        });
    }

    private Transaction getTransaction(){
        return new Transaction(
                Date.valueOf("2020-12-12"),
                "14:42:00",
                1,
                15,
                1,
                "Nafta",
                500,
                394.49
        );
    }

    private Transaction getWrongTimedTransaction(){
        return new Transaction(
                Date.valueOf("2020-12-12"),
                "Wrong",
                5,
                15,
                44,
                "Nafta",
                500,
                394.49
        );
    }

}
