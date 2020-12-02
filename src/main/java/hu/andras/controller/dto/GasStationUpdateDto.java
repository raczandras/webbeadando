package hu.andras.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GasStationUpdateDto extends GasStationDto{
    private int updatedChainId;
    private String updatedCountry;
    private String updatedSegment;
}
