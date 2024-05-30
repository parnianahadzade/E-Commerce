package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.OrderQuantities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderQuantitiesRepository extends JpaRepository<OrderQuantities, Long> {
}
