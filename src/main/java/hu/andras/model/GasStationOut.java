package hu.andras.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class GasStationOut {
    private int gasStationId;
    private int chainId;
    private String country;
    private String segment;

}
