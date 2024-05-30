package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.model.entity.Product;
import com.mftplus.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findProducts(){
        return productRepository.findAll();
    }
}
