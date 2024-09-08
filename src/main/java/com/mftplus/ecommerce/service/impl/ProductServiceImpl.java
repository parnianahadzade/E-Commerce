package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.api.dto.SearchRequest;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Product;
import com.mftplus.ecommerce.repository.ProductRepository;
import com.mftplus.ecommerce.repository.ProductSearchRepository;
import com.mftplus.ecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductSearchRepository productSearchRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductSearchRepository productSearchRepository) {
        this.productRepository = productRepository;
        this.productSearchRepository = productSearchRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) throws NoContentException {
        productRepository.findByIdAndDeletedFalse(product.getId()).orElseThrow(
                () -> new NoContentException("No Active Product Found with id : " + product.getId())
        );
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        productRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Product Found with id : " + id)
        );
        productRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        productRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Product Found with id : " + id)
        );
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) throws NoContentException {
        return productRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Product Found with id : " + id)
        );
    }

    @Override
    public Product findByIdAndDeletedFalse(Long id) throws NoContentException {
        return productRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Product Found with id : " + id)
        );
    }

    @Override
    public List<Product> findAllByCriteria(SearchRequest request) {
        return productSearchRepository.findAllByCriteria(request);
    }

    @Override
    public List<Product> findByCodeAndDeletedFalse(String code) {
        return productRepository.findByCodeAndDeletedFalse(code);
    }

    @Override
    public List<Product> findByIdNotAndCode(Long id, String code) {
        return productRepository.findByIdNotAndCode(id,code);
    }

    @Override
    public Optional<Product> findByNameAndDeletedFalse(String name) throws DuplicateException {
        Optional<Product> optional = productRepository.findByNameAndDeletedFalse(name);
        if (optional.isEmpty()) {
            return optional;
        } else {
            throw new DuplicateException("A product with name : " + name + " already exists.");
        }
    }

    @Override
    public Optional<Product> findByMainImageIdAndDeletedFalse(Long id) throws DuplicateException {
        Optional<Product> optional = productRepository.findByMainImageIdAndDeletedFalse(id);
        if (optional.isEmpty()) {
            return optional;
        } else {
            throw new DuplicateException("A product with mainImageId : " + id + " already exists.");
        }
    }

    @Override
    public Optional<Product> findByImagesIdAndDeletedFalse(Long id) throws DuplicateException {
        Optional<Product> optional = productRepository.findByImagesIdAndDeletedFalse(id);
        if (optional.isEmpty()) {
            return optional;
        } else {
            throw new DuplicateException("A product with imageId : " + id + " already exists.");
        }
    }
}
