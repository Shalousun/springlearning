package com.sunyu.redis.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yu 2018/11/9.
 */
@Data
@ToString
public class Articles implements Serializable {
    private static final long serialVersionUID = -8518581735983853597L;

    /**
     * 文章编号
     */
    private long articleId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文件类型
     */
    private String category;


}
