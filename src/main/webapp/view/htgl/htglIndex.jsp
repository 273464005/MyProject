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

        <div style="display: inline-block; width: 180px; height: 80%; padding: 10px; border: 1px solid #ddd; overflow: auto;">
            <ul id="sygn"></ul>
        </div>
        <div style="display: inline-block; width: 80%; height: 80%;  padding: 10px; border: 1px solid #ddd; overflow: auto;">
            <!-- 内容主体区域 -->
            <div style="padding: 15px;">
                <iframe id="editXt_gnb" name="if_content" width="100%" onload="this.height=if_content.document.body.scrollHeight" frameborder="0" marginwidth="0" marginheight="0"
                        src="">

                </iframe>
            </div>
        </div>
    </div>
    <script src="<%=basePath%>layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js"></script>
    <script type="text/javascript">
        layui.use(['tree', 'layer'], function () {
            var layer = layui.layer
                , $ = layui.jquery;

            layui.tree({
                elem: '#sygn' //指定元素
                , click: function (item) { //点击节点回调
                    if(item.id != 'sygn'){
                        $("#editXt_gnb").attr("src","<%=basePath%>htgl/editXt_gnb?gnid=" + item.id);
                    }
                }
                , nodes: [ //节点
                    ${treeDate}
                ]
            });

        });
    </script>
</body>
</html>
