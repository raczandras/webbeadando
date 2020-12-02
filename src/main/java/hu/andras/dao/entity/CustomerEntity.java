package hu.andras.dao.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customers", schema = "ccs")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "customer_id")
    private int customerId;

    @Column(name="Segment")
    private String segment;

    @Column(name="Currency")
    private String currency;

}
