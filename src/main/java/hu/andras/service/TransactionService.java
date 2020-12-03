package hu.andras.service;

import hu.andras.exceptions.*;
import hu.andras.model.Transaction;

import java.util.Collection;

public interface TransactionService {
    Collection<Transaction> getAllTranslaction();

    void recordTransaction(Transaction transaction) throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, WrongTimeFormatException, OutOfBoundAmountException;
    void deleteTransaction(Transaction transaction) throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException;

    void updateTransaction(Transaction transaction, Transaction transaction1) throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException;
}
