package com.aomentec.realworld.web.controller;

import com.aomentec.realworld.core.entity.User;
import com.aomentec.realworld.core.service.IUserService;
import com.aomentec.realworld.web.config.security.JwtTokenProvider;
import com.aomentec.realworld.web.controller.parameter.LoginParam;
import com.aomentec.realworld.web.controller.parameter.RegisterParam;
import com.aomentec.realworld.web.controller.parameter.UserUpdateParam;
import com.aomentec.realworld.web.controller.response.UserResponse;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 * <p>
 * User Controller API
 * </p>
 *
 * @author Noel Saldanha
 * @since 2022-08-29
 */
@RestController
@RequestMapping("api")
public class UserController {
  
  @Autowired
  private IUserService userService;
  
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // Registration
  @PostMapping(value = "users")
  public ResponseEntity<?> createUser(@Valid @RequestBody RegisterParam registerParam) {
    User user = new User();
    user.setUsername(registerParam.getUsername());
    user.setPassword(passwordEncoder.encode(registerParam.getPassword()));
    user.setEmail(registerParam.getEmail());

    if (userService.save(user)) {
      String token = jwtTokenProvider.createToken(user.getId());
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(user, token));
    } else {
      return ResponseEntity.badRequest().body("Invalid registration");
    }
  }

  // Authentication
  @PostMapping(value = "users/login")
  public ResponseEntity<?> userLogin(@Valid @RequestBody LoginParam loginParam) {
    User user = userService.getUserByEmail(loginParam.getEmail());
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    if (!passwordEncoder.matches(loginParam.getPassword(), user.getPassword())) {
      return ResponseEntity.notFound().build();
    }
    
    String token = jwtTokenProvider.createToken(user.getId());
    return ResponseEntity.ok(new UserResponse(user, token));
  }

  // Get current user
  @GetMapping(value = "user")
  public ResponseEntity<?> getUser(@AuthenticationPrincipal User currentUser,
                                   @RequestHeader(value = "Authorization") String token) {
    if (currentUser == null) {
      return ResponseEntity.badRequest().body("Not logged in");
    }
    return ResponseEntity.ok(new UserResponse(currentUser, token.split(" ")[1]));
  }

  @PutMapping(value = "user")
  public ResponseEntity<?> updateUser(@AuthenticationPrincipal User currentUser,
                                      @RequestHeader(value = "Authorization") String token,
                                      @Valid @RequestBody UserUpdateParam userUpdateParam) {
    if (currentUser == null) {
      return ResponseEntity.badRequest().body("Not logged in");
    }

    User newUser = userFieldUpdate(currentUser, userUpdateParam);
    userService.updateById(newUser);
    return ResponseEntity.ok(new UserResponse(newUser, token.split(" ")[1]));
  }

  private User userFieldUpdate(User user, UserUpdateParam userUpdateParam) {
    if (!userUpdateParam.getEmail().isEmpty()) {
      user.setEmail(userUpdateParam.getEmail());
    }
    if (!userUpdateParam.getPassword().isEmpty()) {
      user.setPassword(passwordEncoder.encode(userUpdateParam.getPassword()));
    }
    if (!userUpdateParam.getUsername().isEmpty()) {
      user.setUsername(userUpdateParam.getUsername());
    }
    if (!userUpdateParam.getBio().isEmpty()) {
      user.setBio(userUpdateParam.getBio());
    }
    if (!userUpdateParam.getImage().isEmpty()) {
      user.setImage(userUpdateParam.getImage());
    }
    return user;
  }

}
