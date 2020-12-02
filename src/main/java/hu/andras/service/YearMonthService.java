package hu.andras.service;


import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.exceptions.UnknownYearMonthException;
import hu.andras.model.YearMonth;
import java.util.Collection;

public interface YearMonthService {
    Collection<YearMonth> getAllYearMonths();

    void recordYearMonth(YearMonth yearMonth) throws UnknownCustomerException;
    void deleteYearMonth(YearMonth yearMonth) throws UnknownCustomerException, UnknownYearMonthException;

    void updateFirstMatch(YearMonth yearMonth, double updatedConsumption) throws UnknownCustomerException, UnknownYearMonthException;
}
