package com.sunyu.concurrent.redis.listener;

import java.io.Serializable;

/**
 * 消费者
 * Created by yu on 2016/11/15.
 */
public class ListenMessage {
    /**
     * 消息处理这
     * @param message
     */
    public void handleMessage(Serializable message){
        System.out.println(message);
    }
}
