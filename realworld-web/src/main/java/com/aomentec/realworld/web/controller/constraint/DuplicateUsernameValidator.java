package com.aomentec.realworld.web.controller.constraint;

import com.aomentec.realworld.core.service.IUserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : noelsaldanha
 * @created : 2022-09-01
**/

public class DuplicateUsernameValidator implements ConstraintValidator<DuplicateUsernameConstraint, String> {

  @Autowired private IUserService userService;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return (value == null || value.isEmpty()) || userService.getUserByUsername(value) == null;
  }
}
