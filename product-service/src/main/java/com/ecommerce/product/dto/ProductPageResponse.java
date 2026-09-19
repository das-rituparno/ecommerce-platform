package com.ecommerce.product.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPageResponse {
    private List<ProductResponse> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
