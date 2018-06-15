<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

    <head>
        <meta charset="utf-8">
        <title>后台管理系统</title>
        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="<%=path%>/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=path%>/assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="<%=path%>/assets/css/form-elements.css">
        <link rel="stylesheet" href="<%=path%>/assets/css/style.css">
        <link rel="shortcut icon" href="<%=path%>/assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=path%>/assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=path%>/assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=path%>/assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="<%=path%>/assets/ico/apple-touch-icon-57-precomposed.png">
        <!-- Javascript -->
        <%--<script src="<%=path%>/assets/js/jquery-1.11.1.min.js"></script>--%>
        <script src="<%=path%>/js/jquery-3.2.1.min.js"></script>
        <script src="<%=path%>/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="<%=path%>/assets/js/jquery.backstretch.min.js"></script>
        <script src="<%=path%>/assets/js/scripts.js"></script>
        <!-- layer JS -->
        <script src="<%=path%>/layer/layer.js"></script>

    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1>欢迎使用后台管理系统</h1>
                            <div class="description">
                            	<p>
	                            	系统简介，或者其他的什么东西
                            	</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>登陆你的后台系统</h3>
                            		<p>请输入您的登陆账号密码：</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="" method="post" class="login-form" id="dlFrom">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="dlh">登录号</label>
			                        	<input type="text" name="dlh" placeholder="请输入登陆号" class="form-username form-control" id="dlh">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="mm">密码</label>
			                        	<input type="password" name="mm" placeholder="请输入密码" class="form-password form-control" id="mm">
			                        </div>
			                        <%--<button type="submit" class="btn">登陆</button>--%>
                                    <button class="btn" onclick="return dlxt('dlFrom')">登陆</button>
			                    </form>
                                <div hidden="hidden">
                                    <input type="text" id="msg" value="${msg}">
                                </div>
		                    </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 social-login">
                        	<h3><span name="msg"></span></h3>
                        	<div class="social-login-buttons">
	                        	<a class="btn btn-link-1 btn-link-1-facebook" href="####">
                                    <i class="layui-icon layui-icon-password"></i>找回密码
	                        	</a>
	                        	<a class="btn btn-link-1 btn-link-1-twitter" href="####" onclick="zczh()">
                                    <i class="layui-icon layui-icon-user"></i>注册账号
	                        	</a>
	                        	<a class="btn btn-link-1 btn-link-1-google-plus" href="####">
                                    <i class="layui-icon layui-icon-chat"></i>关于系统
	                        	</a>
                        	</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <script type="text/javascript">
//            var msg = $("#msg").val();
//            if(msg != ""){
//                $("span[name=msg]").css("color", "#FC4343");
//                $("span[name=msg]").text(msg);
//            }
            function zczh(){
                layer.open({
                    type: 2,
                    title: '用户注册',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area : ['505px' , '691px'],
                    content: '/reg.jsp'
                });
            }

            function dlxt(fromid){
                $.ajax({
                    method: "post",
                    url: "dlxt",
                    data: $("#"+fromid).serialize(),
                    success: function (data) {
                        if(data.state != 1){
                            $("span[name=msg]").css("color", "#FC4343");
                            $("span[name=msg]").text(data.text);
                            $("input[name=mm]").val("");
                        } else {
                            window.location = "htgl/htglMainHome.jsp"
                        }
                    }
                });
                return false;
            }
        </script>
    </body>
    
</html>