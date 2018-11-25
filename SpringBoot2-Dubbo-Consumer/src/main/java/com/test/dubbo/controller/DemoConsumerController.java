package com.test.dubbo.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.test.dubbo.service.api.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yu 2018/11/24.
 */
@RestController
public class DemoConsumerController {

    @Reference(version = "${api.version}",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}")
    private DemoService demoService;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name) {
        return demoService.sayHello(name);
    }

}
