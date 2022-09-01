package com.aomentec.realworld.web.controller.response;

import com.aomentec.realworld.core.entity.User;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/**
 * @author : noelsaldanha
 * @created : 2022-09-01
**/

@Getter
@JsonRootName("user")
public class UserResponse {
  private String email;
  private String token;
  private String username;
  private String bio;
  private String image;

  public UserResponse(User user, String token) {
    this.email = user.getEmail();
    this.token = token;
    this.username = user.getUsername();
    this.bio = user.getBio();
    this.image = user.getImage();
  }
}
