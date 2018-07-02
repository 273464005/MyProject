<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <title>基本信息</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/layui/css/layui.css"/>
    <script src="<%=basePath%>js/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path%>/layer/layer.js"></script>
    <script type="text/javascript" src="<%=path%>/layui/layui.all.js"></script>
</head>
<body>
<div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>基本信息</legend>
    </fieldset>
    <form class="layui-form" action="" lay-filter="formCzry">
        <div hidden="">
            <input type="hidden" value="${gg_czry.id}" name="id">
            <input type="hidden" value="${gg_czry.zt}" name="zt">
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="mc" id="mc" lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">登录号</label>
            <div class="layui-input-inline">
                <input type="text" name="dlh" id="dlh" lay-verify="required" placeholder="请输入登录号" autocomplete="off" readonly="readonly" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">登陆系统使用</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">身份证号码</label>
            <div class="layui-input-inline">
                <input type="text" name="sfzh" lay-verify="identity" placeholder="请输入身份证号码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" lay-verify="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生年月</label>
            <div class="layui-input-inline">
                <input type="text" name="csnyr" id="date" lay-verify="date" placeholder="选择出生年月日" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input type="text" name="sjh" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input" value="">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权限</label>
            <div class="layui-input-inline">
                <select name="dyqx">
                    <c:forEach items="${qxList}" var="qxs">
                        <c:if test="${gg_czry.qx == qxs.key}">
                            <option value="${qxs.key}" id="${qxs.key}" selected>${qxs.value}</option>
                        </c:if>
                        <c:if test="${gg_czry.qx != qxs.key}">
                            <option value="${qxs.key}" id="${qxs.key}">${qxs.value}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="xb" value="0" title="男" checked="checked">
                <input type="radio" name="xb" value="1" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="<%=basePath%>js/information.js"></script>
<script type="text/javascript">
    layui.use(['form', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#date'
        });

        //初始赋值
        form.val('formCzry', {
            "mc": '${gg_czry.mc}' // "name": "value"
            , "dlh": '${gg_czry.dlh}'
            , "sfzh": '${gg_czry.sfzh}'
            , "email": '${gg_czry.email}'
            , "csnyr": '${csnyr}'
            , "sjh": '${gg_czry.sjh}'
            , "xb": '${gg_czry.xb}'
        });

        //监听提交
        form.on('submit(formDemo)', function(data){
            data.field.csnyr = data.field.csnyr.replace(/-/g,'');
            $.ajax({
                url: '<%=basePath%>htgl/czry/saveGg_czry'
                , method: 'post'
                , data: data.field
                , beforeSend: function () {
                    loadIndex = layer.load();
                }
                , success: function (returnValue) {
                    popupOk(returnValue, function () {

                    },function () {

                    });
                    layer.close(loadIndex);
                }
                , error:function () {
                    layer.alert('发生未知异常！', {
                        icon: 5
                        , shade: 0
                        , anim: 6
                    });
                    layer.close(loadIndex);
                }
            });
            return false;
        });

        form.render();
    });


</script>
</body>
</html>

