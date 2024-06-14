package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.model.entity.Category;
import com.mftplus.ecommerce.repository.CategoryRepository;
import com.mftplus.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public void Save(Category category) {
        categoryRepository.save(category);
    }


}
