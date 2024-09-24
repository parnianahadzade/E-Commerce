package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.api.dto.OrderSearchRequest;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Order;
import com.mftplus.ecommerce.model.entity.Role;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.model.entity.enums.OrderStatus;
import com.mftplus.ecommerce.repository.OrderRepository;
import com.mftplus.ecommerce.repository.OrderSearchRepository;
import com.mftplus.ecommerce.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderSearchRepository orderSearchRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderSearchRepository orderSearchRepository) {
        this.orderRepository = orderRepository;
        this.orderSearchRepository = orderSearchRepository;
    }


    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) throws NoContentException {
        orderRepository.findByIdAndDeletedFalse(order.getId()).orElseThrow(
                () -> new NoContentException("سفارش مورد نظر یافت نشد.")
        );
        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        orderRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("سفارش مورد نظر یافت نشد.")
        );
        orderRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        orderRepository.findById(id).orElseThrow(
                () -> new NoContentException("سفارش مورد نظر یافت نشد.")
        );
        orderRepository.deleteById(id);
    }

    @Override
    public Order findById(Long id) throws NoContentException {
        return orderRepository.findById(id).orElseThrow(
                () -> new NoContentException("سفارش مورد نظر یافت نشد.")
        );
    }

    @Override
    public Order findByIdAndDeletedFalse(Long id) throws NoContentException {
        return orderRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("سفارش مورد نظر یافت نشد.")
        );
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByCriteria(OrderSearchRequest request) {
        return orderSearchRepository.findAllByCriteria(request);
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

    @Override
    public boolean userHasPermissionToOrder(User user, Long id) {
        // Check if the user has an admin role
        for (Role role : user.getRoles()) {
            if (role.getId().equals(2L)) {
                return true; // Admin users have access to all orders
            }
        }

        // Check if the user ID matches the order ID
        if (Objects.equals(user.getId(), id)) {
            return true;
        }

        return false;
    }
}
