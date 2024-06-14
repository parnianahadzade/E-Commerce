package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductIdAndColorId(Long productId, Long colorId);
}
