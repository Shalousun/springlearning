package com.socket.demo.model;

import lombok.Data;

/**
 * @author yu 2019/1/20.
 */
@Data
public class MessageInfo {


    /**
     * 源客户端id
     */
    private String sourceClientId;

    /**
     * 目标客户端id
     */
    private String targetClientId;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息内容
     */
    private String msgContent;

}
