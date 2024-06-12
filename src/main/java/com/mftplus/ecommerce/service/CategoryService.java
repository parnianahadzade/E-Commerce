package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.model.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findByProductId(Long id);

    List<Category> findByName(String name);


}
