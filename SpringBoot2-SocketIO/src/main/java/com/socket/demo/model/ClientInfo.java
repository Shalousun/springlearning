package com.socket.demo.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author yu 2019/1/20.
 */

@Data
public class ClientInfo {

    /**
     * 客服端id
     */
    private String clientId;

    /**
     * 是否连接
     */
    private Integer connected;

    /**
     *
     */
    private Long mostSignBits;

    /**
     * 登录
     */
    private Long leastSignBits;
    /**
     * 最后连接时间
     */
    private Timestamp lastConnectedTime;
}
