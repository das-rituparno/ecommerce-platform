package com.ecommerce.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0",
            inclusive = false,
            message = "price must be greater than 0")
    private BigDecimal price;

    @NotBlank(message = "CategoryID is required")
    private Long categoryId;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotNull(message = "Quantity is required")
    @Min(value = 0,
            message = "Quantity can not be negative")
    private Integer quantity;

    private String imageUrl;
}
