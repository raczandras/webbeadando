package hu.andras.dao;

import hu.andras.exceptions.UnknownGasStationException;
import hu.andras.model.GasStation;
import hu.andras.model.GasStationOut;

import java.util.Collection;

public interface GasStationDao {
    Collection<GasStationOut> readAll();
    void createGasStation(GasStation gasStation);
    void deleteGasStation(GasStation gasStation) throws UnknownGasStationException;

    void updateFirstMatch(GasStation original, GasStation updated) throws UnknownGasStationException;
}