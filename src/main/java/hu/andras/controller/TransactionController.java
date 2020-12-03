package hu.andras.controller;

import hu.andras.controller.dto.TransactionDto;
import hu.andras.controller.dto.TransactionUpdateDto;
import hu.andras.exceptions.*;
import hu.andras.model.Transaction;
import hu.andras.service.TransactionService;
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
public class TransactionController {

    private final TransactionService service;

    @GetMapping("/Transaction")
    public Collection<TransactionDto> listTransactions(){
        return service.getAllTranslaction()
                .stream()
                .map( model -> TransactionDto.builder()
                        .date(model.getDate())
                        .time(model.getTime())
                        .customerId(model.getCustomerId())
                        .cardId(model.getCardId())
                        .gasStationId(model.getGasStationId())
                        .product(model.getProduct())
                        .amount(model.getAmount())
                        .price(model.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Transaction")
    public void record(@RequestBody TransactionDto transactionDto){

        try{
            service.recordTransaction( new Transaction(
                    transactionDto.getDate(),
                    transactionDto.getTime(),
                    transactionDto.getCustomerId(),
                    transactionDto.getCardId(),
                    transactionDto.getGasStationId(),
                    transactionDto.getProduct(),
                    transactionDto.getAmount(),
                    transactionDto.getPrice()
            ));
        } catch (UnknownCustomerException | UnknownGasStationException | UnknownProductException | WrongTimeFormatException | OutOfBoundAmountException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Transaction")
    public void deleteFirstMatch(@RequestBody TransactionDto transactionDto){
        try{
            service.deleteTransaction( new Transaction(
                    transactionDto.getDate(),
                    transactionDto.getTime(),
                    transactionDto.getCustomerId(),
                    transactionDto.getCardId(),
                    transactionDto.getGasStationId(),
                    transactionDto.getProduct(),
                    transactionDto.getAmount(),
                    transactionDto.getPrice()
            ));
        } catch (UnknownCustomerException | UnknownGasStationException | UnknownProductException | UnknownTransactionException | WrongTimeFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/Transaction")
    public void updateFirstMatch(@RequestBody TransactionUpdateDto transactionUpdateDto){

        try{
            service.updateTransaction(
                    new Transaction(
                    transactionUpdateDto.getDate(),
                    transactionUpdateDto.getTime(),
                    transactionUpdateDto.getCustomerId(),
                    transactionUpdateDto.getCardId(),
                    transactionUpdateDto.getGasStationId(),
                    transactionUpdateDto.getProduct(),
                    transactionUpdateDto.getAmount(),
                    transactionUpdateDto.getPrice()
                    ),
                    new Transaction(
                    transactionUpdateDto.getUpdatedDate(),
                    transactionUpdateDto.getUpdatedTime(),
                    transactionUpdateDto.getUpdatedCustomerId(),
                    transactionUpdateDto.getUpdatedCardId(),
                    transactionUpdateDto.getUpdatedGasStationId(),
                    transactionUpdateDto.getUpdatedProduct(),
                    transactionUpdateDto.getUpdatedAmount(),
                    transactionUpdateDto.getUpdatedPrice()
                    ));
        } catch (UnknownCustomerException | UnknownGasStationException | UnknownProductException | UnknownTransactionException | WrongTimeFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
