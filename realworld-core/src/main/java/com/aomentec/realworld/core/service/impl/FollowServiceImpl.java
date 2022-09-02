package com.aomentec.realworld.core.service.impl;


import com.aomentec.realworld.core.entity.Follow;
import com.aomentec.realworld.core.mapper.FollowMapper;
import com.aomentec.realworld.core.service.IFollowService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Noel Saldanha
 * @since 2022-08-29
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {

  @Override
  public boolean removeFollow(String userId, String followId) {
    return remove(new QueryWrapper<Follow>().lambda()
            .eq(Follow::getUserId, userId)
            .and(q -> q.eq(Follow::getFollowId, followId)));
  }

  @Override
  public boolean isFollowing(String userId, String followId) {
      int resultCount = (int) count(new QueryWrapper<Follow>().lambda()
              .eq(Follow::getUserId, userId)
              .and(q -> q.eq(Follow::getFollowId, followId)));
      if (resultCount > 0) {
        return true;
      } else {
        return false;
      }
  }

}
