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
  <title>后台管理系统</title>
  <link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
  <%--<link rel="stylesheet" href="/layer/mobile/need/layer.css">--%>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">LOGO位置</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item">
        <a href="javascript:;">功能管理</a>
        <dl class="layui-nav-child">
          <dd><a href="javascript:;" onclick="">添加功能</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">用户管理</a></li>
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
                <dd><a href="javascript:;" onclick="tz('${childrenList.get('ljdz')}')">${childrenList.get('gnmc')}</a></dd>
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
    <div style="padding: 15px;">
    	<iframe id="if_content" name="if_content" width="100%" onload="this.height=if_content.document.body.scrollHeight" frameborder="0" src="/msg.jsp"></iframe>
    </div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © lyz_0220@163.com - 作者邮箱
  </div>
</div>
<script type="text/javascript" src="<%=path%>/js/jquery-3.2.1.js" ></script>
<script src="<%=path%>/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/layer/layer.js" ></script>
<script>
//JavaScript代码区域
    layui.use('element', function(){
      var element = layui.element;

    });
    //跳转页面
    function tz(tzdz){
        $("#ztnr").attr("src",tzdz);
    }

    //退出登陆
    function tcdl() {
        if(confirm("确认退出登陆？")){
            <%--location.href = "<%=path%>/exit.action";--%>
            location.href = "exit.action";
        }
//        layer.msg("退出登录！");
        /*layer.open({
            type: 1,
            title: false, //不显示标题栏
            closeBtn: false,
            area: '300px;',
            shade: 0.8,
            id: 'exitHtgl', //设定一个id，防止重复弹出
            resize: false,
            btn: ['确认', '取消'],
            btnAlign: 'c',
            moveType: 1, //拖拽模式，0或者1
            content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">内容<br>内容</div>',
            success: function(layero){
                var btn = layero.find('.layui-layer-btn');
                btn.find('.layui-layer-btn0').attr({
                    href: 'http://www.layui.com/'
                    ,target: '_blank'
                });
            }
        });*/
    }
</script>
</body>
</html>