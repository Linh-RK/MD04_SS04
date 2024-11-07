package com.ra.md04_ss04_security.validation;

import com.ra.md04_ss04_security.service.user.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueValidate implements ConstraintValidator<UniqueUser,String> {
    private final UserService userService;

    public UniqueValidate(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userService.checkNameExist(value);
    }
}