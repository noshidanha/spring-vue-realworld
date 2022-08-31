package com.aomentec.realworld.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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

    @TableField("article_id")
    private String articleId;

    @TableField("user_id")
    private String userId;


}
