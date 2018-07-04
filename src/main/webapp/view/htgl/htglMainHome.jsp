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
  <title>信息管理平台</title>
  <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
  <link rel="stylesheet" href="<%=basePath%>layer/mobile/need/layer.css">

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo"><a href="javascript:;" onclick="window.location.reload()"><span style="color:#ffffff">LOGO位置</span></a></div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <c:if test="${user.qx < GG_CZRY_QX_PTYH}">
      <ul class="layui-nav layui-layout-left">
        <li class="layui-nav-item">
          <a href="javascript:;" onclick="tz('<%=basePath%>htgl/xtgn/xtgnIndex')">功能管理</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;" onclick="tz('<%=basePath%>htgl/xtgn/editXt_gnb')">添加功能</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:;" onclick="tz('<%=basePath%>htgl/czry')">用户管理</a></li>
      </ul>
    </c:if>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <c:if test="${showImg != '' && showImg != null}">
            <img src="${showImg}" class="layui-nav-img">
          </c:if>
          <c:if test="${showImg == null || showImg == ''}">
            <img src="<%=path%>/img/zanwu.jpg" class="layui-nav-img">
          </c:if>
          ${user.mc}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="javascript:;" onclick="tz('<%=basePath%>htgl/czry/editGg_czry?czryid=${user.id}')">基本资料</a></dd>
          <dd><a href="javascript:;"  id="czmm">重置密码</a></dd>
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
                <dd><a href="javascript:;" onclick="tz('<%=basePath%>${childrenList.get('ljdz')}')">${childrenList.get('gnmc')}</a></dd>
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
<script type="text/javascript" src="<%=basePath%>layer/layer.js" ></script>
<script type="text/javascript" src="<%=basePath%>js/information.js"></script>
<script type="text/javascript" src="<%=basePath%>js/sha1.js"></script>
<script type="text/javascript">
//JavaScript代码区域
    layui.use(['element','layer'], function(){
      var element = layui.element
          ,layer = layui.layer;

      $("#czmm").click(function () {
          layer.prompt({title: '请输入密码', formType: 1}, function (yzmm, index1) {
              $.ajax({
                  method: 'post'
                  , url: '<%=basePath%>htgl/czry/jymm'
                  , data: {
                      id:'${user.id}'
                      , mm: SHA2(yzmm)
                  }
                  , success: function (returnValue) {
                      popupOk(returnValue, function () {
                          layer.close(index1);
                          layer.prompt({title: '请输入修改后的密码', formType: 3}, function (mm, index2) {
                              layer.close(index2);
                              layer.prompt({title: '请再次输入密码', formType: 3}, function (qrmm, index3) {
                                  if (mm === qrmm) {
                                      layer.close(index3);
                                      $.ajax({
                                          method: 'post'
                                          , url: '<%=basePath%>htgl/czry/updateMm'
                                          , data: {
                                              id:'${user.id}'
                                              , mm: SHA2(mm)
                                          }
                                          , success: function (returnValue) {
                                              popupOk(returnValue, function () {
                                                  location.href = "<%=basePath%>zcdl/exit";
                                              }, function () {

                                              });
                                          }
                                          , error: function () {

                                          }
                                      })
                                  } else {
                                      layer.msg("两次密码不匹配！", {
                                          icon: 5
                                          , shade: 0
                                          , anim: 6
                                          , time: 3000
                                      });
                                  }
                              });
                          });
                      }, function () {

                      });

                  }
                  , error: function () {
                      layer.close(index);
                  }
              });


          });
      })


    });
    //跳转页面
    function tz(tzdz){
        ztnr = $("#ztnr");
        ztnr.attr("height","97%");
        ztnr.attr("src",tzdz);
    }

    //退出登陆
    function tcdl() {
        if(confirm("确认退出登陆？")){
            location.href = "<%=basePath%>zcdl/exit";
        }
    }

</script>
</body>
</html>