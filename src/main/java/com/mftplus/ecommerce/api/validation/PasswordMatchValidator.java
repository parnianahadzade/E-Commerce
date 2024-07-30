package com.mftplus.ecommerce.api.validation;

import com.mftplus.ecommerce.api.dto.RegistrationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegistrationDTO> {

    @Override
    public boolean isValid(RegistrationDTO registrationDTO, ConstraintValidatorContext context) {
        if (registrationDTO.getPassword() == null || registrationDTO.getConfirmPassword() == null) {
            return false;
        }
        return registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword());
    }
}
