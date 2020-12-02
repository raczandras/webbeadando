package hu.andras.dao;

import hu.andras.dao.entity.GasStationRepository;
import hu.andras.exceptions.UnknownGasStationException;
import hu.andras.model.GasStation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GasStationDaoImplTest {

    @Spy
    @InjectMocks
    private GasStationDaoImpl dao;
    @Mock
    private GasStationRepository gasStationRepository;

    @Test
    public void createGasStationSuccessfull() {
        dao.createGasStation(getGasStation());

        verify(gasStationRepository,times(1)).save(any());
    }


    @Test
    public void deleteGasStationWithUnknownGasStation() throws UnknownGasStationException{
        doThrow(UnknownGasStationException.class).when(dao).deleteGasStation(any());

        assertThrows(UnknownGasStationException.class, ()->{
           dao.deleteGasStation(getGasStation());
        });
    }


    @Test
    public void updateGasStationWithUnknownGasStation() throws UnknownGasStationException{
        doThrow(UnknownGasStationException.class).when(dao).updateFirstMatch(any(), any());

        assertThrows(UnknownGasStationException.class, ()->{
           dao.updateFirstMatch(getGasStation(), getGasStation());
        });
    }

    private GasStation getGasStation(){
        return new GasStation(
                2,
                "HUN",
                "HUF"
        );
    }
}
