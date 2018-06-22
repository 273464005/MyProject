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
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>后台管理</title>
  <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
  <link rel="stylesheet" href="<%=basePath%>layer/mobile/need/layer.css">

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo"><a href="####" onclick="window.location.reload()"><span style="color:#ffffff">LOGO位置</span></a></div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item">
        <a href="javascript:;" onclick="tz('<%=basePath%>htgl/xtgnIndex','add')">功能管理</a>
        <dl class="layui-nav-child">
          <dd><a href="javascript:;" onclick="tz('<%=basePath%>htgl/editXt_gnb','add')">添加功能</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="####">用户管理</a></li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="<%=path%>/img/zanwu.jpg" class="layui-nav-img">
          ${user.mc}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="javascript:;" onclick="tcdl()">退出</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <c:forEach items="${xtgnList}" var="xtgnList">
          <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">${xtgnList.get('gnmc')}</a>
          <c:if test="${xtgnList.get('childrenList') != null}">
            <dl class="layui-nav-child">
              <c:forEach items="${xtgnList.get('childrenList')}" var="childrenList">
                <dd><a href="javascript:;" onclick="tz('${childrenList.get('ljdz')}','url')">${childrenList.get('gnmc')}</a></dd>
              </c:forEach>
            </dl>
          </c:if>
            </li>
          </c:forEach>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 10px;">
    	<iframe id="ztnr" name="ztnr" width="100%" onload="this.height=ztnr.document.body.scrollHeight" frameborder="0" marginwidth="0" marginheight="0"
                src="<%=basePath%>view/htgl/welcome.html">

        </iframe>
    </div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © lyz_0220@163.com - 作者邮箱
  </div>
</div>
<script type="text/javascript" src="<%=path%>/js/jquery-3.2.1.js" ></script>
<script src="<%=path%>/layui/layui.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/layer/layer.js" ></script>
<script type="text/javascript">
//JavaScript代码区域
    layui.use('element', function(){
      var element = layui.element;

    });
    //跳转页面
    function tz(tzdz,type){
        ztnr = $("#ztnr");
        ztnr.attr("height","97%");
        if(type==="url"){
            url = "<%=basePath%>view/"+tzdz;
        }else{
            url = tzdz;
        }
        ztnr.attr("src",url);
    }

    //退出登陆
    function tcdl() {
        if(confirm("确认退出登陆？")){
            location.href = "exit";
        }
    }
</script>
</body>
</html>