package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.model.entity.Order;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
