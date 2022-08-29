package com.aomentec.realworld.core.service.impl;

import com.aomentec.realworld.core.entity.User;
import com.aomentec.realworld.core.mapper.UserMapper;
import com.aomentec.realworld.core.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
