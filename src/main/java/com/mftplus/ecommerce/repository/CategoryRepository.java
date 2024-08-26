package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameAndDeletedFalse(String name);

    List<Category> findAllByDeletedFalse();

    Optional<Category> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Query("update categoryEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<Category> findByNameStartsWithIgnoreCaseAndDeletedFalse(String name);


}
