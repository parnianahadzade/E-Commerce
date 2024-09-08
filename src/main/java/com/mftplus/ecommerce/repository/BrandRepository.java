package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Brand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByNameAndDeletedFalse(String name);

    List<Brand> findAllByDeletedFalse();

    List<Brand> findAllByDeletedFalse(Pageable pageable);

    Optional<Brand> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Query("update brandEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<Brand> findByNameStartsWithIgnoreCaseAndDeletedFalse(String name);

}
