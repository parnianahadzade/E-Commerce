package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Brand;

import java.util.List;

public interface BrandService {

    Brand save(Brand brand);

    Brand update(Brand brand) throws NoContentException;

    void logicalRemove(Long id) throws NoContentException;

    void remove(Long id) throws NoContentException;

    Brand findByNameAndDeletedFalse(String name) throws NoContentException;

    void findByNameAndDeletedFalseWithOutReturn(String name) throws DuplicateException;

    Brand findById(Long id) throws NoContentException;

    Brand findByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Brand> findAll();

    List<Brand> findAllByDeletedFalse();

    List<Brand> findAllByDeletedFalse(Integer pageNumber, Integer pageSize);

    List<Brand> findByNameStartsWithIgnoreCaseAndDeletedFalse(String name);
}
