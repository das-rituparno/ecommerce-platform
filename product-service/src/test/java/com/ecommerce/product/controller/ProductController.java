package com.ecommerce.product.controller;

import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.exception.ProductNotFoundException;
import com.ecommerce.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductController {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void getProductById_shouldReturn200_whenProductExists() throws Exception {

        // Arrange
        ProductResponse response = ProductResponse.builder()
                .id(1L)
                .name("iPhone 17")
                .price(new BigDecimal("79999"))
                .brand("Apple")
                .build();

        when(productService.getProductById(1L))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(
                        get("/api/products/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("iPhone 17"))
                .andExpect(jsonPath("$.brand").value("Apple"));
    }

    @Test
    void getProductById_shouldReturn404_whenProductDoesNotExist() throws Exception {

        // Arrange
        when(productService.getProductById(999L))
                .thenThrow(new ProductNotFoundException(999L));

        // Act & Assert
        mockMvc.perform(
                        get("/api/products/999")
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message")
                        .value("Product with id 999 not found"));
    }

    @Test
    void createProduct_shouldReturn400_whenRequestIsInvalid() throws Exception {

        // Arrange
        String request = """
                {
                    "name": "",
                    "price": -100,
                    "categoryId": null,
                    "brand": "",
                    "quantity": -5
                }
                """;

        // Act & Assert
        mockMvc.perform(
                        post("/api/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                )
                .andExpect(status().isBadRequest());
    }
}