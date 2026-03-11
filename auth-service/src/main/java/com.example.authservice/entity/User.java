package com.example.authservice.entity;

import com.example.authservice.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Version
    private Long version;

    @PrePersist
    public void generateUserId() {
        if (this.userId == null) {
            this.userId = ("CUSTOMER-" + UUID.randomUUID()
                    .toString()
                    .substring(0, 8)
                    .toUpperCase());
        }
    }
}
