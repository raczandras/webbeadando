package hu.andras.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.sql.Date;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Transaction {

    private Date date;
    private String time;
    private int customerId;
    private int cardId;
    private int gasStationId;
    private String product;
    private int amount;
    private double price;


}
