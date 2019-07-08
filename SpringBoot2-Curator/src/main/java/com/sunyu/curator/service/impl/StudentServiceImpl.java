package com.sunyu.curator.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.curator.enums.ErrorCode;
import com.sunyu.curator.model.Student;
import com.sunyu.curator.dao.StudentDao;
import com.sunyu.curator.service.StudentService;

/**
 * @author yu on 2019/07/08.
 */
@Service("studentService")
public class StudentServiceImpl  implements StudentService{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Resource
	private StudentDao studentDao;

	@Override
	public CommonResult save(Student entity) {
        try {
        	studentDao.save(entity);
        } catch (Exception e) {
        	logger.error("StudentService添加数据异常：", e);
        	//抛出异常方便事务管理，并且会被RestExceptionHandler自动处理
        	throw new RuntimeException("添加数据失败");
        }
        return CommonResult.ok(ErrorCode.SUCCESS);
	}

	@Override
	public CommonResult update(Student entity) {
        try {
            studentDao.update(entity);
        } catch (Exception e) {
            logger.error("StudentService修改数据异常：", e);
            throw new RuntimeException("修改数据失败");
        }
        return CommonResult.ok(ErrorCode.SUCCESS);
	}

	@Override
	public CommonResult delete(Long id) {
        try {
            studentDao.delete(id);
        } catch (Exception e) {
            logger.error("StudentService删除数据异常：", e);
            throw new RuntimeException("删除数据异常");
        }
        return CommonResult.ok(ErrorCode.SUCCESS);
	}

	@Override
	public CommonResult queryById(Long id) {
	    Student entity = studentDao.queryById(id);
	    if (null != entity) {
	        //成功返回数据
        	return CommonResult.ok(ErrorCode.SUCCESS).setResult(entity);
        } else {
        	logger.info("StudentService未查询到数据，编号：{}", id);
        	return CommonResult.fail(ErrorCode.UNKNOWN_ERROR);
        }
	}

	@Override
    public PageSerializable queryPage(int offset, int limit) {
        PageHelper.offsetPage(offset, limit);
        List<Student> list = studentDao.queryPage();
        return new PageSerializable(list);
    }
}