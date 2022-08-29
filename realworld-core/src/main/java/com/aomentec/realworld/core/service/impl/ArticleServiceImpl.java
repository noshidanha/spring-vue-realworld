package com.aomentec.realworld.core.service.impl;

import com.aomentec.realworld.core.entity.Article;
import com.aomentec.realworld.core.mapper.ArticleMapper;
import com.aomentec.realworld.core.service.IArticleService;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
