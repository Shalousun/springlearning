package com.sunyu.apollo.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;
import lombok.ToString;

/**
 * Table:
 * @author yu on 2018/11/12.
 */
@Data
@ToString
public class Article implements Serializable {

    private static final long serialVersionUID = -5517778112470798039L;

   	private Integer articleId;

	private String title;

	private String category;


}