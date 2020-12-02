package hu.andras.controller;

import hu.andras.controller.dto.CustomerDto;
import hu.andras.controller.dto.CustomerUpdateDto;
import hu.andras.exceptions.UnknownCustomerException;
import hu.andras.model.Customer;
import hu.andras.service.CustomerService;
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
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/customer")
    public Collection<CustomerDto> listCustomers(){
        return service.getAllCustomers()
                .stream()
                .map( model -> CustomerDto.builder()
                    .segment(model.getSegment())
                    .currency(model.getCurrency())
                    .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/customer")
    public void record(@RequestBody CustomerDto customerDto) {
        service.recordCustomer(
                new Customer(
                        customerDto.getSegment(),
                        customerDto.getCurrency()
                ));
    }

    @DeleteMapping("/customer")
    public void deleteFirstMatch(@RequestBody CustomerDto customerDto){
        try{
            service.deleteCustomer(
                    new Customer(
                            customerDto.getSegment(),
                            customerDto.getCurrency()
                    )
            );
        } catch(UnknownCustomerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/customer")
    public void updateFirstMatch(@RequestBody CustomerUpdateDto customerUpdateDto) {

        try{
            service.updateFirstMatch(
                    new Customer(
                            customerUpdateDto.getSegment(),
                            customerUpdateDto.getCurrency()
                    ),
                    new Customer(
                            customerUpdateDto.getUpdatedSegment(),
                            customerUpdateDto.getUpdatedCurrency()

                    ));
        } catch(UnknownCustomerException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
