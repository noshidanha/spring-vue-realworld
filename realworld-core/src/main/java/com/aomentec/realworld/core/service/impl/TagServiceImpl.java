package com.aomentec.realworld.core.service.impl;

import com.aomentec.realworld.core.entity.Tag;
import com.aomentec.realworld.core.mapper.TagMapper;
import com.aomentec.realworld.core.service.ITagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
