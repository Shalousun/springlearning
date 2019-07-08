package com.sunyu.curator.model;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * Table:student
 * @author yu on 2019/07/08.
 */
@Data
@ToString
public class Student implements Serializable {

    private static final long serialVersionUID = -7172310289583798221L;

   	private Long id;

	private String name;

	private Integer age;

	private String address;


}