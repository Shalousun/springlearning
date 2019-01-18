package com.socket.demo.controller;

import com.power.common.model.CommonResult;
import com.power.common.util.StringUtil;
import com.socket.demo.constants.GlobConstants;
import com.socket.demo.enums.ErrorCode;
import com.socket.demo.handler.SystemWebSocketHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu 2019/1/17.
 */
@RestController
public class IndexController extends BaseController {

    @Resource
    private SystemWebSocketHandler systemWebSocketHandler;
    private static Map<String,String> userMap = new HashMap<>();
    static {
        userMap.put("zhangsang","zhangsan");
        userMap.put("lisi","lisi");
    }

    @GetMapping("/")
    public CommonResult sayHello() {
        return CommonResult.ok().setResult("hello world");
    }


    @ResponseBody
    @RequestMapping("/login/{user}")
    public CommonResult login(@PathVariable String user,HttpServletRequest request){

        if(StringUtil.isEmpty(user)){
            return CommonResult.fail(ErrorCode.LOGIN_ERROR);
        }
        if(userMap.containsKey(user)){
            HttpSession session = request.getSession();
            session.setAttribute(GlobConstants.SESSION_USERNAME,user);
            session.setAttribute(GlobConstants.WEB_SOCKET_USERNAME,user);
           return CommonResult.ok();
        }else {
            return CommonResult.fail(ErrorCode.LOGIN_ERROR);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/sendMsg",method = RequestMethod.POST)
    public String auditing(HttpServletRequest request){
        String result = "消息";
        systemWebSocketHandler.sendMessageToUsers(new TextMessage(result));
        return result;
    }
}
