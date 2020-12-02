package hu.andras.dao;

import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.exceptions.UnknownYearMonthException;
import hu.andras.model.YearMonth;
import java.util.Collection;

public interface YearMonthDao {
    Collection<YearMonth> readAll();
    void createYearMonth(YearMonth yearMonth) throws UnknownCustomerException;
    void deleteYearMonth(YearMonth yearMonth) throws UnknownCustomerException, UnknownYearMonthException;

    void updateFirstMatch(YearMonth yearMonth, double updatedConsumption) throws UnknownCustomerException, UnknownYearMonthException;
}