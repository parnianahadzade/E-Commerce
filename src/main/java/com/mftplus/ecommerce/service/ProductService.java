package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.api.dto.SearchRequest;
import com.mftplus.ecommerce.model.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);
    List<Product> findAllByCriteria(SearchRequest request);
}
