package com.socket.demo.handler;

import com.power.common.util.IpUtil;
import com.socket.demo.constants.GlobConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by yu on 2017/5/23.
 */
public class SystemWebSocketHandler implements WebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(SystemWebSocketHandler.class);

    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList();



    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("connect to the websocket success......");
        sessions.add(session);
        String userName = (String) session.getAttributes().get(GlobConstants.WEB_SOCKET_USERNAME);
        if (userName != null) {
            StringBuilder message = new StringBuilder();
            message.append("Hi ").append(userName).append(",you are now connected to the server[")
                    .append(IpUtil.getServerIp()).append("]");
            session.sendMessage(new TextMessage(message.toString()));
        }
    }

    /**
     * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage message) throws Exception {
        int a = 0;
        //System.out.println("Message received: " + message.getPayload()+a);
        sendMessageToUsers(new TextMessage(IpUtil.getServerIp()+":"+message.getPayload().toString()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.debug("websocket connection closed......");
        sessions.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("websocket connection closed......");
        sessions.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        sessions.forEach(session -> {
            session.isOpen();
            if (session.isOpen()) {
                try {
                    session.sendMessage(message);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : sessions) {
            if (user.getAttributes().get(GlobConstants.WEB_SOCKET_USERNAME).equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
