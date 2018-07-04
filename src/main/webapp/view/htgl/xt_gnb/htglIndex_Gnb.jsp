<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>所用功能列表</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
    <style type="text/css">

    </style>
</head>
<body>

    <div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>所有功能</legend>
        </fieldset>
        <div>
            <button class="layui-btn layui-btn-sm layui-btn-primary">
                树形展示
            </button>
            <button class="layui-btn layui-btn-sm layui-btn-primary">
                列表展示
            </button>

        </div>

       <div>
           <iframe id="editXt_gnb" name="if_content" width="100%" onload="this.height=if_content.document.body.scrollHeight" frameborder="0" marginwidth="0" marginheight="0"
                   src="<%=basePath%>/view/htgl/xt_gnb/htglTree_gnb.jsp">
           </iframe>
       </div>

    </div>
    <script src="<%=basePath%>layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/myjs/layui-xtree.js"></script>
    <script type="text/javascript">
        layui.use(['tree', 'layer','form'], function () {
            var layer = layui.layer
                , $ = layui.jquery
                ,form = layui.form;


        });
    </script>
</body>
</html>
