package hu.andras.controller;

import hu.andras.controller.dto.GasStationDto;
import hu.andras.controller.dto.GasStationUpdateDto;
import hu.andras.exceptions.UnknownGasStationException;
import hu.andras.model.GasStation;
import hu.andras.service.GasStationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GasStationController {

    private final GasStationService service;

    @GetMapping("/GasStation")
    public Collection<GasStationDto> listGasStations(){
        return service.getAllGasStations()
                .stream()
                .map( model -> GasStationDto.builder()
                        .chainId(model.getChainId())
                        .country(model.getCountry())
                        .segment(model.getSegment())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @PostMapping("/GasStation")
    public void record(@RequestBody GasStationDto gasStationDto){
        try{
            service.recordGasStation( new GasStation(
                    gasStationDto.getChainId(),
                    gasStationDto.getCountry(),
                    gasStationDto.getSegment()
            ));
        } catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @DeleteMapping("/GasStation")
    public void deleteFirstMatch(@RequestBody GasStationDto gasStationDto){
        try{
            service.deleteGasStation( new GasStation(
                    gasStationDto.getChainId(),
                    gasStationDto.getCountry(),
                    gasStationDto.getSegment()
            ));
        } catch(UnknownGasStationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/GasStation")
    public void updateFirstMatch(@RequestBody GasStationUpdateDto gasStationUpdateDto){
        try{
            service.updateFirstMatch(
                    new GasStation(
                    gasStationUpdateDto.getChainId(),
                    gasStationUpdateDto.getCountry(),
                    gasStationUpdateDto.getSegment()
                    ),
                    new GasStation(
                            gasStationUpdateDto.getUpdatedChainId(),
                            gasStationUpdateDto.getUpdatedCountry(),
                            gasStationUpdateDto.getUpdatedSegment()
                    ));
        } catch(UnknownGasStationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
