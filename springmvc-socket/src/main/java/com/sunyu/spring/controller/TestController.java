package com.sunyu.spring.controller;

import com.sunyu.spring.constants.GlobConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yu on 2017/5/23.
 */

@Controller
public class TestController extends BaseController {

    @ResponseBody
    @RequestMapping("/login")
    public String login(HttpServletRequest request){

        Principal principal = request.getUserPrincipal();
        String userName = principal.getName();
        String result = "登录成功";
        Map<String,String> userMap = new HashMap<>();
        userMap.put("sunyu","123456");
        userMap.put("zhangsan","123456");
        String name = "sunyu";
        if("".equals(name)||null == name){
            result = "登录失败";
            return result;
        }
        if(userMap.containsKey(name)){
            HttpSession session = request.getSession();
            session.setAttribute(GlobConstants.SESSION_USERNAME,name);
            session.setAttribute(GlobConstants.WEBSOCKET_USERNAME,name);
            result = "登录成功";
        }else {
            result = "登录失败";
        }

        return result;
    }
    @ResponseBody
    @RequestMapping("/login2")
    public String login2(HttpServletRequest request){
        String result = "登录成功";
        Map<String,String> userMap = new HashMap<>();
        userMap.put("sunyu","123456");
        userMap.put("zhangsan","123456");
        String name = "zhangsan";
        if("".equals(name)||null == name){
            result = "登录失败";
            return result;
        }
        if(userMap.containsKey(name)){
            HttpSession session = request.getSession();
            session.setAttribute(GlobConstants.SESSION_USERNAME,name);
            session.setAttribute(GlobConstants.WEBSOCKET_USERNAME,name);
            result = "登录成功";
        }else {
            result = "登录失败";
        }

        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/sendMsg",method = RequestMethod.POST)
    public String auditing(HttpServletRequest request){
        String username = request.getParameter("username");
        String message = request.getParameter("msg");
        String result = "消息";
        systemWebSocketHandler().sendMessageToUser(username, new TextMessage(message));
       // systemWebSocketHandler().sendMessageToUsers(new TextMessage(message));
        return result;
    }
}
