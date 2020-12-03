package hu.andras.service;

import hu.andras.exceptions.UnknownGasStationException;
import hu.andras.model.GasStation;
import hu.andras.model.GasStationOut;

import java.util.Collection;

public interface GasStationService {
    Collection<GasStationOut> getAllGasStations();

    void recordGasStation(GasStation gasStation);
    void deleteGasStation(GasStation gasStation) throws UnknownGasStationException;

    void updateFirstMatch(GasStation original, GasStation updated) throws UnknownGasStationException;
}
