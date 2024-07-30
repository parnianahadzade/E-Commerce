package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.OrderInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderInventoryRepository extends JpaRepository<OrderInventory, Long> {
    Optional<OrderInventory> findByIdAndDeletedFalse(Long id);


    // TODO: 7/29/2024 findById 

    List<OrderInventory> findAllByDeletedFalse();
    
    @Modifying
    @Query("update orderEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
