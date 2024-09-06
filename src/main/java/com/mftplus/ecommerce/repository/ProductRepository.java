package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameAndDeletedFalse(String name);
    Optional<Product> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Query("update productEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<Product> findByCodeAndDeletedFalse(String code);

    List<Product> findByIdNotAndCode(Long id, String code);

    Optional<Product> findByMainImageIdAndDeletedFalse(Long id);

    Optional<Product> findByImagesIdAndDeletedFalse(Long id);
}
