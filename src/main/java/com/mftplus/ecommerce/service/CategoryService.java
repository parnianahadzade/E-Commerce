package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Category;

import java.util.Optional;

public interface CategoryService {

    Optional<Category> findByName(String name);

    void Save(Category category);

    Category findById(Long id) throws NoContentException;





}
