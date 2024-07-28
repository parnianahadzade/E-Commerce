package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Order;
import com.mftplus.ecommerce.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserAndDeletedFalse(User user);

    List<Order> findAllByDeletedFalse();

    Optional<Order> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Query("update orderEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
