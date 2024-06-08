package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.api.dto.RegistrationBody;
import com.mftplus.ecommerce.exception.EmailFailureException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.UserAlreadyExistsException;
import com.mftplus.ecommerce.model.entity.User;

public interface UserService {

    User save(RegistrationBody registrationBody) throws UserAlreadyExistsException , EmailFailureException;

    void update(User user) throws NoContentException;

    User findById(Long id) throws NoContentException;


}
