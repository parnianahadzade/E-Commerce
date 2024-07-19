package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    Role update(Role role) throws NoContentException;

    void logicalRemove(Long id) throws NoContentException;

    void remove(Long id) throws NoContentException;

    Role findById(Long id) throws NoContentException;

    Role findByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Role> findAll();

    List<Role> findAllByDeletedFalse();
}
