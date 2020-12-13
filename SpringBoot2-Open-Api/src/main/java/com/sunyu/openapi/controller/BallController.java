package com.sunyu.openapi.controller;

import com.alibaba.fastjson.JSON;
import com.power.common.model.CommonResult;
import com.sunyu.openapi.enums.OrderEnum;
import com.sunyu.openapi.model.Ball;
import com.sunyu.openapi.repository.BallRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 * 测试openapi
 *
 * @author yu 2020/11/28.
 */
@RestController
@RequestMapping("/api/ball")
public class BallController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BallController.class);
    @Autowired
    private BallRepository ballRepository;

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Ball findById(@PathVariable final Integer id) {
        return ballRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/")
    public Collection<Ball> findBalls() {
        return ballRepository.getBalls();
    }
//

    /**
     * @param id   编号
     * @param ball
     * @return
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ball updateBall(@PathVariable("id") final Integer id, Ball ball) {
        LOGGER.info("ball:{}",JSON.toJSONString(ball));
        return ball;
    }

    /**
     * 获取枚举参数2
     *
     * @param orderEnum 简单枚举
     * @return
     */
    @GetMapping(value = "/enum/{orderEnum}")
    public String testEnumPathVal0(@PathVariable OrderEnum orderEnum) {
        System.out.println(orderEnum.name());
        return "hello apiNote";
    }


    /**
     * app测试3
     *
     * @param name      姓名
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param age       年龄
     * @return
     */
    @PostMapping(value = "/test3/{name}")
    public Map<String, Ball> test3(@PathVariable String name, @RequestBody Map<String, Ball> map) {
        LOGGER.info("name:{} data:{}", name, JSON.toJSONString(map));
        return map;
    }


}
