package com.sunyu.curator.controller;

import javax.annotation.Resource;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.curator.model.Student;
import com.sunyu.curator.service.StudentService;

/**
 * Student Information Operation Interface
 * @author yu on 2019/07/08.
 */
@RestController
@RequestMapping("student")
public class StudentController {
    /**
     * log
     */
    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Resource
	private StudentService studentService;

    /**
	 * Add student info
	 * @param entity student info
	 * @return
	 */
	@PostMapping(value = "/add")
	public CommonResult save(Student entity) {
		return studentService.save(entity);
	}

    /**
	 * Update student info
	 * @param entity student info
	 * @return
	 */
	@PostMapping(value = "/update")
	public CommonResult update(Student entity) {
		return studentService.update(entity);
	}

    /**
	 * Delete student by id
	 * @param id student id
	 * @return
	 */
	@GetMapping(value = "/delete/{id}")
	public CommonResult delete(@PathVariable Long id) {
		return studentService.delete(id);
	}

    /**
	 * Query student by id
	 * @param id student id
	 * @return
	 */
	@GetMapping(value = "/query/{id}")
	public CommonResult<Student> queryById(@PathVariable Long id) {
		return studentService.queryById(id);
	}

    /**
	 * Pagination query student information
	 * @param offset page offset
	 * @param limit page limit
	 * @return
	 */
    @GetMapping(value = "/page/{offset}/{limit}")
    public PageSerializable<Student> queryPage(@PathVariable int offset,@PathVariable int limit) {
        return studentService.queryPage(offset,limit);
    }
}