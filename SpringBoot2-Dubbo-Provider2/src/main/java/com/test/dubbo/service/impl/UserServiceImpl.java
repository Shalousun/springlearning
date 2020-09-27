package com.test.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.test.dubbo.service.api.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu 2018/11/24.
 */
@Service(
        version = "${api.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class UserServiceImpl implements UserService {

    private static Map<Integer,String> userMap = new HashMap<>();
    static {
        userMap.put(1,"zhangsang");
        userMap.put(2,"lisi");
    }

    @Override
    public String getUser(int id) {
        return userMap.get(id);
    }
}
