package com.example.productservice.service.impl;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.entity.Product;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = ProductMapper.toEntity(productRequest);

        Product savedProduct = productRepository.save(product);

        log.info("product saved successfully : {}", savedProduct);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductResponse getByproductId(String productId) {
        Optional<Product> product= productRepository.findByProductId(productId);

        ProductResponse productResponse = ProductMapper.toDto(product.orElse(null));

        log.info("productResponse fetched successfully for productId {} : {}", productId, productResponse);
        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> productResponses = new ArrayList<>();

        List<Product> productList = productRepository.findAll();

        productList.forEach(product -> {
                ProductResponse productResponse = ProductMapper.toDto(product);
            productResponses.add(productResponse);
        });
        log.info("All productResponses fetched successfully : {}", productResponses);
        return productResponses;
    }

    @Override
    public ProductResponse decreaseStock(String productId, BigInteger quantity) {
        Product product = productRepository.findByProductId(productId).orElse(null);
        if (product != null) {
            product.setStock(product.getStock().subtract(quantity));
            productRepository.save(product);
            return ProductMapper.toDto(product);
        }
        return new ProductResponse();
    }
}
