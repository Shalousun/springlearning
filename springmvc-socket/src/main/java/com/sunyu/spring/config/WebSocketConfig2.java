package com.sunyu.spring.config;

import com.sunyu.spring.handler.MyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by yu on 2016/3/18.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig2 implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MyHandler(),"/mysocket");
    }
    @Bean
    public WebSocketHandler myHandler() {
        return new MyHandler();
    }
}
