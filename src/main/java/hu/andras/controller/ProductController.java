package hu.andras.controller;

import hu.andras.controller.dto.ProductDto;
import hu.andras.controller.dto.ProductUpdateDto;
import hu.andras.exceptions.UnknownProductException;
import hu.andras.model.Product;
import hu.andras.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/Product")
    public Collection<ProductDto> listProducts(){
        return service.getAllProducts()
                .stream()
                .map( model -> ProductDto.builder()
                        .description(model.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Product")
    public void record(@RequestBody ProductDto productDto){
        try{
            service.recordProduct(new Product(
                    productDto.getDescription()
            ));
        } catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @DeleteMapping("/Product")
    public void deleteFirstMatch(@RequestBody ProductDto productDto) throws UnknownProductException {
        try{
            service.deleteProduct(new Product(
                    productDto.getDescription()
            ));
        } catch(UnknownProductException e){
            throw new UnknownProductException(e.getMessage());
        }
    }

    @PutMapping("/Product")
    public void updateFirstMatch(@RequestBody ProductUpdateDto productUpdateDto) throws UnknownProductException{
        try{
            service.updadeFirstMatch(
                    new Product(
                            productUpdateDto.getDescription()
                    ),
                    new Product(
                            productUpdateDto.getUpdatedDescription()
                    )
            );
        } catch( UnknownProductException e){
            throw new UnknownProductException((e.getMessage()));
        }
    }
}
