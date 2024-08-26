package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Order;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.model.entity.enums.OrderStatus;
import com.mftplus.ecommerce.repository.OrderRepository;
import com.mftplus.ecommerce.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) throws NoContentException {
        orderRepository.findByIdAndDeletedFalse(order.getId()).orElseThrow(
                () -> new NoContentException("No Active Order Found with id : " + order.getId())
        );
        return orderRepository.save(order);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        orderRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Order Found with id : " + id)
        );
        orderRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        orderRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Order Found with id : " + id)
        );
        orderRepository.deleteById(id);
    }

    @Override
    public Order findById(Long id) throws NoContentException {
        return orderRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Order Found with id : " + id)
        );
    }

    @Override
    public Order findByIdAndDeletedFalse(Long id) throws NoContentException {
        return orderRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Order Found with id : " + id)
        );
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByDeletedFalse() {
        return orderRepository.findAllByDeletedFalse();
    }

    @Override
    public List<Order> findByUserAndDeletedFalse(User user) {
        return orderRepository.findByUserAndDeletedFalse(user);
    }

    @Override
    public List<Order> findOrdersWaitingOrValidatedAndUserAndDeletedFalse(User user) {
        return orderRepository.findOrdersWaitingOrValidatedAndUserAndDeletedFalse
                (OrderStatus.waitingForPayment,OrderStatus.successfulPayOrValidated,user);
    }

    @Override
    public List<Order> findCanceledOrdersAndUserAndDeletedFalse(User user) {
        return orderRepository.findCanceledOrdersAndUserAndDeletedFalse(OrderStatus.failedPayOrCanceled,user);
    }

    @Override
    public List<Order> findDeliveredOrdersAndUserAndDeletedFalse(User user) {
        return orderRepository.findDeliveredOrdersAndUserAndDeletedFalse(OrderStatus.delivered,user);
    }
}
