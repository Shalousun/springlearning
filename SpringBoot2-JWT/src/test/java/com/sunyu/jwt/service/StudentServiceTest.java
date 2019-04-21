package com.sunyu.jwt.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.power.common.model.CommonResult;
import com.sunyu.jwt.model.Student;
import com.sunyu.jwt.service.StudentService;

/**
 * @author yu on 2019/04/21.
 */
public class StudentServiceTest extends ServiceBaseTest {

	@Resource
	private StudentService studentService;

	@Test
	public void testSave() {
		Student entity = new Student();
		CommonResult result = studentService.save(entity);
	}

	@Test
	public void testUpdate() {
		Student entity = new Student();
		CommonResult result = studentService.update(entity);
	}

	@Test
	public void testDelete() {
		CommonResult result = studentService.delete(1L);
	}

	@Test
	public void testQueryById() {

	}

    /**
     * 分页查询
     *
     */
	@Test
	public void testQueryPage() {

	}

}