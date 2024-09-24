package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByDeletedFalse();

    Optional<Role> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Query("update roleEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Role> findByNameAndDeletedFalse(String name);
}
