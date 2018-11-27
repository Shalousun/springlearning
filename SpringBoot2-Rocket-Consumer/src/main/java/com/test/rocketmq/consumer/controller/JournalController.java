package com.test.rocketmq.consumer.controller;

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
import com.test.rocketmq.consumer.model.Journal;
import com.test.rocketmq.consumer.service.JournalService;

/**
 * Journal Information Operation Interface
 * @author yu on 2018/11/27.
 */
@RestController
@RequestMapping("journal")
public class JournalController {
    /**
     * log
     */
    private static Logger logger = LoggerFactory.getLogger(JournalController.class);

	@Resource
	private JournalService journalService;

    /**
	 * Add journal info
	 * @param entity journal info
	 * @return
	 */
	@PostMapping(value = "/add")
	public CommonResult save(Journal entity) {
		return journalService.save(entity);
	}

    /**
	 * Update journal info
	 * @param entity journal info
	 * @return
	 */
	@PostMapping(value = "/update")
	public CommonResult update(Journal entity) {
		return journalService.update(entity);
	}

    /**
	 * Delete journal by id
	 * @param id journal id
	 * @return
	 */
	@GetMapping(value = "/delete/{id}")
	public CommonResult delete(@PathVariable int id) {
		return journalService.delete(id);
	}

    /**
	 * Query journal by id
	 * @param id journal id
	 * @return
	 */
	@GetMapping(value = "/query/{id}")
	public CommonResult<Journal> queryById(@PathVariable int id) {
		return journalService.queryById(id);
	}

    /**
	 * Pagination query journal information
	 * @param offset page offset
	 * @param limit page limit
	 * @return
	 */
    @GetMapping(value = "/page/{offset}/{limit}")
    public PageSerializable<Journal> queryPage(@PathVariable int offset,@PathVariable int limit) {
        return journalService.queryPage(offset,limit);
    }
}