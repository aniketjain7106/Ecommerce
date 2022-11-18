package com.cg.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.product.dto.ProductRequest;
import com.cg.product.model.Product;
import com.cg.product.repository.ProductRepository;
import com.cg.product.service.ProductService;

@SpringBootTest(classes = {TestCasesProduct.class})
public class TestCasesProduct {
    
    @Mock
    ProductRepository productRepository;
    
    @InjectMocks
    ProductService productService;
    
    public List<Product> myProducts;
    
    @Test
    @Order(1)
    public void test_getAllProducts() {
        List<Product> myproducts = new ArrayList<Product>();
        myproducts.add(new Product("1","coffee","coffeecoffee",120,1000));
        myproducts.add(new Product("2","choclate","choclatechoclate",10,100));
        when(productRepository.findAll()).thenReturn(myproducts);
        assertEquals(2, productService.getAllProducts().size());
    }
    
    @Test
    public void test_getProductbyid() {
        List<Product> myproducts = new ArrayList<Product>();
        myproducts.add(new Product("1","coffee","coffeecoffee",120,1000));
        myproducts.add(new Product("2","choclate","choclatechoclate",10,100));
        when(productRepository.findAll()).thenReturn(myproducts);
        assertEquals("1",productService.getProductById("1").getId());
    }
    
    @Test
    public void test_deleteProductbyid() {
//        List<Product> myproducts = new ArrayList<Product>();
//        myproducts.add(new Product("1","coffee","coffeecoffee",120,1000));
//        myproducts.add(new Product("2","choclate","choclatechoclate",10,100));
//        when(productRepository.findAll()).thenReturn(myproducts);
//        productService.deleteProduct("1");
////        assertEquals(1, productService.getAllProducts().size());
//        verify(myproducts,times(1)).remove(0);
        Product product = new Product("14567","coffee","coffeecoffee",120,1000);
        when(productRepository.getProductById("14567")).thenReturn(product);
        productService.deleteProduct("14567");
        verify(productRepository,times(1)).deleteById("14567");
    }
    
    @Test
    public void test_addProducts() {
        ProductRequest productRequest = new ProductRequest("3","book","bookbook",15,50);
        List<Product> myproducts = new ArrayList<Product>();
        Product product = Product.builder()
                .id(productRequest.getId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        when(productRepository.save(product)).thenReturn(product);
        myproducts.add(product);
        when(productRepository.findAll()).thenReturn(myproducts);
        productService.createProduct(productRequest);
        assertEquals(1,productService.getAllProducts().size());
    }
    
    @Test
    public void test_increaseQuantity() {
        Product product = new Product("14567","coffee","coffeecoffee",120,1000);
        when(productRepository.getProductById("14567")).thenReturn(product);
        Product product2 = productService.increaseQuantity(10,"14567");
        assertEquals(product.getQuantity()+10, product2.getQuantity());
    }
    
    @Test
    public void test_decreaseQuantity() {
        Product product = new Product("14567","coffee","coffeecoffee",120,1000);
        when(productRepository.getProductById("14567")).thenReturn(product);
        Product product2 = productService.reduceQuantity(10,"14567");
        assertEquals(product.getQuantity()-10, product2.getQuantity());
    }
    
    @Test
    public void test_price() {
        Product product = new Product("14567","coffee","coffeecoffee",120,1000);
        when(productRepository.getProductById("14567")).thenReturn(product);
        Product product2 = productService.updatePrice("14567",10);
        assertEquals(10, product2.getPrice());
    }
    
    @Test
    public void test_update() {
        Product product2 = new Product("14567","coffee","coffeecoffee",120,1000);
        ProductRequest productRequest = new ProductRequest("14567","book","bookbook",15,50);
        Product product = Product.builder()
                .id(productRequest.getId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        when(productRepository.save(product2)).thenReturn(product);
        Product product3 = productService.update(productRequest, "14567");
        assertEquals(product, product3);
    }
    
    
}
