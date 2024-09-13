package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.api.dto.ProductSearchRequest;
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

    List<Product> findAllByCriteria(ProductSearchRequest request);

    List<Product> findByIdNotAndCode(Long id, String code, Integer pageNumber, Integer pageSize);

    Optional<Product> findByNameAndDeletedFalse(String name) throws DuplicateException;

    void findByMainImageIdAndDeletedFalse(Long id) throws DuplicateException;

    void findByImagesIdAndDeletedFalse(Long id) throws DuplicateException;
}
