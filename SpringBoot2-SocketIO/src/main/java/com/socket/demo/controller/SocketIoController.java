package com.socket.demo.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.power.common.model.CommonResult;
import com.socket.demo.handler.SimpleMessageEventHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yu 2019/1/20.
 */
@RestController
public class SocketIoController {

    /**
     * 当前不可测试，为未完成
     * @param clientId
     * @return
     */
    @GetMapping("sendMsg")
    public CommonResult sendMsg(@PathVariable String clientId){
        SocketIOClient client = SimpleMessageEventHandler.webSocketMap.get(clientId);
        client.sendEvent("messageevent","来自服务器测试");
        return CommonResult.ok();

    }
}
