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
import com.test.rocketmq.consumer.model.Account;
import com.test.rocketmq.consumer.service.AccountService;

/**
 * Account Information Operation Interface
 * @author yu on 2018/11/27.
 */
@RestController
@RequestMapping("account")
public class AccountController {
    /**
     * log
     */
    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Resource
	private AccountService accountService;

    /**
	 * Add account info
	 * @param entity account info
	 * @return
	 */
	@PostMapping(value = "/add")
	public CommonResult save(Account entity) {
		return accountService.save(entity);
	}

    /**
	 * Update account info
	 * @param entity account info
	 * @return
	 */
	@PostMapping(value = "/update")
	public CommonResult update(Account entity) {
		return accountService.update(entity);
	}

    /**
	 * Delete account by id
	 * @param id account id
	 * @return
	 */
	@GetMapping(value = "/delete/{id}")
	public CommonResult delete(@PathVariable int id) {
		return accountService.delete(id);
	}

    /**
	 * Query account by id
	 * @param id account id
	 * @return
	 */
	@GetMapping(value = "/query/{id}")
	public CommonResult<Account> queryById(@PathVariable int id) {
		return accountService.queryById(id);
	}

    /**
	 * Pagination query account information
	 * @param offset page offset
	 * @param limit page limit
	 * @return
	 */
    @GetMapping(value = "/page/{offset}/{limit}")
    public PageSerializable<Account> queryPage(@PathVariable int offset,@PathVariable int limit) {
        return accountService.queryPage(offset,limit);
    }
}