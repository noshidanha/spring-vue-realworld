package com.aomentec.realworld.web.controller;

import com.aomentec.realworld.core.entity.User;
import com.aomentec.realworld.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("api/user")
public class UserController {
  
  @Autowired
  private IUserService userService;

  @GetMapping(value = "/{id}", produces = "application/json")
  public User getUser(@PathVariable String id) {
    return userService.getById(id);
  }



}
