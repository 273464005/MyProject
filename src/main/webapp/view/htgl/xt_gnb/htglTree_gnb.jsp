<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>功能树</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
    <style type="text/css">

    </style>
</head>
<body>

<div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>功能树</legend>
    </fieldset>

    <div id="tree">
        <div style="display: inline-block; width: 180px; height: 80%; padding: 10px; border: 1px solid #ddd; overflow: auto;" id="sygnTree">
            <ul id="sygn"></ul>
        </div>
        <div style="display: inline-block; width: 80%; height: 80%;  padding: 10px; border: 1px solid #ddd; overflow: auto;">
            <div style="padding: 15px;">
                <iframe id="editXt_gnb" name="if_content" width="100%" onload="this.height=if_content.document.body.scrollHeight" frameborder="0" marginwidth="0" marginheight="0"
                        src="">
                </iframe>
            </div>
        </div>
    </div>
    <div id="tables" hidden="hidden">

    </div>

</div>
<script src="<%=basePath%>layui/layui.all.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/myjs/layui-xtree.js"></script>
<script type="text/javascript">
    layui.use(['tree', 'layer','form'], function () {
        var layer = layui.layer
            , $ = layui.jquery;
//                ,form = layui.form;

        layui.tree({
            elem: '#sygn' //指定元素
            , click: function (item) { //点击节点回调
                if(item.id != 'ROOT' && item.id != ${zc} && item.id != ${jy}){
                    $("#editXt_gnb").attr("src","<%=basePath%>htgl/xtgn/editXt_gnb?gnid=" + item.id);
                }
            }
            , nodes: [ //节点
                ${treeDate}
            ]
        });
//            var xtree = new layuiXtree({
//                elem: 'sygnTree' //放xtree的容器（必填，只能为id，注意不带#号）
//                , form: form              //layui form对象 （必填）
//                , data: []             //数据，结构请参照下面 （必填）
//                , isopen: true            //初次加载时全部展开，默认true （选填）
//                , color: "#000"           //图标颜色 （选填）
//                , icon: {                 //图标样式 （选填）
//                    open: ""      //节点打开的图标（使用layui的图标，这里只填入图标代号即可）
//                    , close: ""   //节点关闭的图标
//                    , end: ""     //末尾节点的图标
//                }
//            });

    });
</script>
</body>
</html>
