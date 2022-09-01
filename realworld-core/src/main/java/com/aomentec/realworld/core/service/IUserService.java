package com.aomentec.realworld.core.service;

import com.aomentec.realworld.core.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Noel Saldanha
 * @since 2022-08-29
 */
public interface IUserService extends IService<User> {

  public User getUserByUsername(String username);
  public User getUserByEmail(String email);

}
