<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
<h2>欢迎使用本系统！</h2>
<a href="login.jsp">点我跳转jsp登陆页面</a>
<br />
<a href="angular/angularJSLogin.html">点我跳转angularJS登陆页面</a>
</body>
</html>
