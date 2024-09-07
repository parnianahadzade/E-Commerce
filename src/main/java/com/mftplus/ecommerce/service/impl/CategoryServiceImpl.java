package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Category;
import com.mftplus.ecommerce.repository.CategoryRepository;
import com.mftplus.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) throws NoContentException {
        Category existingCategory = categoryRepository.findByIdAndDeletedFalse(category.getId()).orElseThrow(
                () -> new NoContentException("No Active Category Found with id : " + category.getId())
        );
        existingCategory.setParentCategory(category.getParentCategory());
        existingCategory.setName(category.getName());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        categoryRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Category Found with id : " + id)
        );
        categoryRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        categoryRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Category Found with id : " + id)
        );
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findByNameAndDeletedFalse(String name) throws NoContentException {
        return categoryRepository.findByNameAndDeletedFalse(name).orElseThrow(
                () -> new NoContentException("No Category Found with name : " + name)
        );
    }

    @Override
    public void findByNameAndDeletedFalseWithOutReturn(String name) throws DuplicateException {
        Optional<Category> optional = categoryRepository.findByNameAndDeletedFalse(name);
        if (optional.isEmpty()) {

        } else {
            throw new DuplicateException("A category with name : " + name + " already exists.");
        }
    }

    @Override
    public Category findById(Long id) throws NoContentException {
        return categoryRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Category Found with id : " + id)
        );
    }

    @Override
    public Category findByIdAndDeletedFalse(Long id) throws NoContentException {
        return categoryRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Category Found with id : " + id)
        );
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllByDeletedFalse() {
        return categoryRepository.findAllByDeletedFalse();
    }

    @Override
    public List<Category> findByNameStartsWithIgnoreCaseAndDeletedFalse(String name) {
        return categoryRepository.findByNameStartsWithIgnoreCaseAndDeletedFalse(name);
    }
}
