package com.example.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ProductRequest {
    @NotBlank(message = "Product name is required")
    private String name;
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;
    @NotNull(message = "Stock is required")
    @Positive(message = "Stock must be positive")
    private BigInteger stock;
}
