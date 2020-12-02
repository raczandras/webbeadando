package hu.andras.exceptions;

import hu.andras.model.Transaction;
import lombok.Data;

@Data
public class UnknownTransactionException extends Exception {

    private Transaction transaction;

    public UnknownTransactionException(Transaction transaction){
        this.transaction = transaction;
    }

    public UnknownTransactionException(String message, Transaction transaction){
        super(message);
        this.transaction = transaction;
    }

    public UnknownTransactionException(String message){
        super(message);
    }
}
