package com.aomentec.realworld.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : noelsaldanha
 * @created : 2022-08-29
**/

@Configuration
@MapperScan("com.aomentec.realworld.core.mapper")
public class MybatisPlusConfig {}
