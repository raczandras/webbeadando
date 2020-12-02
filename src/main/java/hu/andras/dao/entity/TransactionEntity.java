package hu.andras.dao.entity;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "transaction", schema = "ccs")
public class TransactionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TransactionID")
    private int transactionId;

    @Column(name="Date")
    private Date date;

    @Column(name="Time")
    private String time;

    @ManyToOne
    @JoinColumn(name= "customer_id")
    private CustomerEntity customer;

    @Column(name="CardID")
    private int cardId;

    @ManyToOne
    @JoinColumn(name= "gas_stationid")
    private GasStationEntity gasStation;

    @ManyToOne
    @JoinColumn(name= "productid")
    private ProductEntity product;

    @Column(name="Amount")
    private int amount;

    @Column(name="Price")
    private double price;

}

