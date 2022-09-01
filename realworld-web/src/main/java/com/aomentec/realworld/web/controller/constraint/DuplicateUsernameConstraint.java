package com.aomentec.realworld.web.controller.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author : noelsaldanha
 * @created : 2022-09-01
**/

@Constraint(validatedBy = DuplicateUsernameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicateUsernameConstraint {
  String message() default "duplicated username";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
