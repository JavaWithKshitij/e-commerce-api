package com.example.productservice.controller;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody @Valid ProductRequest productRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String productId) {

        return ResponseEntity.status(HttpStatus.OK).body(productService.getByproductId(productId));
    }

    @PutMapping("/{id}/decrease-stock")
    public ResponseEntity<ProductResponse> decreaseStock(@PathVariable String productId, @RequestBody Map<String, BigInteger> request) {
        BigInteger quantity = request.get("quantity");

        return ResponseEntity.status(HttpStatus.OK).body(productService.decreaseStock(productId, quantity));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {

        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }
}
