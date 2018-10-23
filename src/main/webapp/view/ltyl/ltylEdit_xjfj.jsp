<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>编辑房间信息</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
    <script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.js" ></script>
    <script src="<%=basePath%>/layui/layui.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=basePath%>layer/layer.js" ></script>
    <script type="text/javascript" src="<%=basePath%>js/information.js"></script>
    <script type="text/javascript" src="${basePath}js/regularcheck.js"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>编辑房间信息</legend>
</fieldset>
<div>
    <form class="layui-form" action="" method="post" id="formId" lay-filter="formLtfj">
        <div hidden="hidden">
            <input name="id" value="${ltfj.id}">
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">房间号</label>
            <div class="layui-input-inline">
                <input type="text" name="fjh" lay-verify="required" autocomplete="off" class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">房间名称</label>
            <div class="layui-input-inline">
                <input type="text" name="fjmc" lay-verify="required" placeholder="请输入房间名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">房间简介</label>
            <div class="layui-input-inline">
                <input type="text" name="fjms" placeholder="介绍一下自己的房间吧~~" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">房间密码</label>
            <div class="layui-input-inline">
                <input type="text" name="fjmm" id="fjmm" placeholder="请输入房间密码" maxlength="4" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">默认无密码，最长可输入四位</div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo" id="formDemo">保存</button>
                <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    layui.use(['form','upload'], function () {
        var form = layui.form
            , layer = layui.layer;

        //初始赋值
        form.val('formLtfj', {
            "fjh": '${ltfj.fjh}' // "name": "value"
            , "fjmc": '${ltfj.fjmc}'
            , "fjms": '${ltfj.fjms}'
            , "fjmm": '${ltfj.fjmm}'
        });

        //监听提交
        form.on('submit(formDemo)', function(data){
            if ( !($("#fjmm").val() != null || $("#fjmm").val())){
                if(!LoginPasswordCheck("#fjmm")){
                    return false;
                }
            }

            $.ajax({
                url: '<%=basePath%>ltyl/fjgl/saveLtfj'
                , method: 'post'
                , data: data.field
                , beforeSend: function () {
                    loadIndex = layer.load();
                }
                , success: function (returnValue) {
                    popupOk(returnValue, function () {
                        //关闭子页面刷新父页面
                       reloadParentWindow();
                    },function () {

                    });
                    layer.close(loadIndex);
                }
                , error:function () {
                    alertMsg('发生未知异常',5);
                    layer.close(loadIndex);
                }
            });
            return false;
        });

        $("#fjmm").on('change',function () {
            if ($(this).val() != null && $(this).val() != ''){
                LoginPasswordCheck(this);
            }
        });
        form.render();
    });

</script>
</body>
</html>
