package com.aomentec.realworld.web.controller.parameter;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;

/**
 * @author : noelsaldanha
 * @created : 2022-08-31
**/

@Getter
@JsonRootName("user")
public class LoginParam {
  @NotBlank(message = "e-mail can't be empty")
  @Email(message = "must follow e-mail format")
  private String email;

  @NotBlank(message = "password can't be empty")
  private String password;

}
