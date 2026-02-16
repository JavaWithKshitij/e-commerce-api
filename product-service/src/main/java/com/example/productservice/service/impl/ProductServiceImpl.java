package com.example.productservice.service.impl;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.entity.Product;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
