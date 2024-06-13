package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.api.dto.SearchRequest;
import com.mftplus.ecommerce.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findByBrandName(String name);

    List<Product> findByBrandNameAndCategoriesName(String brandName, String categoryName);

    List<Product> findByCategoriesName(String name);

    List<Product> findAllByCriteria(SearchRequest request);
}
