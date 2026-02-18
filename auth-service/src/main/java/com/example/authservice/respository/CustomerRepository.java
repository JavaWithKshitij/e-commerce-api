package com.example.authservice.respository;

import com.example.authservice.entity.Customer;
import com.example.authservice.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    // Find by username (for login)
    Optional<Customer> findByUsername(String username);

    // Find by business userId (USER-XXXX)
    Optional<Customer> findByUserId(String userId);

    // Check if username already exists (for registration validation)
    boolean existsByUsername(String username);

    // Find all users by role (ADMIN / USER)
    List<Customer> findByRole(Role role);

}