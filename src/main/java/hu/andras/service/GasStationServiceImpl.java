package hu.andras.service;

import hu.andras.dao.GasStationDao;
import hu.andras.exceptions.UnknownGasStationException;
import hu.andras.model.GasStation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class GasStationServiceImpl implements GasStationService {

    private final GasStationDao gasStationDao;

    @Override
    public Collection<GasStation> getAllGasStations() {
        return gasStationDao.readAll();
    }

    @Override
    public void recordGasStation(GasStation gasStation) {
        gasStationDao.createGasStation(gasStation);
    }

    @Override
    public void deleteGasStation(GasStation gasStation) throws UnknownGasStationException {
        gasStationDao.deleteGasStation(gasStation);
    }

    @Override
    public void updateFirstMatch(GasStation original, GasStation updated) throws UnknownGasStationException {
        gasStationDao.updateFirstMatch(original, updated);
    }
}
