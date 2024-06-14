package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.model.entity.Inventory;
import com.mftplus.ecommerce.repository.InventoryRepository;
import com.mftplus.ecommerce.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Optional<Inventory> findByProductIdAndColorId(Long productId, Long colorId) {
        return inventoryRepository.findByProductIdAndColorId(productId,colorId);
    }
}
