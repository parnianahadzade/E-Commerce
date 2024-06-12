package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.model.entity.Product;
import com.mftplus.ecommerce.repository.ProductRepository;
import com.mftplus.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findProducts(){
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategoriesName(String name) {
        return productRepository.findByCategoriesName(name);
    }
}
