package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;

import java.math.BigInteger;
import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse getByproductId(String productId);

    List<ProductResponse> getAllProducts();

    ProductResponse decreaseStock (String productId, BigInteger quantity);
}
