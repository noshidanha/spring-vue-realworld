package com.aomentec.realworld.web.controller;

import com.aomentec.realworld.core.entity.Follow;
import com.aomentec.realworld.core.entity.User;
import com.aomentec.realworld.core.service.IFollowService;
import com.aomentec.realworld.core.service.IUserService;
import com.aomentec.realworld.web.controller.response.ProfileResponse;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : noelsaldanha
 * @created : 2022-09-02
**/

@RestController
@RequestMapping("api/profiles/{username}")
public class ProfileController {
 
  @Autowired
  IUserService userService;

  @Autowired
  IFollowService followService;

  @GetMapping
  public ResponseEntity<?> getUserProfile(@PathVariable("username") String username,
                                          @AuthenticationPrincipal User user) {
    User profiledUser = userService.getUserByUsername(username);
    if (profiledUser == null) {
      return ResponseEntity.badRequest().body("User profile not found");
    }
    boolean isFollowing = followService.isFollowing(user.getId(), profiledUser.getId());
    return ResponseEntity.ok().body(new ProfileResponse(profiledUser, isFollowing));
  }

  @PostMapping(value = "follow")
  public ResponseEntity<?> follow(@PathVariable("username") String username,
                                  @AuthenticationPrincipal User user) {
    User profiledUser = userService.getUserByUsername(username);
    if (profiledUser == null) {
      return ResponseEntity.badRequest().body("User profile not found");
    }
    if (followService.isFollowing(user.getId(), profiledUser.getId())) {
      return ResponseEntity.ok().body(new ProfileResponse(profiledUser, true));
    }
    Follow followPair = new Follow();
    followPair.setUserId(user.getId());
    followPair.setFollowId(profiledUser.getId());
    boolean success = followService.saveOrUpdate(followPair);
    if (!success) {
      return ResponseEntity.badRequest().body("Something went wrong");
    }
    return ResponseEntity.ok().body(new ProfileResponse(profiledUser, true));
  }

  @DeleteMapping(value = "follow")
  public ResponseEntity<?> unfollow(@PathVariable("username") String username,
                                    @AuthenticationPrincipal User user) {
    User profiledUser = userService.getUserByUsername(username);
    if (profiledUser == null) {
      return ResponseEntity.badRequest().body("User profile not found");
    }
    boolean success = followService.removeFollow(user.getId(), profiledUser.getId());
    if (!success) {
      return ResponseEntity.badRequest().body("Something went wrong");
    }
    return ResponseEntity.ok().body(new ProfileResponse(profiledUser, false));
  }

}
