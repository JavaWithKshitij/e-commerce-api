package com.example.orderservice.service.impl;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.dto.ProductResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    @Value("${product.service.url}")
    private String productServiceUrl;

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        // 1️⃣ Call Product Service to get product details
        ProductResponse productResponse = webClient.get()
                .uri(productServiceUrl + "/products/{id}", orderRequest.getProductId())
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();

        if (productResponse == null) {
            throw new RuntimeException("Product not found");
        }
        // 2️⃣ Check stock
        if (productResponse.getStock().compareTo(orderRequest.getProductQuantity()) < 0) {
            throw new RuntimeException("Product out of stock");
        }
        ProductResponse updatedProduct = webClient.put()
                .uri(productServiceUrl + "/products/{id}/decrease-stock", orderRequest.getProductId())
                .bodyValue(Map.of("quantity", orderRequest.getProductQuantity()))
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();

        if (updatedProduct == null) {
            throw new RuntimeException("Stock update failed");
        }

        Order order = OrderMapper.toEntity(updatedProduct, orderRequest);
        return orderRepository.save(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<OrderResponse> orderResponseList = orderRepository.findAll().stream()
                .map(OrderMapper::toDto)
                .toList();
        if (orderResponseList.isEmpty()) {
            throw new RuntimeException("No orders found");
        }
        return orderResponseList;
    }

}
