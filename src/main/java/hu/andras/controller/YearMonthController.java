package hu.andras.controller;

import hu.andras.controller.dto.YearMonthDto;
import hu.andras.controller.dto.YearMonthUpdateDto;
import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.exceptions.UnknownYearMonthException;
import hu.andras.model.YearMonth;
import hu.andras.service.YearMonthService;
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
public class YearMonthController {

    private final YearMonthService service;

    @GetMapping("/YearMonth")
    public Collection<YearMonthDto> listYearMonths(){
        return service.getAllYearMonths()
                .stream()
                .map( model -> YearMonthDto.builder()
                        .customerId(model.getCustomerId())
                        .date(model.getDate())
                        .consumption(model.getConsumption())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/YearMonth")
    public void record(@RequestBody YearMonthDto yearMonthDto){
        try{
            service.recordYearMonth(
                    new YearMonth(
                            yearMonthDto.getCustomerId(),
                            yearMonthDto.getDate(),
                            yearMonthDto.getConsumption()
                    ));
        } catch(UnknownCustomerException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/YearMonth")
    public void deleteFristMatch(@RequestBody YearMonthDto yearMonthDto){
        try{
            service.deleteYearMonth(
                    new YearMonth(
                            yearMonthDto.getCustomerId(),
                            yearMonthDto.getDate(),
                            yearMonthDto.getConsumption()
                    ));
        } catch(UnknownYearMonthException | UnknownCustomerException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/YearMonth")
    public void updateFirstMatch(@RequestBody YearMonthUpdateDto yearMonthUpdateDto){
        try{
            service.updateFirstMatch(
                    new YearMonth(
                            yearMonthUpdateDto.getCustomerId(),
                            yearMonthUpdateDto.getDate(),
                            yearMonthUpdateDto.getConsumption()
                    ),
                    yearMonthUpdateDto.getUpdatedConsumption()
            );
        } catch( UnknownYearMonthException | UnknownCustomerException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
