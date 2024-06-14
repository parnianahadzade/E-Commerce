package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.model.entity.Inventory;

import java.util.Optional;

public interface InventoryService {

    Inventory save(Inventory inventory);

    Optional<Inventory> findByProductIdAndColorId(Long productId, Long colorId);
}
