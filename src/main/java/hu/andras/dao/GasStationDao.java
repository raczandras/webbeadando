package hu.andras.dao;

import hu.andras.exceptions.UnknownGasStationException;
import hu.andras.model.GasStation;
import java.util.Collection;

public interface GasStationDao {
    Collection<GasStation> readAll();
    void createGasStation(GasStation gasStation);
    void deleteGasStation(GasStation gasStation) throws UnknownGasStationException;

    void updateFirstMatch(GasStation original, GasStation updated) throws UnknownGasStationException;
}