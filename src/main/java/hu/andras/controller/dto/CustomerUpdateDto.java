package hu.andras.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateDto extends CustomerDto {
    private String updatedSegment;
    private String updatedCurrency;
}
