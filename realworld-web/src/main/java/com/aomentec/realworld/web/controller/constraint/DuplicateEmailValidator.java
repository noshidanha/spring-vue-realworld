package com.aomentec.realworld.web.controller.constraint;

import com.aomentec.realworld.core.service.IUserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : noelsaldanha
 * @created : 2022-09-01
**/

public class DuplicateEmailValidator implements ConstraintValidator<DuplicateEmailConstraint, String> {

  @Autowired private IUserService userService;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return (value == null || value.isEmpty()) || userService.getUserByEmail(value) == null;
  }
}
