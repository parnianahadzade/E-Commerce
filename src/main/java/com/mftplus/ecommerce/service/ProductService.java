package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.api.dto.SearchRequest;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    Product update(Product product) throws NoContentException;

    void logicalRemove(Long id) throws NoContentException;

    void remove(Long id) throws NoContentException;

    Product findById(Long id) throws NoContentException;

    Product findByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Product> findAllByCriteria(SearchRequest request);

    List<Product> findByCodeAndDeletedFalse(String code);

    List<Product> findByIdNotAndCode(Long id, String code);

    Optional<Product> findByNameAndDeletedFalse(String name) throws DuplicateException;

    Optional<Product> findByMainImageIdAndDeletedFalse(Long id) throws DuplicateException;

    Optional<Product> findByImagesIdAndDeletedFalse(Long id) throws DuplicateException;
}
