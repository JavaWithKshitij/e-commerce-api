package com.example.productservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String productId;
    private String name;
    private BigDecimal price;
    private BigInteger stock;
    @Version
    private Long version;

    @PrePersist
    public void generateProductId() {
        if (this.productId == null) {
            this.productId = ("PROD-" + UUID.randomUUID()
                    .toString()
                    .substring(0, 8)
                    .toUpperCase());
        }
    }
}
