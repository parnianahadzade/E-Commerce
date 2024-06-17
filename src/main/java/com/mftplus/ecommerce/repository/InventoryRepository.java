package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductIdAndColorIdAndDeletedFalse(Long productId, Long colorId);

    List<Inventory> findAllByDeletedFalse();

    Optional<Inventory> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Query("update inventoryEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

}
