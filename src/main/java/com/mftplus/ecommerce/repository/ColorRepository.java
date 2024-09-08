package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Color;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color> findByNameAndDeletedFalse(String name);

    List<Color> findAllByDeletedFalse();

    List<Color> findAllByDeletedFalse(Pageable pageable);

    Optional<Color> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Query("update colorEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<Color> findByNameStartsWithIgnoreCaseAndDeletedFalse(String name);
}
