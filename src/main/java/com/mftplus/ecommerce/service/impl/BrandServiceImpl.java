package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Brand;
import com.mftplus.ecommerce.repository.BrandRepository;
import com.mftplus.ecommerce.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(Brand brand) throws NoContentException {
        brandRepository.findByIdAndDeletedFalse(brand.getId()).orElseThrow(
                () -> new NoContentException("No Active Brand Found with id : " + brand.getId())
        );
        return brandRepository.save(brand);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        brandRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Brand Found with id : " + id)
        );
        brandRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        brandRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Brand Found with id : " + id)
        );
        brandRepository.deleteById(id);
    }

    @Override
    public Brand findByNameAndDeletedFalse(String name) throws NoContentException{
        return brandRepository.findByNameAndDeletedFalse(name).orElseThrow(
                () -> new NoContentException("No Brand Found with name : " + name)
        );
    }

    @Override
    public Brand findById(Long id) throws NoContentException {
        return brandRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Brand Found with id : " + id)
        );
    }

    @Override
    public Brand findByIdAndDeletedFalse(Long id) throws NoContentException {
        return brandRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Brand Found with id : " + id)
        );
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> findAllByDeletedFalse() {
        return brandRepository.findAllByDeletedFalse();
    }
}
