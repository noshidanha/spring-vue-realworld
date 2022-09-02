package com.aomentec.realworld.web.controller.parameter;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : noelsaldanha
 * @created : 2022-09-02
**/

@Getter
@JsonRootName("user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateParam {

  @Builder.Default
  @Email(message = "must follow e-mail format")
  private String email = "";

  @Builder.Default private String password = "";
  @Builder.Default private String username = "";
  @Builder.Default private String bio = "";
  @Builder.Default private String image = "";
}
