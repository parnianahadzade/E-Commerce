package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Brand;
import com.mftplus.ecommerce.repository.BrandRepository;
import com.mftplus.ecommerce.service.BrandService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(Brand brand) throws NoContentException {
        Brand existingBrand = brandRepository.findByIdAndDeletedFalse(brand.getId()).orElseThrow(
                () -> new NoContentException("No Active Brand Found with id : " + brand.getId())
        );
        existingBrand.setName(brand.getName());
        existingBrand.setExplanation(brand.getExplanation());

        return brandRepository.save(existingBrand);
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        brandRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Brand Found with id : " + id)
        );
        brandRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        brandRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Brand Found with id : " + id)
        );
        brandRepository.deleteById(id);
    }

    @Override
    public Brand findByNameAndDeletedFalse(String name) throws NoContentException{
        return brandRepository.findByNameAndDeletedFalse(name).orElseThrow(
                () -> new NoContentException("No Brand Found with name : " + name)
        );
    }

    @Override
    public void findByNameAndDeletedFalseWithOutReturn(String name) throws DuplicateException {
        Optional<Brand> optional = brandRepository.findByNameAndDeletedFalse(name);
        if (optional.isEmpty()) {

        } else {
            throw new DuplicateException("A brand with name : " + name + " already exists.");
        }
    }

    @Override
    public Brand findById(Long id) throws NoContentException {
        return brandRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Brand Found with id : " + id)
        );
    }

    @Override
    public Brand findByIdAndDeletedFalse(Long id) throws NoContentException {
        return brandRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Brand Found with id : " + id)
        );
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> findAllByDeletedFalse() {
        return brandRepository.findAllByDeletedFalse();
    }

    @Override
    public List<Brand> findAllByDeletedFalse(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return brandRepository.findAllByDeletedFalse(pageable);
    }

    @Override
    public List<Brand> findByNameStartsWithIgnoreCaseAndDeletedFalse(String name) {
        return brandRepository.findByNameStartsWithIgnoreCaseAndDeletedFalse(name);
    }
}
