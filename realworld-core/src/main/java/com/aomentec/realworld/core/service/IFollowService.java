package com.aomentec.realworld.core.service;

import com.aomentec.realworld.core.entity.Follow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Noel Saldanha
 * @since 2022-08-29
 */
public interface IFollowService extends IService<Follow> {

  public boolean removeFollow(String userId, String followId);
  public boolean isFollowing(String userId, String followId); 

}
