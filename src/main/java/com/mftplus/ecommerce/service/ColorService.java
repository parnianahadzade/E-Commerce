package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Color;

import java.util.List;

public interface ColorService {

    Color save(Color color);

    Color update(Color color) throws NoContentException;

    void logicalRemove(Long id) throws NoContentException;

    void remove(Long id) throws NoContentException;

    Color findByNameAndDeletedFalse(String name) throws NoContentException;

    void findByNameAndDeletedFalseWithOutReturn(String name) throws DuplicateException;

    Color findById(Long id) throws NoContentException;

    Color findByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Color> findAll();

    List<Color> findAllByDeletedFalse();

    List<Color> findAllByDeletedFalse(Integer pageNumber, Integer pageSize);

    List<Color> findByNameStartsWithIgnoreCaseAndDeletedFalse(String name);
}
