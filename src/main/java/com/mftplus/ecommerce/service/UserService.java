package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.model.entity.LocalUser;

import java.util.List;

public interface UserService {
    void save(LocalUser user) throws Exception;
    List<LocalUser> findAll();

}
