<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>My WebSocket</title>
    <script src="statics/js/jquery-1.11.1.min.js"></script>
    <script src="statics/js/sockjs.min.js"></script>
</head>

<body>
Welcome<br/>
<input id="text" type="text"/>
<button onclick="send()">Send</button>
<button onclick="closeWebSocket()">Close</button>
<div id="message">
</div>
<script type="text/javascript">
    var websocket = null;

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://" + location.host + "/webSocketServer");
        //"ws://localhost:8080/websocket");
    } else {
        websocket = new SockJS("http://" + location.host + "/sockjs/webSocketServer");
        //alert('Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("error");
    };

    //连接成功建立的回调方法
    websocket.onopen = function (event) {
        setMessageInnerHTML("open");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }
    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("close");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    }
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }
    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }
    //发送消息
    function send() {
        if (websocket != null) {
            var message = document.getElementById('text').value;
            $.ajax({
                url: 'http://localhost:8080/sendMsg',
                type: 'post',
                dataType: 'json',
                data: {
                    username: 'sunyu',
                    msg: message
                },
                success: function (result) {
                    console.log("result");
                }
            })
        } else {
            alert("未与服务器连接");
        }
        //websocket.send(message);
    }
</script>
</body>
</html>
