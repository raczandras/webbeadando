package hu.andras.service;

import hu.andras.dao.GasStationDao;
import hu.andras.exceptions.UnknownGasStationException;
import hu.andras.model.GasStation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@ExtendWith(MockitoExtension.class)
public class GasStationServiceImplTest {

    @InjectMocks
    private GasStationServiceImpl service;

    @Mock
    private GasStationDao dao;

    @Test
    public void recordGasStationSuccessful(){
        GasStation gasStation = getGasStation();
        service.recordGasStation(gasStation);

        verify(dao, times(1)).createGasStation(gasStation);
    }

    @Test
    public void deleteGasStationSuccessful() throws UnknownGasStationException {
        GasStation gasStation = getGasStation();

        service.deleteGasStation(gasStation);
        verify(dao, times(1)).deleteGasStation(gasStation);
    }

    @Test
    public void deleteGasStationWithUnknownGasStation() throws UnknownGasStationException{
        doThrow(UnknownGasStationException.class).when(dao).deleteGasStation(any());

        assertThrows(UnknownGasStationException.class, ()->{
            service.deleteGasStation(getGasStation());
        });
    }

    @Test
    public void updateGasStationSuccessful() throws UnknownGasStationException{
        GasStation gasStation = getGasStation();
        GasStation updatedGasStation = getUpdatedGasStation();

        service.updateFirstMatch(gasStation, updatedGasStation);

        verify(dao, times(1)).updateFirstMatch(gasStation, updatedGasStation);
    }

    @Test
    public void updateGasStationWithUnknownGasStation() throws UnknownGasStationException{
        doThrow(UnknownGasStationException.class).when(dao).updateFirstMatch(any(), any());

        assertThrows(UnknownGasStationException.class, ()->{
            service.updateFirstMatch(getGasStation(), getUpdatedGasStation());
        });
    }

    @Test
    public void readAllGasStations(){
        when(dao.readAll()).thenReturn(getDefaultGasStations());

        Collection<GasStation> actual = service.getAllGasStations();

        assertThat(getDefaultGasStations(), is(actual));
    }


    private GasStation getGasStation(){
        return new GasStation(
                2,
                "HUN",
                "HUF"
        );
    }

    private GasStation getUpdatedGasStation(){
        return new GasStation(
                3,
                "ENG",
                "GBP"
        );
    }

    private Collection<GasStation> getDefaultGasStations(){
        return Arrays.asList(
                new GasStation(
                        3,
                        "ENG",
                        "GBP"
                ),
                new GasStation(
                        2,
                        "HUN",
                        "HUF"
                ),
                new GasStation(
                        4,
                        "ROM",
                        "RAM"
                ));
    }
}
