package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.EmailFailureException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.model.entity.User;

import java.util.List;

public interface UserService {

    User save(User user) throws DuplicateException, EmailFailureException;

    void update(User user) throws NoContentException;

    void logicalRemove(Long id) throws NoContentException;

    void remove(Long id) throws NoContentException;

    User findById(Long id) throws NoContentException;

    User findByIdAndDeletedFalse(Long id) throws NoContentException;

    User findByUsernameIgnoreCaseAndDeletedFalse(String username) throws NoContentException;

    User findByEmailIgnoreCaseAndDeletedFalse(String email) throws NoContentException;

    User findByPersonIdAndDeletedFalse(Long id) throws NoContentException;

    List<User> findAll();

    List<User> findAllByDeletedFalse();


}
