package hu.andras.exceptions;

import lombok.Data;

@Data
public class OutOfBoundAmountException extends Exception {
    private int amount;

    public OutOfBoundAmountException(String message){
        super(message);
    }
}
