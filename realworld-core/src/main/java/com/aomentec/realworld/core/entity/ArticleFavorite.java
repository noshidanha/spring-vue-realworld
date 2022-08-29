package com.aomentec.realworld.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Noel Saldanha
 * @since 2022-08-29
 */
@Getter
@Setter
@TableName("article_favorite")
public class ArticleFavorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "article_id", type = IdType.AUTO)
    private String articleId;

    @TableId(value = "user_id", type = IdType.AUTO)
    private String userId;


}
