package hu.andras.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionUpdateDto extends TransactionDto {
    private Date updatedDate;
    private String updatedTime;
    private int updatedCustomerId;
    private int updatedCardId;
    private int updatedGasStationId;
    private String updatedProduct;
    private int updatedAmount;
    private double updatedPrice;
}
