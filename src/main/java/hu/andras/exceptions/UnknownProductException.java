package hu.andras.exceptions;

import hu.andras.model.Product;
import lombok.Data;

@Data
public class UnknownProductException extends Exception{

    private Product product;

    public UnknownProductException( Product product) {
        this.product = product;
    }

    public UnknownProductException(String message, Product product){
        super(message);
        this.product = product;
    }

    public UnknownProductException(String message){
        super(message);
    }
}
