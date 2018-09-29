<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <title>首页</title>
<body>
<h2>欢迎使用本系统！</h2>
<a href="login.jsp" target="_blank">点我跳转jsp登陆页面</a>
<br />
<a href="view/angular/angularJSLogin.html" target="_blank">点我跳转angularJS登陆页面</a>
<br />
</body>
</html>
