package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.model.entity.Brand;

import java.util.Optional;

public interface BrandService {

    Optional<Brand> findByName(String name);
}
