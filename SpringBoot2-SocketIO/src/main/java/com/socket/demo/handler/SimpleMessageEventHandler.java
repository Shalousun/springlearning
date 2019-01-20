package com.socket.demo.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.power.common.util.IpUtil;
import com.socket.demo.model.MessageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author yu 2019/1/20.
 */
@Slf4j
@Component
public class SimpleMessageEventHandler {


    static ArrayList<UUID> listClient = new ArrayList<>();
    static final int limitSeconds = 60;


    @Autowired
    public SocketIOServer socketIoServer;

    @OnConnect
    public void onConnect(SocketIOClient client) {
        listClient.add(client.getSessionId());
        log.info("客户端:{} 已连接", client.getSessionId());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        log.info("客户端:{} 断开连接", client.getSessionId());
    }


    /**
     * 自定义添加监听
     *
     * @param client
     * @param request
     * @param data
     */
    @OnEvent(value = "messageevent")
    public void onEvent(SocketIOClient client, AckRequest request, MessageInfo data) {
        log.info("发来消息：{}", data.getMsgContent());
        StringBuilder msg = new StringBuilder();
        msg.append("I'm server ").append(IpUtil.getServerIp()).append(".");
        socketIoServer.getClient(client.getSessionId()).sendEvent("messageevent", msg.toString());
    }

}
