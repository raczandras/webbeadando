package hu.andras.exceptions;

import hu.andras.model.YearMonth;
import lombok.Data;

@Data
public class UnknownYearMonthException extends Exception {

    private YearMonth yearMonth;

    public UnknownYearMonthException(YearMonth yearMonth){
        this.yearMonth = yearMonth;
    }

    public UnknownYearMonthException(String message, YearMonth yearMonth){
        super(message);
        this.yearMonth = yearMonth;
    }

    public UnknownYearMonthException(String message) {
        super(message);
    }
}
