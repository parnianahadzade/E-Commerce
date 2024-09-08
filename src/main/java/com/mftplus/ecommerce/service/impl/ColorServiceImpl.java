package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Color;
import com.mftplus.ecommerce.repository.ColorRepository;
import com.mftplus.ecommerce.service.ColorService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color update(Color color) throws NoContentException {
        Color existingColor = colorRepository.findByIdAndDeletedFalse(color.getId()).orElseThrow(
                () -> new NoContentException("No Active Color Found with id : " + color.getId())
        );
        existingColor.setName(color.getName());
        existingColor.setHexCode(color.getHexCode());

        return colorRepository.save(existingColor);
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        colorRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Color Found with id : " + id)
        );
        colorRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        colorRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Color Found with id : " + id)
        );
        colorRepository.deleteById(id);
    }

    @Override
    public Color findByNameAndDeletedFalse(String name) throws NoContentException{
        return colorRepository.findByNameAndDeletedFalse(name).orElseThrow(
                () -> new NoContentException("No Active Color Found with name : " + name)
        );
    }

    @Override
    public void findByNameAndDeletedFalseWithOutReturn(String name) throws DuplicateException {
        Optional<Color> optional = colorRepository.findByNameAndDeletedFalse(name);
        if (optional.isEmpty()) {

        } else {
            throw new DuplicateException("A color with name : " + name + " already exists.");
        }
    }

    @Override
    public Color findById(Long id) throws NoContentException {
        return colorRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Color Found with id : " + id)
        );
    }

    @Override
    public Color findByIdAndDeletedFalse(Long id) throws NoContentException {
        return colorRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Color Found with id : " + id)
        );
    }

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public List<Color> findAllByDeletedFalse() {
        return colorRepository.findAllByDeletedFalse();
    }

    @Override
    public List<Color> findAllByDeletedFalse(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return colorRepository.findAllByDeletedFalse(pageable);
    }

    @Override
    public List<Color> findByNameStartsWithIgnoreCaseAndDeletedFalse(String name) {
        return colorRepository.findByNameStartsWithIgnoreCaseAndDeletedFalse(name);
    }
}
