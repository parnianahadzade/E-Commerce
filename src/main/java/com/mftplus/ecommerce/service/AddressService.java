package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Address;

import java.util.List;

public interface AddressService {

    Address save(Address address);

    Address update(Address address) throws NoContentException;

    void logicalRemove(Long id) throws NoContentException;

    void remove(Long id) throws NoContentException;

    Address findById(Long id) throws NoContentException;

    Address findByIdAndDeletedFalse(Long id) throws NoContentException;

    Address findByPersonIdAndDeletedFalse(Long id) throws NoContentException;

    List<Address> findAll();

    List<Address> findAllByDeletedFalse();
}
