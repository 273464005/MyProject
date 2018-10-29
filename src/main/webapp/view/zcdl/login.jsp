<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>登陆</title>
</head>
<body>
<div>
    <!--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                            <legend>用户登陆</legend>
                        </fieldset>-->
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">登录号</label>
            <div class="layui-input-inline">
                <input type="text" name="dlh" id="dlh" lay-verify="required" placeholder="请输入登录号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="mm" id="mm" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">验证码</label>
            <div class="layui-input-inline">
                <input type="text" name="yzm" id="yzm" lay-verify="required" maxlength="4" placeholder="请输入验证码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <img id="validationCode_img"  src="${basePath}zcdl/validationCode">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="loginFrom">登陆</button>
            </div>
        </div>
    </form>
</div>

</body>
<script type="text/javascript">
    layui.use(['element','form'], function(){
//	    var $ = layui.jquery
        var form = layui.form;
        form.on('submit(loginFrom)', function(data){
            $.ajax({
                method: "post",
                url: "${basePath}zcdl/dlxtjy",
                data:{
                    dlh:$("#dlh").val()
                    ,mm:SHA2($("#mm").val())
                    ,yzm:$("#yzm").val()
                },
                beforeSend: function () {
                    processIndex = layer.load();
                },
                success: function (data) {
                    layer.close(processIndex);
                    jsonMsg(data,function () {
                        location.href = "${basePath}zcdl/htglMainHome";
                    },function () {
                        $("input[name=mm]").val(null);
                        $("input[name=yzm]").val(null);
                        $("#validationCode_img").click();
                    })

                },
                error:function (e) {
                    alertMsg("发生未知异常，请联系管理员！" , 5);
                    $("input[name=mm]").val("");
                    layer.close(processIndex);
                }
            });
            return false;
        });

        $("#validationCode_img").on('click',function () {
            $("#validationCode_img").attr("src","${basePath}zcdl/validationCode?"+Math.random());
        })
    });
</script>
