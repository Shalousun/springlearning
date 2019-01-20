package com.socket.demo.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.power.common.util.DateTimeUtil;
import com.socket.demo.dao.ClientInfoDao;
import com.socket.demo.model.ClientInfo;
import com.socket.demo.model.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author yu 2019/1/20.
 */
public class MessageEventHandler {

    @Autowired
    private SocketIOServer server;

    @Autowired
    private ClientInfoDao clientInfoDao;

    @Autowired
    public MessageEventHandler(SocketIOServer server) {
        this.server = server;
    }

    //添加connect事件，当客户端发起连接时调用，本文中将clientid与sessionid存入数据库
    //方便后面发送消息时查找到对应的目标client,
    @OnConnect
    public void onConnect(SocketIOClient client) {
        String clientId = client.getHandshakeData().getSingleUrlParam("clientid");
        ClientInfo clientInfo = clientInfoDao.findClientByclientId(clientId);
        if (clientInfo != null) {

            clientInfo.setConnected(1);
            clientInfo.setMostSignBits(client.getSessionId().getMostSignificantBits());
            clientInfo.setLeastSignBits(client.getSessionId().getLeastSignificantBits());
            clientInfo.setLastConnectedTime(DateTimeUtil.nowTimeStamp());
            clientInfoDao.save(clientInfo);
        }
    }

    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String clientId = client.getHandshakeData().getSingleUrlParam("clientid");
        ClientInfo clientInfo = clientInfoDao.findClientByclientId(clientId);
        if (clientInfo != null) {
            clientInfo.setConnected(0);
            clientInfo.setMostSignBits(null);
            clientInfo.setLeastSignBits(null);
            clientInfoDao.save(clientInfo);
        }
    }

    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
    @OnEvent(value = "messageevent")
    public void onEvent(SocketIOClient client, AckRequest request, MessageInfo data) {
        String targetClientId = data.getTargetClientId();
        ClientInfo clientInfo = clientInfoDao.findClientByclientId(targetClientId);
        if (clientInfo != null && clientInfo.getConnected() != 0) {
            UUID uuid = new UUID(clientInfo.getMostSignBits(), clientInfo.getLeastSignBits());
            System.out.println(uuid.toString());
            MessageInfo sendData = new MessageInfo();
            sendData.setSourceClientId(data.getSourceClientId());
            sendData.setTargetClientId(data.getTargetClientId());
            sendData.setMsgType("chat");
            sendData.setMsgContent(data.getMsgContent());
            client.sendEvent("messageevent", sendData);
            server.getClient(uuid).sendEvent("messageevent", sendData);
        }

    }

}
