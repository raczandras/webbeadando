package hu.andras.exceptions;

import hu.andras.model.GasStation;
import lombok.Data;

@Data
public class UnknownGasStationException extends Exception{

    private GasStation gasStation;

    public UnknownGasStationException(GasStation gasStation){
        this.gasStation = gasStation;
    }

    public UnknownGasStationException(String message, GasStation gasStation){
        super(message);
        this.gasStation = gasStation;
    }

    public UnknownGasStationException(String message){
        super(message);
    }
}
