package com.mftplus.ecommerce.api.validation;

import com.mftplus.ecommerce.api.dto.RegistrationBody;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegistrationBody> {

    @Override
    public boolean isValid(RegistrationBody body, ConstraintValidatorContext context) {
        if (body.getPassword() == null || body.getConfirmPassword() == null) {
            return false;
        }
        return body.getPassword().equals(body.getConfirmPassword());
    }
}
