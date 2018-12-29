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
import com.sunyu.prometheus.model.ProvinceGateway;
import com.sunyu.prometheus.dao.ProvinceGatewayDao;
import com.sunyu.prometheus.service.ProvinceGatewayService;

/**
 * @author yu on 2018/12/29.
 */
@Service("provinceGatewayService")
public class ProvinceGatewayServiceImpl  implements ProvinceGatewayService{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ProvinceGatewayService.class);

	@Resource
	private ProvinceGatewayDao provinceGatewayDao;

	@Override
	public CommonResult queryById(int id) {
	    ProvinceGateway entity = provinceGatewayDao.queryById(id);
	    if (null != entity) {
	        //成功返回数据
        	return CommonResult.ok(ErrorCode.SUCCESS).setResult(entity);
        } else {
        	logger.info("ProvinceGatewayService未查询到数据，编号：{}", id);
        	return CommonResult.fail(ErrorCode.UNKNOWN_ERROR);
        }
	}

	@Override
    public PageSerializable queryPage(int offset, int limit) {
        PageHelper.offsetPage(offset, limit);
        List<ProvinceGateway> list = provinceGatewayDao.queryPage();
        return new PageSerializable(list);
    }
}