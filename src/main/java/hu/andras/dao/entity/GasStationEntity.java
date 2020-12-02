package hu.andras.dao.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "gasstations", schema = "ccs")
public class GasStationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "gas_stationid")
    private int gasStationId;

    @Column(name="ChainID")
    private int chainId;

    @Column(name="Country")
    private String country;

    @Column(name="Segment")
    private String segment;
}
