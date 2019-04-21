package com.sunyu.jwt.model;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * Table:student
 * @author yu on 2019/04/21.
 */
@Data
@ToString
public class Student implements Serializable {

    private static final long serialVersionUID = -6928239699851671778L;

   	private Long id;

	private String name;

	private Integer age;

	private String address;


}