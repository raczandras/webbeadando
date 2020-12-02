package hu.andras.dao.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "yearmonth", schema = "ccs")
public class YearMonthEntity {

    @EmbeddedId()
    private Key key;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Embeddable
    public static class Key implements  java.io.Serializable{
        @ManyToOne
        @JoinColumn(name="customerId")
        private CustomerEntity customer;

        @Column(name="Date")
        private int date;

    }

    @Column(name="Consumption")
    private double consumption;

}
