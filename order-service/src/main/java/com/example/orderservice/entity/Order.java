package com.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String productId;
    private BigInteger productQuantity;
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
