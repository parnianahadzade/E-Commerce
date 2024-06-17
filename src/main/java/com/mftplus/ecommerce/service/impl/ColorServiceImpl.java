package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Color;
import com.mftplus.ecommerce.repository.ColorRepository;
import com.mftplus.ecommerce.service.ColorService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        colorRepository.findByIdAndDeletedFalse(color.getId()).orElseThrow(
                () -> new NoContentException("No Active Color Found with id : " + color.getId())
        );
        return colorRepository.save(color);
    }

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
}
