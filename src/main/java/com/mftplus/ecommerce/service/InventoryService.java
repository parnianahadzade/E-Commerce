package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Inventory;

import java.util.List;

public interface InventoryService {

    Inventory save(Inventory inventory);

    Inventory update(Inventory inventory) throws NoContentException;

    void logicalRemove(Long id) throws NoContentException;

    void remove(Long id) throws NoContentException;

    Inventory findById(Long id) throws NoContentException;

    Inventory findByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Inventory> findAll();

    List<Inventory> findAllByDeletedFalse();

}
