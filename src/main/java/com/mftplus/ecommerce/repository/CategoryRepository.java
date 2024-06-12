package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByProductId(Long id);

    List<Category> findByName(String name);

}
