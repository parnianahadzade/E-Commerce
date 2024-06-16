package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Brand;
import com.mftplus.ecommerce.repository.BrandRepository;
import com.mftplus.ecommerce.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByName(name);
    }

    @Override
    public Brand findById(Long id) throws NoContentException {
        return brandRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Brand Found with id : " + id)
        );
    }
}
