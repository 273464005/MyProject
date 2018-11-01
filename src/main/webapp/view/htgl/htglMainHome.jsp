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

    <!--  第一个不显示删除按钮 -->
    <style type="text/css">
      .first-tab i.layui-tab-close{
        display:none!important;
      }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"><a href="javascript:;" onclick="window.location.reload()"><span style="color:#ffffff">LYZ</span></a></div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <c:if test="${user.qx < GG_CZRY_QX_PTYH}">
                <li class="layui-nav-item">
                    <a href="javascript:;" onclick="tz('<%=basePath%>htgl/xtgn','gngl',this)">功能管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" onclick="tz('<%=basePath%>htgl/xtgn/editXt_gnb','tjgn',this)">添加功能</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;" onclick="tz('<%=basePath%>htgl/czry','yhgl',this)">用户管理</a></li>
            </c:if>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <c:if test="${showImg != '' && showImg != null}">
                        <img src="${showImg}" class="layui-nav-img">
                        <%--<img src="<%=basePath%>showImgs?path=${imgPath}" class="layui-nav-img">--%>
                    </c:if>
                    <c:if test="${showImg == null || showImg == ''}">
                        <img src="<%=path%>/img/zanwu.jpg" class="layui-nav-img">
                    </c:if>
                    ${user.mc}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" onclick="tz('<%=basePath%>htgl/czry/editGg_czry?czryid=${user.id}','jbzl',this)">基本资料</a></dd>
                    <dd><a href="javascript:;"  id="czmm">重置密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="javascript:;" id="tcdl">退出</a></li>
        </ul>
    </div>
  
  <div class="layui-side layui-bg-black">
      <div class="layui-side-scroll">
          <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
          <ul class="layui-nav layui-nav-tree"  lay-filter="test">
              <c:forEach items="${xtgnList}" var="xtgnList">
                  <li class="layui-nav-item" onclick="showLi(this)">
                  <a class="" href="javascript:;">${xtgnList.get('gnmc')}</a>
                  <c:if test="${xtgnList.get('childrenList') != null}">
                      <dl class="layui-nav-child">
                          <c:forEach items="${xtgnList.get('childrenList')}" var="childrenList">
                              <dd><a href="javascript:;" onclick="tz('<%=basePath%>${childrenList.get('ljdz')}','${childrenList.get('id')}',this)">${childrenList.get('gnmc')}</a></dd>
                          </c:forEach>
                      </dl>
                  </c:if>
                  </li>
              </c:forEach>
          </ul>
      </div>
  </div>
  
  <div class="layui-body">
      <!-- 选项卡操作区 -->
      <div hidden="hidden">
          <button class="layui-btn lyz-active" data-type="tabAdd" id="addTab">新增Tab</button>
          <button class="layui-btn lyz-active" data-type="tabDel" id="delTab">删除Tab</button>
          <input type="hidden" id="tabName"/>
          <input type="hidden" id="tabId"/>
          <input type="hidden" id="tabSrc">
          <button id="showIframe"></button>
      </div>
      <!-- 内容主体区域 -->
      <div class="layui-tab layui-tab-card" lay-filter="ztnrTab" lay-allowclose="true">
          <ul class="layui-tab-title">
              <li class="first-tab" lay-id="wdzm">我的桌面</li>
          </ul>
          <div class="layui-tab-content">
              <div class="layui-tab-item layui-show">
                  <!--  主体内容展示区域 -->
                  <div style="padding: 10px;">
                      <!-- onload="this.height=ztnr.document.body.scrollHeight" -->
                      <iframe name="ztnr" width="100%" height="97%"  frameborder="0" marginwidth="0" marginheight="0"
                            src="${basePath}view/htgl/welcome.html">
                      </iframe>
                  </div>
              </div>
          </div>
      </div>
  </div>
  
  <div class="layui-footer">
      <!-- 底部固定区域 -->
      <div id="showLow">
        ©
      </div>
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

            $.ajax({
                method:'post'
                ,url:'${basePath}zcdl/showFooter'
                ,success:function (returnValue) {
                    $("#showLow").append(returnValue.showFooter);
                    //加载完成后重新渲染页面
                    element.init();
                }
                ,error:function () {

                }
            });
            var layFilter = 'ztnrTab';
            var active = {
                tabAdd:function(){
                    var id = $("#tabId").val();
                    var tabName = $("#tabName").val();
                    var tabSrc = $("#tabSrc").val();

                    var layerLi = $("li[lay-id="+id+"]").length;
                    if(layerLi > 0){
                        //tab已经存在直接切换tab
                    }else{
                        //新增一个Tab项
                        element.tabAdd(layFilter, {
                            title: tabName
                            ,content: '<iframe name="ztnr" width="100%" height="97%"  frameborder="0" marginwidth="0" marginheight="0"src='+tabSrc+'></iframe>'
                            ,id: id
                        });
                    }
                    //切换tab
                    element.tabChange(layFilter,id);
                }
                ,tabDel:function(){
                    var id = $("#tabId").val();
                    element.tabDelete(layFilter, id);
                }
            };
            $(".lyz-active").on('click',function(){
                var othis = $(this), type = othis.data('type');
                active[type] ? active[type].call(this, othis) : '';
            });

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
                        layer.close(index1);
                        popupOk(returnValue, function () {
                            xgmm();
                        }, function () {

                        });

                    }
                    , error: function () {
                        layer.close(index);
                    }
                });


            });
        });

        function xgmm() {
            layer.prompt({title: '请输入修改后的密码', formType: 1}, function (mm, index2) {
                layer.close(index2);
                layer.prompt({title: '请再次输入密码', formType: 1}, function (qrmm, index3) {
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
                        msg("两次密码不匹配！",5)
                    }
                });
            });
        }

          //退出登陆
          $("#tcdl").click(function (){
              layer.confirm("确认退出登陆？", {
                  icon: 3
                  , title: '提示'
              }, function (index) {
                  location.href = "<%=basePath%>zcdl/exit";
                  layer.close(index);
              });
          });

    });
    //跳转页面
    function tz(tzdz,id,obj){
        $("#tabId").val(id);
        $("#tabName").val($(obj).html());
        $("#tabSrc").val(tzdz);
        $("#addTab").click();
//        ztnr = $("#ztnr");
//        ztnr.attr("height","97%");
//        ztnr.attr("src",tzdz);
    }

    /**
     * 左侧导航列表只展开当前点击的目录
     * @param obj 点击对象
     */
    function showLi(obj) {
        $(".layui-nav-item").each(function () {
            $(this).removeClass("layui-nav-itemed");
        });
        $(obj).addClass("layui-nav-itemed");
    }

</script>
</body>
</html>