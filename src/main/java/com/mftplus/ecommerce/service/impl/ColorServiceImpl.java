package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Color;
import com.mftplus.ecommerce.repository.ColorRepository;
import com.mftplus.ecommerce.service.ColorService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public Optional<Color> findByName(String name) {
        return colorRepository.findByName(name);
    }

    @Override
    public Color findById(Long id) throws NoContentException {
        return colorRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Color Found with id : " + id)
        );
    }
}
