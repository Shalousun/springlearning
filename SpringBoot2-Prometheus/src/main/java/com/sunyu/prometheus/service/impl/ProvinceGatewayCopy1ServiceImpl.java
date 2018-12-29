package com.sunyu.prometheus.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.sunyu.prometheus.enums.ErrorCode;
import com.sunyu.prometheus.model.ProvinceGatewayCopy1;
import com.sunyu.prometheus.dao.ProvinceGatewayCopy1Dao;
import com.sunyu.prometheus.service.ProvinceGatewayCopy1Service;

/**
 * @author yu on 2018/12/29.
 */
@Service("provinceGatewayCopy1Service")
public class ProvinceGatewayCopy1ServiceImpl  implements ProvinceGatewayCopy1Service{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ProvinceGatewayCopy1Service.class);

	@Resource
	private ProvinceGatewayCopy1Dao provinceGatewayCopy1Dao;

	@Override
	public CommonResult queryById(int id) {
	    ProvinceGatewayCopy1 entity = provinceGatewayCopy1Dao.queryById(id);
	    if (null != entity) {
	        //成功返回数据
        	return CommonResult.ok(ErrorCode.SUCCESS).setResult(entity);
        } else {
        	logger.info("ProvinceGatewayCopy1Service未查询到数据，编号：{}", id);
        	return CommonResult.fail(ErrorCode.UNKNOWN_ERROR);
        }
	}

	@Override
    public PageSerializable queryPage(int offset, int limit) {
        PageHelper.offsetPage(offset, limit);
        List<ProvinceGatewayCopy1> list = provinceGatewayCopy1Dao.queryPage();
        return new PageSerializable(list);
    }
}