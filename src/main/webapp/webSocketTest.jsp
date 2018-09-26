<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/1
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String webSocketPath = "//"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>webSocket测试页面</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/layui/css/layui.css"/>
    <script src="<%=basePath%>js/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>/layer/layer.js"></script>
    <script type="text/javascript" src="<%=basePath%>/layui/layui.all.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/myWebSocketJs.js"></script>
</head>
<body>
    <div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>测试即时通信</legend>
        </fieldset>
        <div>
            <div hidden="hidden">
                <input id="userId" value="${user.id}" />
                <input id="homeId" value="abc">
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-inline">
                    <input type="text" name="fsnr" id="fsnr" lay-verify="required" placeholder="需要发送的消息" autocomplete="off" class="layui-input" value="">
                </div>
            </div>
            <div>
                <button onclick="send('fsnr')" class="layui-btn">发送消息</button>
            </div>
            <br />
            <button onclick="closeWebSocket()" class="layui-btn">关闭WebSocket连接</button>
            <br />
            <div id="message"></div>
        </div>
    </div>
</body>


<script type="text/javascript">
    var homeId = $("#homeId").val();
    var userId = $("#userId").val();
    webSocketSendMsg('<%=webSocketPath%>',homeId,userId,'message');
</script>
</html>
