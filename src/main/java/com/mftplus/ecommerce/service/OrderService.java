package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Order;
import com.mftplus.ecommerce.model.entity.User;

import java.util.List;

public interface OrderService {

    Order save(Order order);

    Order update(Order order) throws NoContentException;

    void logicalRemove(Long id) throws NoContentException;

    void remove(Long id) throws NoContentException;

    Order findById(Long id) throws NoContentException;

    Order findByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Order> findAll();

    List<Order> findAllByDeletedFalse();

    List<Order> findByUserAndDeletedFalse(User user);

    List<Order> findOrdersWaitingOrValidatedAndUserAndDeletedFalse(User user);

    List<Order> findCanceledOrdersAndUserAndDeletedFalse(User user);

    List<Order> findDeliveredOrdersAndUserAndDeletedFalse(User user);

}
