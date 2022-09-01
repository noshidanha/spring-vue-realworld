package com.aomentec.realworld.web.controller.parameter;

import com.aomentec.realworld.web.controller.constraint.DuplicateEmailConstraint;
import com.aomentec.realworld.web.controller.constraint.DuplicateUsernameConstraint;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author : noelsaldanha
 * @created : 2022-08-31
**/

@Getter
@JsonRootName("user")
@NoArgsConstructor
@AllArgsConstructor
public class RegisterParam {

  @NotBlank
  // @DuplicateUsernameConstraint
  private String username;

  @NotBlank(message = "e-mail can't be empty")
  @Email(message = "must follow e-mail format")
  @DuplicateEmailConstraint
  private String email;

  @NotBlank(message = "password can't be empty")
  private String password;

}
