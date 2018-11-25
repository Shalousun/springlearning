package com.test.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.test.dubbo.service.api.DemoService;

/**
 * @author yu 2018/11/24.
 */
@Service(
        version = "${api.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }
}
