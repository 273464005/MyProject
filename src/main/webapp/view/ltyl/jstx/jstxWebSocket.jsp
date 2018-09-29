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
    <title>即时通信</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
    <script type="text/javascript" src="<%=basePath%>layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/information.js"></script>
    <script type="text/javascript" src="<%=basePath%>layer/layer.js" ></script>
    <script type="text/javascript" src="<%=basePath%>js/jstxInstantMessaging.js"></script>
</head>
<body>
<div>
    <div>
        <fieldset class="layui-elem-field">
            <legend>即时通信</legend>
            <div class="layui-field-box" style="height: 69%;width: 95%;display: inline-block;overflow:auto">
                <div hidden="hidden">
                    <input id="userId" value="${user.id}" />
                    <input id="homeId" value="${ltfj.id}">
                </div>
                <div id="message" style="display: inline-block;"></div>
            </div>
        </fieldset>
    </div>
    <div style="position: fixed;bottom: 0;width: 100%">
        <div>
            <textarea name="fsnr" id="fsnr" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
        <div style="margin-top: 5px;float: right;">
            <c:if test="${fjry.zt == GG_FJRYB_ZT_ZC}">
                <button onclick="send('fsnr','${ltfj.id}')" class="layui-btn">发送消息</button>
            </c:if>
            <c:if test="${fjry.zt != GG_FJRYB_ZT_ZC}">
                <button class="layui-btn layui-btn-disabled">您已被禁言</button>
            </c:if>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var homeId = $("#homeId").val();
    var userId = $("#userId").val();
    webSocketSendMsg('<%=webSocketPath%>',homeId,userId,'message','<%=basePath%>');
</script>
</html>
