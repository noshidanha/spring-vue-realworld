package com.aomentec.realworld.gen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

/**
 * @author : noelsaldanha
 * @created : 2022-08-26
**/

public class MyBatisPlusGenerate {

  private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
      .Builder("jdbc:mysql://localhost:3306/realworld?characterEncoding=utf-8", "root", "realworld123")
      .dbQuery(new MySqlQuery())
      .typeConvert(new MySqlTypeConvert())
      .keyWordsHandler(new MySqlKeyWordsHandler())
      .build();

  public static void main (String[] args) {
    

    // Set global config
    String projectPath = System.getProperty("user.dir");
    GlobalConfig gc = new GlobalConfig
      .Builder()
      .disableOpenDir()
      .outputDir(projectPath + "/opt/src/main/java")
      .author("Noel Saldanha")
      .dateType(DateType.TIME_PACK)
      .build();

    PackageConfig pc = new PackageConfig
      .Builder("com.aomentec", "realworld")
      .entity("entity")
      .service("service")
      .serviceImpl("service.impl")
      .mapper("mapper")
      .controller("controller")
      .build();

    StrategyConfig sc = new StrategyConfig
      .Builder()
      .enableSchema()
      .mapperBuilder()
      .enableBaseResultMap()
      .enableBaseColumnList()
      .build();

    AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG)
      .global(gc)
      .packageInfo(pc)
      .strategy(sc);
    
    generator.execute();

  }

}
