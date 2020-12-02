package hu.andras.service;

import hu.andras.exceptions.UnknownGasStationException;
import hu.andras.model.GasStation;

import java.util.Collection;

public interface GasStationService {
    Collection<GasStation> getAllGasStations();

    void recordGasStation(GasStation gasStation);
    void deleteGasStation(GasStation gasStation) throws UnknownGasStationException;

    void updateFirstMatch(GasStation original, GasStation updated) throws UnknownGasStationException;
}
