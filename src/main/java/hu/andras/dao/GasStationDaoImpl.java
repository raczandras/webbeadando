package hu.andras.dao;

import hu.andras.dao.entity.GasStationEntity;
import hu.andras.dao.entity.GasStationRepository;
import hu.andras.exceptions.UnknownGasStationException;
import hu.andras.model.GasStation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class GasStationDaoImpl implements GasStationDao {
    private final GasStationRepository gasStationRepository;

    @Override
    public Collection<GasStation> readAll() {
        return StreamSupport.stream(gasStationRepository.findAll().spliterator(), false)
                .map(entity -> new GasStation(
                        entity.getChainId(),
                        entity.getCountry(),
                        entity.getSegment()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createGasStation(GasStation gasStation) {

        GasStationEntity gasStationEntity;

        gasStationEntity = GasStationEntity.builder()
                .chainId(gasStation.getChainId())
                .country(gasStation.getCountry())
                .segment(gasStation.getSegment())
                .build();

        log.info("GasStationEntity: {}", gasStationEntity);
        try{
            gasStationRepository.save(gasStationEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }

    }

    @Override
    public void deleteGasStation(GasStation gasStation) throws UnknownGasStationException {
        Optional<GasStationEntity> gasStationEntity = gasStationRepository.findByChainIdAndCountryAndSegment(gasStation.getChainId(), gasStation.getCountry(), gasStation.getSegment())
                .stream()
                .filter(entity -> entity.getChainId() == gasStation.getChainId() && entity.getCountry().equals(gasStation.getCountry()) && entity.getSegment().equals(gasStation.getSegment()))
                .findFirst();

        if(!gasStationEntity.isPresent()){
            throw new UnknownGasStationException("Unknown Gas Station: " + gasStation.toString());
        }
        log.info("GasStationEntity: {}", gasStationEntity);
        try{
            gasStationRepository.delete(gasStationEntity.get());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(GasStation original, GasStation updated) throws UnknownGasStationException{
        Optional<GasStationEntity> gasStationEntity = gasStationRepository.findByChainIdAndCountryAndSegment(
                original.getChainId(),
                original.getCountry(),
                original.getSegment())
                .stream()
                .filter(entity -> entity.getChainId() == original.getChainId() &&
                        entity.getCountry().equals(original.getCountry()) &&
                        entity.getSegment().equals(original.getSegment()))
                .findFirst();

        if(!gasStationEntity.isPresent()){
            throw new UnknownGasStationException("Unknown Gas Station: " + original.toString());
        }

        log.info("EREDETI: " + gasStationEntity.toString());
        gasStationEntity.get().setCountry(updated.getCountry());
        gasStationEntity.get().setChainId(updated.getChainId());
        gasStationEntity.get().setSegment(updated.getSegment());
        log.info("UPDATELT: " + gasStationEntity.toString());

        try{
            gasStationRepository.save(gasStationEntity.get());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
