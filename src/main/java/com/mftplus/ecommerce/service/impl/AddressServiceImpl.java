package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Address;
import com.mftplus.ecommerce.repository.AddressRepository;
import com.mftplus.ecommerce.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address) throws NoContentException {
        addressRepository.findByIdAndDeletedFalse(address.getId()).orElseThrow(
                () -> new NoContentException("No Active Address Found with id : " + address.getId())
        );
        return addressRepository.save(address);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        addressRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Address Found with id : " + id)
        );
        addressRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        addressRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Address Found with id : " + id)
        );
        addressRepository.deleteById(id);
    }

    @Override
    public Address findById(Long id) throws NoContentException {
        return addressRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Address Found with id : " + id)
        );
    }

    @Override
    public Address findByIdAndDeletedFalse(Long id) throws NoContentException {
        return addressRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Address Found with id : " + id)
        );
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public List<Address> findAllByDeletedFalse() {
        return addressRepository.findAllByDeletedFalse();
    }
}
