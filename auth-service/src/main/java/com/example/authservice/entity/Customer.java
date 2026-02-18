package com.example.authservice.entity;

import com.example.authservice.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String customerId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Role role;

    @Version
    private Long version;

    @PrePersist
    public void generateUserId() {
        if (this.customerId == null) {
            this.customerId = ("CUSTOMER-" + UUID.randomUUID()
                    .toString()
                    .substring(0, 8)
                    .toUpperCase());
        }
    }
}
