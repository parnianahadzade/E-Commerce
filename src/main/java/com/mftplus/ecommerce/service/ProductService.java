package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findByCategoriesName(String name);
}
