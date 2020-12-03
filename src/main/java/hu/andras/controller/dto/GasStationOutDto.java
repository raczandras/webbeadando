package hu.andras.controller.dto;

import hu.andras.dao.entity.GasStationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GasStationOutDto extends GasStationDto {
    private int gasStationId;
}
