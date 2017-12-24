package com.sunyu.spring.controller;

import com.sunyu.spring.websocket.SystemWebSocketHandler;
import org.springframework.context.annotation.Bean;

/**
 * Created by yu on 2017/5/23.
 */
public abstract class BaseController {

    @Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }
}
