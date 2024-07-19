package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Role;
import com.mftplus.ecommerce.repository.RoleRepository;
import com.mftplus.ecommerce.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) throws NoContentException {
        roleRepository.findByIdAndDeletedFalse(role.getId()).orElseThrow(
                () -> new NoContentException("No Active Role Found with id : " + role.getId())
        );
        return roleRepository.save(role);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        roleRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Role Found with id : " + id)
        );
        roleRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        roleRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Role Found with id : " + id)
        );
        roleRepository.deleteById(id);
    }

    @Override
    public Role findById(Long id) throws NoContentException {
        return roleRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Role Found with id : " + id)
        );
    }

    @Override
    public Role findByIdAndDeletedFalse(Long id) throws NoContentException {
        return roleRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Role Found with id : " + id)
        );
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findAllByDeletedFalse() {
        return roleRepository.findAllByDeletedFalse();
    }
}
