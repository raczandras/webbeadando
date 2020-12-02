package hu.andras.dao.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface GasStationRepository extends CrudRepository<GasStationEntity, Integer> {
    Collection<GasStationEntity> findByGasStationId(int GasStationId);
    Collection<GasStationEntity> findByChainIdAndCountryAndSegment(int ChainId, String Country, String Segment);
}
