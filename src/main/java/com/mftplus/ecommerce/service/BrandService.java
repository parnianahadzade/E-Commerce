package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Brand;

import java.util.Optional;

public interface BrandService {
    Optional<Brand> findByName(String name);

    Brand findById(Long id) throws NoContentException;
}
