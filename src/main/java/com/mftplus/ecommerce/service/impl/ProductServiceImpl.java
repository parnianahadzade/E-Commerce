package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.api.dto.ProductSearchRequest;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Product;
import com.mftplus.ecommerce.repository.ProductRepository;
import com.mftplus.ecommerce.repository.ProductSearchRepository;
import com.mftplus.ecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Product existingProduct = productRepository.findByIdAndDeletedFalse(product.getId()).orElseThrow(
                () -> new NoContentException("No Active Product Found with id : " + product.getId())
        );
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCode(product.getCode());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setCategories(product.getCategories());
        existingProduct.setColor(product.getColor());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setOffPercent(product.getOffPercent());
        existingProduct.setMaterial(product.getMaterial());
        existingProduct.setPattern(product.getPattern());
        existingProduct.setHeight(product.getHeight());
        existingProduct.setMainCategory(product.getMainCategory());
        existingProduct.setImages(product.getImages());
        existingProduct.setMainImage(product.getMainImage());

        return productRepository.save(existingProduct);
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
    public List<Product> findAllByCriteria(ProductSearchRequest request) {
        return productSearchRepository.findAllByCriteria(request);
    }

    @Override
    public List<Product> findByIdNotAndCode(Long id, String code, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return productRepository.findByIdNotAndCodeAndDeletedFalse(id,code,pageable);
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
    public void findByMainImageIdAndDeletedFalse(Long id) throws DuplicateException {
        Optional<Product> optional = productRepository.findByMainImageIdAndDeletedFalse(id);
        if (optional.isEmpty()) {
        } else {
            throw new DuplicateException("A product with mainImageId : " + id + " already exists.");
        }
    }

    @Override
    public void findByImagesIdAndDeletedFalse(Long id) throws DuplicateException {
        Optional<Product> optional = productRepository.findByImagesIdAndDeletedFalse(id);
        if (optional.isEmpty()) {
        } else {
            throw new DuplicateException("A product with imageId : " + id + " already exists.");
        }
    }
}
