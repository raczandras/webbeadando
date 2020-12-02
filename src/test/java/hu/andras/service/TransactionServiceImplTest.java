package hu.andras.service;

import hu.andras.dao.TransactionDao;
import hu.andras.exceptions.*;
import hu.andras.model.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {
    @InjectMocks
    private TransactionServiceImpl service;

    @Mock
    private TransactionDao dao;

    @Test
    public void recordTransactionSuccessful() throws WrongTimeFormatException, UnknownProductException, UnknownCustomerException, UnknownGasStationException {
        Transaction transaction = getTransaction();
        service.recordTransaction(transaction);

        verify(dao, times(1)).createTransaction(transaction);
    }

    @Test
    public void recordTransactionWithWrongTimeFormat() throws WrongTimeFormatException, UnknownProductException, UnknownCustomerException, UnknownGasStationException {
        doThrow(WrongTimeFormatException.class).when(dao).createTransaction(any());

        assertThrows(WrongTimeFormatException.class, ()->{
            service.recordTransaction(getWrongTimedTransaction());
        });
    }

    @Test
    public void recordTransactionWithUnknownProduct() throws WrongTimeFormatException, UnknownProductException, UnknownCustomerException, UnknownGasStationException {
        doThrow(UnknownProductException.class).when(dao).createTransaction(any());

        assertThrows(UnknownProductException.class, ()->{
            service.recordTransaction(getTransaction());
        });
    }

    @Test
    public void recordTransactionWithUnknownCustomer() throws WrongTimeFormatException, UnknownProductException, UnknownCustomerException, UnknownGasStationException {
        doThrow(UnknownCustomerException.class).when(dao).createTransaction(any());

        assertThrows(UnknownCustomerException.class, ()->{
            service.recordTransaction(getTransaction());
        });
    }

    @Test
    public void recordTransactionWithUnknownGasStation() throws WrongTimeFormatException, UnknownProductException, UnknownCustomerException, UnknownGasStationException {
        doThrow(UnknownGasStationException.class).when(dao).createTransaction(any());

        assertThrows(UnknownGasStationException.class, ()->{
            service.recordTransaction(getTransaction());
        });
    }

    @Test
    public void deleteTransactionSuccessful() throws WrongTimeFormatException, UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException {
        Transaction transaction = getTransaction();
        service.deleteTransaction(transaction);

        verify(dao, times(1)).deleteTransaction(transaction);
    }

    @Test
    public void deleteTransactionWithWrongTimeFormat() throws WrongTimeFormatException, UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException {
        doThrow(WrongTimeFormatException.class).when(dao).deleteTransaction(any());

        assertThrows(WrongTimeFormatException.class, ()->{
            service.deleteTransaction(getWrongTimedTransaction());
        });
    }

    @Test
    public void deleteTransactionWithUnknownProduct() throws WrongTimeFormatException, UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException {
        doThrow(UnknownProductException.class).when(dao).deleteTransaction(any());

        assertThrows(UnknownProductException.class, ()->{
            service.deleteTransaction(getTransaction());
        });
    }

    @Test
    public void deleteTransactionWithUnknownCustomer() throws WrongTimeFormatException, UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException {
        doThrow(UnknownCustomerException.class).when(dao).deleteTransaction(any());

        assertThrows(UnknownCustomerException.class, ()->{
            service.deleteTransaction(getTransaction());
        });
    }

    @Test
    public void deleteTransactionWithUnknownGasStation() throws WrongTimeFormatException, UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException {
        doThrow(UnknownGasStationException.class).when(dao).deleteTransaction(any());

        assertThrows(UnknownGasStationException.class, ()->{
           service.deleteTransaction(getTransaction());
        });
    }

    @Test
    public void deleteTransactionWithUnknownTransaction() throws WrongTimeFormatException, UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException {
        doThrow(UnknownTransactionException.class).when(dao).deleteTransaction(any());

        assertThrows(UnknownTransactionException.class, ()->{
            service.deleteTransaction(getTransaction());
        });
    }

    @Test
    public void updateTransactionSuccessFull() throws WrongTimeFormatException, UnknownTransactionException, UnknownProductException, UnknownCustomerException, UnknownGasStationException {
        Transaction transaction = getTransaction();
        Transaction updatedTransaction = getUpdatedTransaction();

        service.updateTransaction(transaction, updatedTransaction);

        verify(dao, times(1)).updateFirstMatch(transaction, updatedTransaction );
    }

    @Test
    public void updateTransactionWithWrongTimeFormat() throws WrongTimeFormatException, UnknownTransactionException, UnknownProductException, UnknownCustomerException, UnknownGasStationException {
        doThrow(WrongTimeFormatException.class).when(dao).updateFirstMatch(any(), any());

        assertThrows(WrongTimeFormatException.class, ()->{
            service.updateTransaction(getTransaction(), getUpdatedTransaction());
        });
    }

    @Test
    public void updateTransactionWithUnknownTransaction() throws WrongTimeFormatException, UnknownTransactionException, UnknownProductException, UnknownCustomerException, UnknownGasStationException {
        doThrow(UnknownTransactionException.class).when(dao).updateFirstMatch(any(), any());

        assertThrows(UnknownTransactionException.class, ()->{
            service.updateTransaction(getTransaction(), getUpdatedTransaction());
        });
    }

    @Test
    public void updateTransactionWithUnknownProduct() throws WrongTimeFormatException, UnknownTransactionException, UnknownProductException, UnknownCustomerException, UnknownGasStationException {
        doThrow(UnknownProductException.class).when(dao).updateFirstMatch(any(), any());

        assertThrows(UnknownProductException.class, ()->{
            service.updateTransaction(getTransaction(), getUpdatedTransaction());
        });
    }

    @Test
    public void updateTransactionWithUnknownCustomer() throws WrongTimeFormatException, UnknownTransactionException, UnknownProductException, UnknownCustomerException, UnknownGasStationException {
        doThrow(UnknownCustomerException.class).when(dao).updateFirstMatch(any(), any());

        assertThrows(UnknownCustomerException.class, ()->{
            service.updateTransaction(getTransaction(), getUpdatedTransaction());
        });
    }

    @Test
    public void updateTransactionWithUnknownGasStation() throws WrongTimeFormatException, UnknownTransactionException, UnknownProductException, UnknownCustomerException, UnknownGasStationException {
        doThrow(UnknownGasStationException.class).when(dao).updateFirstMatch(any(), any());

        assertThrows(UnknownGasStationException.class, ()->{
            service.updateTransaction(getTransaction(), getUpdatedTransaction());
        });
    }

    @Test
    public void readAllTransactions(){
        when(dao.readAll()).thenReturn(getDefaultTransactions());

        Collection<Transaction> actual = service.getAllTranslaction();

        assertThat(getDefaultTransactions(), is(actual));
    }


    private Transaction getTransaction(){
        return new Transaction(
                Date.valueOf("2020-12-12"),
                "14:42:00",
                5,
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

    private Transaction getUpdatedTransaction(){
        return new Transaction(
                Date.valueOf("2018-11-10"),
                "15:24:42",
                8,
                232,
                57,
                "Mogyoró",
                12345,
                95.56
        );
    }

    private Collection<Transaction> getDefaultTransactions(){
        return Arrays.asList(
                new Transaction(
                        Date.valueOf("2018-11-10"),
                        "15:24:42",
                        8,
                        232,
                        57,
                        "Mogyoró",
                        12345,
                        95.56
                ),
                new Transaction(
                        Date.valueOf("2020-12-12"),
                        "14:42:00",
                        5,
                        15,
                        44,
                        "Nafta",
                        500,
                        394.49
                ),
                new Transaction(
                        Date.valueOf("2009-05-21"),
                        "14:27:00",
                        9,
                        25,
                        133,
                        "Palacsinta",
                        123,
                        121.23
                ));
    }
}
