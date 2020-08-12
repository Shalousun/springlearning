package com.boco.sunyu.controller;


import com.boco.sunyu.model.Student;
import com.boco.sunyu.service.StudentService;
import com.github.pagehelper.PageInfo;
import com.power.common.model.CommonResult;
import com.power.common.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by ApplicationPower.
 * @author yu on 2017年12月23日 星期六  22:01:41.
 */
@Controller
@RequestMapping("studentOne")
public class StudentOneController {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(StudentOneController.class);

	@Resource
	private StudentService studentOneService;

	@ResponseBody
	@GetMapping(value = "/add")
	public CommonResult save(Student entity) {
		entity.setName(RandomUtil.randomString(6));
		entity.setAddress(RandomUtil.randomString(8));
		entity.setAge(RandomUtil.randomInt(100));
		return studentOneService.save(entity);
	}

	@ResponseBody
	@PostMapping(value = "/update")
	public CommonResult update(Student entity) {
		return studentOneService.update(entity);
	}

	@ResponseBody
	@GetMapping(value = "/delete/{id}")
	public CommonResult delete(@PathVariable int id) {
		return studentOneService.delete(id);
	}

	@ResponseBody
	@GetMapping(value = "/query/{id}")
	public CommonResult queryById(@PathVariable int id) {
		return studentOneService.queryById(id);
	}

    @ResponseBody
    @GetMapping(value = "/page/{offset}/{limit}")
    public PageInfo queryPage(@PathVariable int offset,@PathVariable int limit) {
        return studentOneService.queryPage(offset,limit);
    }

    @ResponseBody
    @GetMapping(value = "/listMap")
    public List<Map<String,Object>> queryToListMap(@RequestParam Map<String,Object> params) {
        return studentOneService.queryToListMap(params);
    }
}