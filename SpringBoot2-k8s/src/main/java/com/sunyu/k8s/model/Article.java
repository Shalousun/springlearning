package com.sunyu.k8s.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;
import lombok.ToString;

/**
 * Table:
 * @author yu on 2018/12/31.
 */
@Data
@ToString
public class Article implements Serializable {

    private static final long serialVersionUID = -6686909221192812650L;

   	private Integer articleId;

	private String title;

	private String category;


}