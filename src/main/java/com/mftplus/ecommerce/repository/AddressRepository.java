package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

//    List<Address> findByUser_Id(Long id);
}
