package hu.andras.dao;

import hu.andras.dao.entity.ProductRepository;
import hu.andras.exceptions.UnknownProductException;
import hu.andras.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductDaoImplTest {
    @Spy
    @InjectMocks
    private ProductDaoImpl dao;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void createProductSuccessfull(){
        dao.createProduct(getProduct());

        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void updateProductWithUnknownProduct() throws UnknownProductException{
        doThrow(UnknownProductException.class).when(dao).updateFirstMatch(any(), any());

        assertThrows(UnknownProductException.class, ()->{
           dao.updateFirstMatch(getProduct(), getProduct());
        });
    }

    @Test
    public void deleteProductWithUnknownProduct() throws UnknownProductException{
        doThrow(UnknownProductException.class).when(dao).deleteProduct(any());

        assertThrows(UnknownProductException.class, ()->{
            dao.deleteProduct(getProduct());
        });
    }

    private Product getProduct(){
        return new Product(
                "Mogyoró"
        );
    }
}
