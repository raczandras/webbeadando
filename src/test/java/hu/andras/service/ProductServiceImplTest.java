package hu.andras.service;

import hu.andras.dao.ProductDao;
import hu.andras.exceptions.UnknownProductException;
import hu.andras.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductDao dao;

    @Test
    public void recordProductSuccessful(){
        Product product = getProduct();
        service.recordProduct(product);

        verify(dao, times(1)).createProduct(product);
    }

    @Test
    public void deleteProductSuccessful() throws UnknownProductException {
        Product product = getProduct();

        service.deleteProduct(product);
        verify(dao, times(1)).deleteProduct(product);
    }

    @Test
    public void deleteProductWithUnknownProduct() throws UnknownProductException{
        doThrow(UnknownProductException.class).when(dao).deleteProduct(any());

        assertThrows(UnknownProductException.class, ()->{
            service.deleteProduct(getProduct());
        });
    }

    @Test
    public void updateProductSuccessful() throws UnknownProductException{
        Product product = getProduct();
        Product updatedProduct = getUpdatedProduct();

        service.updadeFirstMatch(product, updatedProduct);

        verify(dao, times(1)).updateFirstMatch(product, updatedProduct);
    }

    @Test
    public void updateProductWithUnknownProduct() throws UnknownProductException{
        doThrow(UnknownProductException.class).when(dao).updateFirstMatch(any(), any());

        assertThrows(UnknownProductException.class, ()->{
            service.updadeFirstMatch( getProduct(), getUpdatedProduct());
        });
    }

    @Test
    public void readAllProducts(){
        when(dao.readAll()).thenReturn(getDefaultProducts());

        Collection<Product> actual = service.getAllProducts();

        assertThat(getDefaultProducts(), is(actual));
    }


    private Product getProduct(){
        return new Product(
                "Nafta"
        );
    }

    private Product getUpdatedProduct(){
        return new Product(
                "Motorolaj"
        );
    }

    private Collection<Product> getDefaultProducts(){
        return Arrays.asList(
                new Product(
                "Motorolaj"
                ),
                new Product(
                        "Nafta"
                ),
                new Product(
                        "Mogyoró"
                ));
    }
}
