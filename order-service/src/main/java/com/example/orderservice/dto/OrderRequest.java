package com.example.orderservice.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class OrderRequest {
    private String productId;
    private BigInteger productQuantity;
}
