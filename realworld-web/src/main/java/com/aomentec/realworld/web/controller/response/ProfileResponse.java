package com.aomentec.realworld.web.controller.response;

import com.aomentec.realworld.core.entity.User;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;

/**
 * @author : noelsaldanha
 * @created : 2022-09-02
**/

@Getter
@JsonRootName(value = "profile")
public class ProfileResponse {

  private String username;
  private String bio;
  private String image;
  private boolean following;

  public ProfileResponse(User user, boolean following) {
    this.username = user.getUsername();
    this.bio = user.getBio();
    this.image = user.getImage();
    this.following = following;
  }
}
