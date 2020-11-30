package com.sunyu.openapi.controller;

import com.alibaba.fastjson.JSON;
import com.power.common.model.CommonResult;
import com.sunyu.openapi.model.Ball;
import com.sunyu.openapi.repository.BallRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author yu 2020/11/28.
 */
@RestController
@RequestMapping("/api/ball")
public class BallController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BallController.class);
//    @Autowired
//    private BallRepository ballRepository;
//
//    @GetMapping("/{id}")
//    public Ball findById(@PathVariable long id) {
//        return ballRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException());
//    }
//
//    @GetMapping("/")
//    public Collection<Ball> findBalls() {
//        return ballRepository.getBalls();
//    }
//
    @Deprecated
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ball updateBall(@PathVariable("id") final String id, @RequestBody final Ball ball) {
        return ball;
    }

    /**
     * app测试3
     * @param name 姓名
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param age 年龄
     * @return
     */
    @PostMapping(value = "/test3/{name}" )
    public Map<String,Ball> test3(@PathVariable String name,@RequestBody Map<String,Ball> map){
        LOGGER.info("name:{} data:{}",name,JSON.toJSONString(map));
        return map;
    }
}
