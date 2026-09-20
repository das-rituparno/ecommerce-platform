package com.ecommerce.product.service;

import com.ecommerce.product.dto.ProductPageResponse;
import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.exception.InvalidRequestException;
import com.ecommerce.product.exception.ProductNotFoundException;
import com.ecommerce.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getProductById_shouldReturnProduct_whenProductExists() {

        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("iphone 17");
        product.setPrice(new BigDecimal("79999"));

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        // Act
        var result = productService.getProductById(1L);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("iphone 17", result.getName());

        verify(productRepository).findById(1L);
    }

    @Test
    void getProductById_shouldThrowException_whenProductDoesNotExist() {

        // Arrange
        when(productRepository.findById(999L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                ProductNotFoundException.class,
                () -> productService.getProductById(999L)
        );
    }

    @Test
    void createProduct_shouldCreateAndReturnProduct() {

        // Arrange
        ProductRequest request = new ProductRequest();

        request.setName("iPhone 17");
        request.setDescription("Latest iPhone");
        request.setPrice(new BigDecimal("79999"));
        request.setCategoryId(1L);
        request.setBrand("Apple");
        request.setQuantity(10);
        request.setImageUrl("iphone.jpg");

        Product savedProduct = new Product();

        savedProduct.setId(1L);
        savedProduct.setName("iPhone 17");
        savedProduct.setDescription("Latest iPhone");
        savedProduct.setPrice(new BigDecimal("79999"));
        savedProduct.setCategoryId(1L);
        savedProduct.setBrand("Apple");
        savedProduct.setQuantity(10);
        savedProduct.setImageUrl("iphone.jpg");

        when(productRepository.save(any(Product.class)))
                .thenReturn(savedProduct);

        // Act
        ProductResponse result = productService.createProduct(request);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("iPhone 17", result.getName());
        assertEquals("Apple", result.getBrand());

        verify(productRepository).save(any(Product.class));
    }

    @Test
    void getAllProducts_shouldReturnAllProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("iphone 17");
        product1.setPrice(new BigDecimal("79999"));

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Samsung s26");
        product2.setPrice(new BigDecimal("119999"));

        when(productRepository.findAll())
                .thenReturn(List.of(product1, product2));

        // Act
        List<ProductResponse> result = productService.getAllProducts();

        // Assert
        assertEquals(2, result.size());
        assertEquals("iphone 17", result.get(0).getName());
        assertEquals("Samsung s26", result.get(1).getName());

        verify(productRepository).findAll();
    }

    @Test
    void updateProduct_shouldUpdateAndReturnProduct() {

        // Arrange
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Old Phone");
        existingProduct.setPrice(new BigDecimal("50000"));
        existingProduct.setBrand("Old Brand");
        existingProduct.setQuantity(5);

        ProductRequest request = new ProductRequest();
        request.setName("iPhone 17");
        request.setDescription("Updated phone");
        request.setPrice(new BigDecimal("79999"));
        request.setCategoryId(1L);
        request.setBrand("Apple");
        request.setQuantity(10);
        request.setImageUrl("iphone.jpg");

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("iPhone 17");
        updatedProduct.setDescription("Updated phone");
        updatedProduct.setPrice(new BigDecimal("79999"));
        updatedProduct.setCategoryId(1L);
        updatedProduct.setBrand("Apple");
        updatedProduct.setQuantity(10);
        updatedProduct.setImageUrl("iphone.jpg");

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(existingProduct));

        when(productRepository.save(existingProduct))
                .thenReturn(updatedProduct);

        // Act
        ProductResponse result =
                productService.updateProduct(1L, request);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("iPhone 17", result.getName());
        assertEquals("Apple", result.getBrand());
        assertEquals(new BigDecimal("79999"), result.getPrice());
        assertEquals(10, result.getQuantity());

        verify(productRepository).findById(1L);
        verify(productRepository).save(existingProduct);
    }

    @Test
    void updateProduct_shouldThrowException_whenProductDoesNotExist() {

        // Arrange
        ProductRequest request = new ProductRequest();

        request.setName("iPhone 17");
        request.setPrice(new BigDecimal("79999"));
        request.setCategoryId(1L);
        request.setBrand("Apple");
        request.setQuantity(10);

        when(productRepository.findById(999L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                ProductNotFoundException.class,
                () -> productService.updateProduct(999L, request)
        );

        verify(productRepository).findById(999L);

        // save() should never be called
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void deleteProduct_shouldDeleteProduct_whenProductExists() {

        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("iPhone 17");

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        // Act
        productService.deleteProduct(1L);

        // Assert
        verify(productRepository).findById(1L);
        verify(productRepository).delete(product);
    }

    @Test
    void deleteProduct_shouldThrowException_whenProductDoesNotExist() {

        // Arrange
        when(productRepository.findById(999L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                ProductNotFoundException.class,
                () -> productService.deleteProduct(999L)
        );

        verify(productRepository).findById(999L);

        verify(productRepository, never())
                .delete(any(Product.class));
    }
// ---------------------------------------------------------------------------------------------
@Test
void searchProducts_shouldReturnPaginatedProducts() {

    // Arrange
    Product product1 = new Product();
    product1.setId(1L);
    product1.setName("iPhone 17");
    product1.setPrice(new BigDecimal("79999"));

    Product product2 = new Product();
    product2.setId(2L);
    product2.setName("iPhone 17 Pro");
    product2.setPrice(new BigDecimal("99999"));

    Page<Product> productPage = new PageImpl<>(
            List.of(product1, product2),
            PageRequest.of(0, 2),
            5
    );

    when(productRepository.findAll(
            any(Specification.class),
            any(Pageable.class)
    )).thenReturn(productPage);

    // Act
    ProductPageResponse result = productService.searchProducts(
            "iPhone",
            null,
            null,
            null,
            0,
            2
    );

    // Assert
    assertEquals(2, result.getContent().size());
    assertEquals(0, result.getPage());
    assertEquals(2, result.getSize());
    assertEquals(5, result.getTotalElements());
    assertEquals(3, result.getTotalPages());

    assertEquals("iPhone 17", result.getContent().get(0).getName());
    assertEquals("iPhone 17 Pro", result.getContent().get(1).getName());

    verify(productRepository).findAll(
            any(Specification.class),
            any(Pageable.class)
    );
}
    @Test
    void searchProducts_shouldThrowException_whenPageIsNegative() {

        // Act & Assert
        assertThrows(
                InvalidRequestException.class,
                () -> productService.searchProducts(
                        null,
                        null,
                        null,
                        null,
                        -1,
                        10
                )
        );

        // Repository should never be called
        verify(productRepository, never()).findAll(
                any(Specification.class),
                any(Pageable.class)
        );
    }

    @Test
    void searchProducts_shouldThrowException_whenSizeIsInvalid() {

        // Act & Assert
        assertThrows(
                InvalidRequestException.class,
                () -> productService.searchProducts(
                        null,
                        null,
                        null,
                        null,
                        0,
                        0
                )
        );

        // Repository should never be called
        verify(productRepository, never()).findAll(
                any(Specification.class),
                any(Pageable.class)
        );
    }
}