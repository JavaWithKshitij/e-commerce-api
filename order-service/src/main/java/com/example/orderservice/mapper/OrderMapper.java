package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.dto.ProductResponse;
import com.example.orderservice.entity.Order;
import org.springframework.beans.BeanUtils;

public class OrderMapper {

    public static Order toEntity(ProductResponse productResponse, OrderRequest orderRequest) {
        Order order = new Order();
        order.setProductId(productResponse.getProductId());
        order.setProductQuantity(orderRequest.getProductQuantity());
        return order;
    }

    public static OrderResponse toDto(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setProductId(order.getProductId());
        orderResponse.setProductQuantity(order.getProductQuantity());
        return orderResponse;
    }

}
