package hu.andras.service;

import hu.andras.dao.YearMonthDao;
import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.exceptions.UnknownYearMonthException;
import hu.andras.model.YearMonth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class YearMonthServiceImpl implements YearMonthService {

    private final YearMonthDao yearMonthDao;

    @Override
    public Collection<YearMonth> getAllYearMonths() {
        return yearMonthDao.readAll();
    }

    @Override
    public void recordYearMonth(YearMonth yearMonth) throws UnknownCustomerException {
        yearMonthDao.createYearMonth(yearMonth);
    }

    @Override
    public void deleteYearMonth(YearMonth yearMonth) throws UnknownCustomerException, UnknownYearMonthException {
        yearMonthDao.deleteYearMonth(yearMonth);
    }

    @Override
    public void updateFirstMatch(YearMonth yearMonth, double updatedConsumption) throws UnknownCustomerException, UnknownYearMonthException {
        yearMonthDao.updateFirstMatch(yearMonth, updatedConsumption);
    }
}
