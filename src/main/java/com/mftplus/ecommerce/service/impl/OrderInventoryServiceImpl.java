package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.OrderInventory;
import com.mftplus.ecommerce.repository.OrderInventoryRepository;
import com.mftplus.ecommerce.service.OrderInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInventoryServiceImpl implements OrderInventoryService {

    private final OrderInventoryRepository orderInventoryRepository;

    public OrderInventoryServiceImpl(OrderInventoryRepository orderInventoryRepository) {
        this.orderInventoryRepository = orderInventoryRepository;
    }

    @Override
    public OrderInventory save(OrderInventory orderInventory) {
        return orderInventoryRepository.save(orderInventory);
    }

    @Override
    public OrderInventory update(OrderInventory orderInventory) throws NoContentException {
        orderInventoryRepository.findByIdAndDeletedFalse(orderInventory.getId()).orElseThrow(
                () -> new NoContentException("No Active OrderInventory Found with id : " + orderInventory.getId())
        );
        return orderInventoryRepository.save(orderInventory);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        orderInventoryRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active OrderInventory Found with id : " + id)
        );
        orderInventoryRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        orderInventoryRepository.findById(id).orElseThrow(
                () -> new NoContentException("No OrderInventory Found with id : " + id)
        );
        orderInventoryRepository.logicalRemove(id);
    }

    @Override
    public OrderInventory findById(Long id) throws NoContentException {
        return orderInventoryRepository.findById(id).orElseThrow(
                () -> new NoContentException("No OrderInventory Found with id : " + id)
        );
    }

    @Override
    public OrderInventory findByIdAndDeletedFalse(Long id) throws NoContentException {
        return orderInventoryRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active OrderInventory Found with id : " + id)
        );
    }

    @Override
    public List<OrderInventory> findAll() {
        return orderInventoryRepository.findAll();
    }

    @Override
    public List<OrderInventory> findAllByDeletedFalse() {
        return orderInventoryRepository.findAllByDeletedFalse();
    }
}
