package com.example.productservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private String productId;
    private String name;
    private BigDecimal price;
    private int stock;
}
