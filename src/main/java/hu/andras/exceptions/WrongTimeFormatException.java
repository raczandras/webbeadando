package hu.andras.exceptions;

import lombok.Data;

@Data
public class WrongTimeFormatException extends Exception {
    private String time;

    public WrongTimeFormatException(String message, String time){
        super(message);
        this.time = time;
    }

    public WrongTimeFormatException(String message) {
        super(message);
    }
}
