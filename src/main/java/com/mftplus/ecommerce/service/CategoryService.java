package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    Category update(Category category) throws NoContentException;

    void logicalRemove(Long id) throws NoContentException;

    void remove(Long id) throws NoContentException;

    Category findByNameAndDeletedFalse(String name) throws NoContentException;

    void findByNameAndDeletedFalseWithOutReturn(String name) throws DuplicateException;

    Category findById(Long id) throws NoContentException;

    Category findByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Category> findAll();

    List<Category> findAllByDeletedFalse();

    List<Category> findByNameStartsWithIgnoreCaseAndDeletedFalse(String name);





}
