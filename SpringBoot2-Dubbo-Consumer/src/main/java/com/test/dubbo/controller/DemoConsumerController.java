package com.test.dubbo.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.test.dubbo.service.api.DemoService;
import com.test.dubbo.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yu 2018/11/24.
 */
@Slf4j
@RestController
public class DemoConsumerController {

    @Reference(version = "${api.version}",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}")
    private DemoService demoService;

    @Reference(version = "${api.version}",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}")
    private UserService userService;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name) {
        return demoService.sayHello(name);
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }


}
