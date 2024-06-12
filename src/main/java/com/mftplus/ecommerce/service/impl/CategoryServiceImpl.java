package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.model.entity.Category;
import com.mftplus.ecommerce.repository.CategoryRepository;
import com.mftplus.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findByProductId(Long id) {
        return categoryRepository.findByProductId(id);
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
