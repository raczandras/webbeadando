package hu.andras.dao;

import hu.andras.exceptions.*;
import hu.andras.model.Transaction;

import java.util.Collection;

public interface TransactionDao {
    Collection<Transaction> readAll();
    void createTransaction(Transaction transaction) throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, WrongTimeFormatException;
    void deleteTransaction(Transaction transaction) throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException;

    void updateFirstMatch(Transaction transaction, Transaction updated) throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException;
}
