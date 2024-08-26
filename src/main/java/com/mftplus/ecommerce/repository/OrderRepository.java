package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Order;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.model.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT o FROM orderEntity o WHERE o.orderStatus IN (:waitingForPayment, :validated) AND o.user = :user AND o.deleted = false ")
    List<Order> findOrdersWaitingOrValidatedAndUserAndDeletedFalse
            (@Param("waitingForPayment") OrderStatus waitingForPayment,
             @Param("validated") OrderStatus successfulPayOrValidated, @Param("user") User user);

    @Query("SELECT o FROM orderEntity o WHERE o.orderStatus = :canceled AND o.user = :user AND o.deleted = false")
    List<Order> findCanceledOrdersAndUserAndDeletedFalse
            (@Param("canceled") OrderStatus failedPayOrCanceled, @Param("user") User user);

    @Query("SELECT o FROM orderEntity o WHERE o.orderStatus = :delivered AND o.user = :user AND o.deleted = false")
    List<Order> findDeliveredOrdersAndUserAndDeletedFalse
            (@Param("delivered") OrderStatus delivered, @Param("user") User user);
}
