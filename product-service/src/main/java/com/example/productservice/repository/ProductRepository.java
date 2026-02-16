package com.example.productservice.repository;

import com.example.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find product by name
    Optional<Product> findByName(String name);

    // Find products by price range
    List<Product> findByPriceBetween(double min, double max);

    // Find products with stock greater than given value
    List<Product> findByStockGreaterThan(int stock);

    // Search product by keyword (LIKE query)
    List<Product> findByNameContainingIgnoreCase(String keyword);
}
