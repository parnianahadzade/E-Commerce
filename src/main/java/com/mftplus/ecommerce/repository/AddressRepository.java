package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByDeletedFalse();

    Optional<Address> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Query("update addressEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Address> findByPersonIdAndDeletedFalse(Long id);
}
