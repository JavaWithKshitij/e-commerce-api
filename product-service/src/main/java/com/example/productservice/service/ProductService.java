package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
