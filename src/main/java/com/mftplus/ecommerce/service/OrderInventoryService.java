package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.OrderInventory;

import java.util.List;

public interface OrderInventoryService {

    OrderInventory save(OrderInventory orderInventory);

    OrderInventory update(OrderInventory orderInventory) throws NoContentException;

    void logicalRemove(Long id) throws NoContentException;

    void remove(Long id) throws NoContentException;

    OrderInventory findById(Long id) throws NoContentException;

    OrderInventory findByIdAndDeletedFalse(Long id) throws NoContentException;

    List<OrderInventory> findAll();

    List<OrderInventory> findAllByDeletedFalse();
}
