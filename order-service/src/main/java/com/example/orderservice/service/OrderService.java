package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.entity.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
}
