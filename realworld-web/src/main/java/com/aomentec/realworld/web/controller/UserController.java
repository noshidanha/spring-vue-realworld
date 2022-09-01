package com.aomentec.realworld.web.controller;

import com.aomentec.realworld.core.entity.User;
import com.aomentec.realworld.core.service.IUserService;
import com.aomentec.realworld.web.config.security.JwtTokenProvider;
import com.aomentec.realworld.web.controller.parameter.LoginParam;
import com.aomentec.realworld.web.controller.parameter.RegisterParam;
import com.aomentec.realworld.web.controller.response.UserResponse;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("api/users")
public class UserController {
  
  @Autowired
  private IUserService userService;
  
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping(value = "{id}", produces = "application/json")
  public User getUser(@PathVariable String id) {
    return userService.getById(id);
  }

  // Registration
  @PostMapping(value = "")
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
  @PostMapping(value = "login")
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

}
