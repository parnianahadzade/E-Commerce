package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Inventory;
import com.mftplus.ecommerce.repository.InventoryRepository;
import com.mftplus.ecommerce.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory update(Inventory inventory) throws NoContentException {
        inventoryRepository.findByIdAndDeletedFalse(inventory.getId()).orElseThrow(
                () -> new NoContentException("No Active Inventory Found with id : " + inventory.getId())
        );
        return inventoryRepository.save(inventory);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        inventoryRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Inventory Found with id : " + id)
        );
        inventoryRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        inventoryRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Inventory Found with id : " + id)
        );
        inventoryRepository.deleteById(id);
    }

    @Override
    public Inventory findById(Long id) throws NoContentException {
        return inventoryRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Inventory Found with id : " + id)
        );
    }

    @Override
    public Inventory findByIdAndDeletedFalse(Long id) throws NoContentException {
        return inventoryRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Inventory Found with id : " + id)
        );
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> findAllByDeletedFalse() {
        return inventoryRepository.findAllByDeletedFalse();
    }

}
