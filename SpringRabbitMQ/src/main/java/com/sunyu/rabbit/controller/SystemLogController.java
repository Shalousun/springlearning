package com.sunyu.rabbit.controller;


import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageInfo;
import com.boco.common.model.CommonResult;
import com.sunyu.rabbit.model.SystemLog;
import com.sunyu.rabbit.service.SystemLogService;

/**
 *
 * @author yu
 * @date 2017-04-28 22:50:20
 *
 */
@Controller
@RequestMapping("systemLog")
public class SystemLogController{
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SystemLogController.class);

	@Resource
	private SystemLogService systemLogService;

	@ResponseBody
	@PostMapping(value="/add")
	public CommonResult save(SystemLog entity){
		return systemLogService.save(entity);
	}

	@ResponseBody
	@PostMapping(value="/update")
	public CommonResult update(SystemLog entity){
		return systemLogService.update(entity);
	}

	@ResponseBody
	@GetMapping(value="/delete/{id}")
	public CommonResult delete(@PathVariable int id){
		return systemLogService.delete(id);
	}

	@ResponseBody
	@GetMapping(value="/query/{id}")
	public CommonResult queryById(@PathVariable int id){
		return systemLogService.queryById(id);
	}

    @ResponseBody
    @GetMapping(value = "page/{offset}/{limit}")
    public PageInfo queryPage(@PathVariable int offset,@PathVariable int limit){
        return systemLogService.queryPage(offset,limit);
    }
}