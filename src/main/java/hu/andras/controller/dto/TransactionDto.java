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
public class TransactionDto {
    private Date date;
    private String time;
    private int customerId;
    private int cardId;
    private int gasStationId;
    private String product;
    private int amount;
    private double price;
}
