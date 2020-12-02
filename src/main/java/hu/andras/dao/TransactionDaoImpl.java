package hu.andras.dao;

import hu.andras.dao.entity.*;
import hu.andras.exceptions.*;
import hu.andras.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionDaoImpl implements TransactionDao {
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final GasStationRepository gasStationRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Collection<Transaction> readAll() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return StreamSupport.stream(transactionRepository.findAll().spliterator(), false)
                .map(entity -> new Transaction(
                        entity.getDate(),
                        entity.getTime(),
                        entity.getCustomer().getCustomerId(),
                        entity.getCardId(),
                        entity.getGasStation().getGasStationId(),
                        entity.getProduct().getDescription(),
                        entity.getAmount(),
                        entity.getPrice()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createTransaction(Transaction transaction) throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, WrongTimeFormatException {
        TransactionEntity transactionEntity;

        checkTimeFormat(transaction.getTime());

        transactionEntity = TransactionEntity.builder()
                .date(transaction.getDate())
                .time(transaction.getTime())
                .customer(queryCustomer(transaction.getCustomerId()))
                .cardId(transaction.getCardId())
                .gasStation(queryGasStation(transaction.getGasStationId()))
                .product(queryProduct(transaction.getProduct()))
                .amount(transaction.getAmount())
                .price(transaction.getPrice())
                .build();

        log.info("TransactionEntity: {}", transactionEntity);
        try{
            transactionRepository.save(transactionEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }


    protected ProductEntity queryProduct(String description) throws UnknownProductException {
        Optional<ProductEntity> productEntity = productRepository.findByDescription(description).stream()
                .filter(entity -> entity.getDescription().equals(description))
                .findFirst();
        if( !productEntity.isPresent()){
            throw new UnknownProductException("Unknown Product: " + description);
        }
        return productEntity.get();
    }

    protected GasStationEntity queryGasStation(int gasStationId) throws UnknownGasStationException{
        Optional<GasStationEntity> gasStationEntity = gasStationRepository.findByGasStationId(gasStationId).stream()
                .filter(entity -> entity.getGasStationId() == gasStationId)
                .findFirst();
        if( !gasStationEntity.isPresent()){
            throw new UnknownGasStationException("Unknown Gas Station ID: " + gasStationId);
        }
        return gasStationEntity.get();
    }

    protected CustomerEntity queryCustomer(int customerId) throws UnknownCustomerException {
        Optional<CustomerEntity> customerEntity = customerRepository.findByCustomerId(customerId).stream()
                .filter(entity -> entity.getCustomerId() == customerId)
                .findFirst();
        if(!customerEntity.isPresent()){
            throw new UnknownCustomerException("Unknown Customer ID: " + customerId);
        }
        return customerEntity.get();
    }

    @Override
    public void deleteTransaction(Transaction transaction) throws UnknownCustomerException, UnknownGasStationException, UnknownProductException, UnknownTransactionException, WrongTimeFormatException {
        checkTimeFormat(transaction.getTime());

        Optional<TransactionEntity> transactionEntity = transactionRepository.findByDateAndTimeAndCustomerAndCardIdAndGasStationAndProductAndAmountAndPrice(
                transaction.getDate(),
                transaction.getTime(),
                queryCustomer(transaction.getCustomerId()),
                transaction.getCardId(),
                queryGasStation(transaction.getGasStationId()),
                queryProduct(transaction.getProduct()),
                transaction.getAmount(),
                transaction.getPrice())
                .stream()
                .findFirst();

        if(!transactionEntity.isPresent()){
            throw new UnknownTransactionException("Unknown Transaction: " + transaction.toString());
        }

        log.info("TransactionEntity: {}", transactionEntity);
        try{
            transactionRepository.delete(transactionEntity.get());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(Transaction transaction, Transaction updated) throws UnknownProductException, UnknownCustomerException, UnknownGasStationException, UnknownTransactionException, WrongTimeFormatException {
        checkTimeFormat(transaction.getTime());
        checkTimeFormat(updated.getTime());

        Optional<TransactionEntity> transactionEntity = transactionRepository.findByDateAndTimeAndCustomerAndCardIdAndGasStationAndProductAndAmountAndPrice(
                transaction.getDate(),
                transaction.getTime(),
                queryCustomer(transaction.getCustomerId()),
                transaction.getCardId(),
                queryGasStation(transaction.getGasStationId()),
                queryProduct(transaction.getProduct()),
                transaction.getAmount(),
                transaction.getPrice())
                .stream()
                .findFirst();

        if(!transactionEntity.isPresent()){
            throw new UnknownTransactionException("Unknown Transaction: " + transaction.toString());
        }

        log.info("EREDETI: " + transactionEntity.get().toString());
        transactionEntity.get().setAmount(updated.getAmount());
        transactionEntity.get().setDate(updated.getDate());
        transactionEntity.get().setTime(updated.getTime());
        transactionEntity.get().setCustomer(queryCustomer(updated.getCustomerId()));
        transactionEntity.get().setCardId(updated.getCardId());
        transactionEntity.get().setGasStation(queryGasStation(updated.getGasStationId()));
        transactionEntity.get().setProduct(queryProduct(updated.getProduct()));
        transactionEntity.get().setPrice(updated.getPrice());
        log.info("UPDATELT: " + transactionEntity.get().toString());

        try{
            transactionRepository.save(transactionEntity.get());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }

    }

    protected void checkTimeFormat(String time) throws WrongTimeFormatException {
        String[] checker = time.split(":");
        if(checker.length != 3){
            throw new WrongTimeFormatException("Time format should be hh:mm:ss ", time);
        }
        if(checker[0].length() != 2 || checker[1].length() != 2 || checker[2].length() != 2){
            throw new WrongTimeFormatException("Time format should be hh:mm:ss ", time);
        }
        for (String s : checker) {
            for (int j = 0; j < s.length(); j++) {
                if (!Character.isDigit(s.charAt(j))) {
                    throw new WrongTimeFormatException("Time format should be hh:mm:ss ", time);
                }
            }
        }
    }
}
