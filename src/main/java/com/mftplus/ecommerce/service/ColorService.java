package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Color;

import java.util.Optional;

public interface ColorService {

    Optional<Color> findByName(String name);

    Color findById(Long id) throws NoContentException;
}
