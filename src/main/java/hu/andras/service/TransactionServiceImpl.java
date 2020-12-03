package hu.andras.service;

import hu.andras.dao.TransactionDao;
import hu.andras.exceptions.*;
import hu.andras.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;

    @Override
    public Collection<Transaction> getAllTranslaction() {
        return transactionDao.readAll();
    }

    @Override
    public void recordTransaction(Transaction transaction) throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, WrongTimeFormatException, OutOfBoundAmountException {
        transactionDao.createTransaction(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException{
        transactionDao.deleteTransaction(transaction);
    }

    @Override
    public void updateTransaction(Transaction transaction, Transaction updated) throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException {
        transactionDao.updateFirstMatch(transaction, updated);
    }
}
