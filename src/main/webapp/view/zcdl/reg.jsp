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
	<title>注册</title>
	<%--<link rel="stylesheet" type="text/css" href="<%=path%>/layui/css/layui.css"/>--%>
	<%--<script src="<%=basePath%>js/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>--%>
	<%--<script type="text/javascript" src="<%=path%>/layer/layer.js"></script>--%>
	<%--<script type="text/javascript" src="<%=path%>/layui/layui.all.js"></script>--%>
</head>
<body>
	<div>
		<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			<legend>用户注册</legend>
		</fieldset>--%>
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">姓名</label>
				<div class="layui-input-inline">
					<input type="text" name="mc" id="mc" lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">登录号</label>
				<div class="layui-input-inline">
					<input type="text" name="dlh" id="dlhReg" lay-verify="required" placeholder="请输入登录号" autocomplete="off" class="layui-input" onchange="return LoginNameCheck(this)">
				</div>
				<div class="layui-form-mid layui-word-aux">登陆系统使用</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">验证码</label>
				<div class="layui-input-inline">
					<input type="text" name="yzm" id="yzmReg" lay-verify="required" maxlength="4" placeholder="请输入验证码" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					<img id="validationCode_imgReg"  src="zcdl/validationCode">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-inline">
					<input type="password" name="mm" id="mmReg" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" onchange="return LoginPasswordCheck(this)">
				</div>
				<div class="layui-form-mid layui-word-aux">密码长度4-18位</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">确认密码</label>
				<div class="layui-input-inline">
					<input type="password" name="qrmm" id="qrmm" lay-verify="required" placeholder="请再次输入密码" autocomplete="off" class="layui-input" value="" onchange="return mmjy()">
				</div>
			</div>
			<%--<div class="layui-form-item">
				<label class="layui-form-label">身份证号码</label>
				<div class="layui-input-inline">
					<input type="text" name="sfzh" lay-verify="identity" placeholder="请输入身份证号码" autocomplete="off" class="layui-input" value="">
				</div>
			</div>--%>
			<%--<div class="layui-form-item">
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-inline">
					<input type="text" name="email" lay-verify="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input" value="">
				</div>
			</div>--%>
			<%--<div class="layui-form-item">
				<label class="layui-form-label">出生年月</label>
				<div class="layui-input-inline">
					<input type="text" name="csnyr" id="date" lay-verify="date" placeholder="选择出生年月日" autocomplete="off" class="layui-input">
				</div>
			</div>--%>
			<div class="layui-form-item">
				<label class="layui-form-label">手机号</label>
				<div class="layui-input-inline">
					<input type="text" name="sjh" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input" value="">
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
					<button class="layui-btn" lay-submit lay-filter="formDemoReg">保存</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
		<%--<div>
			<div align="right">
				<button class="layui-btn layui-btn-primary layui-btn-radius" onclick="yyzhdwdl()">已有账号？点我登陆</button>
			</div>
		</div>--%>
	</div>

<%--<script type="text/javascript" src="../../js/information.js"></script>--%>
<%--<script type="text/javascript" src="../../js/regularcheck.js"></script>--%>
<script type="text/javascript">
    layui.use(['form', 'laydate'], function () {
        var form2 = layui.form
            , layer = layui.layer;
//            , laydate = layui.laydate;
        //日期
//        laydate.render({
//            elem: '#date'
//        });
        //监听提交
        form2.on('submit(formDemoReg)', function(data){
            if (!LoginNameCheck("#dlhReg") || !LoginPasswordCheck("#mmReg") || !mmjy()){
                return false;
			} else {
//                data.field.csnyr = data.field.csnyr.replace(/-/g,'');
                $.ajax({
                    url: '${basePath}zcdl/zcczry'
                    , method: 'post'
                    , data: data.field
                    , beforeSend: function () {
                        loadIndex = layer.load();
                    }
                    , success: function (returnValue) {
                        layer.close(loadIndex);
                        popupOk(returnValue, function () {
//                            layerConfirm("是否直接登陆登录系统？",function () {
                                $.ajax({
                                    method: "post",
                                    url: "${basePath}zcdl/dlxtjy",
                                    data:{
                                        dlh:$("#dlhReg").val()
                                        ,mm:SHA2($("#mmReg").val())
                                        ,yzm:$("#yzmReg").val()
                                    },
                                    success: function (data) {
                                        jsonMsg(data,function () {
                                            location.href = "${basePath}zcdl/htglMainHome";
                                        },function () {
                                            reloadWindow();
                                        })

                                    },
                                    error:function (e) {
                                        alertMsg("发生未知异常，请联系管理员！" , 5);
                                    }
//                                });
                            });
//                            reloadWindow();
                        },function () {

                        });
                    }
                    , error:function () {
                        layer.close(loadIndex);
                        alertMsg("发生未知异常", 5);
                    }
                });
			}
            return false;
        });

        $('#mc').bind('input propertychange', function() {
            getHypy();
        });

        $("#validationCode_imgReg").on('click',function () {
            $("#validationCode_imgReg").attr("src","${basePath}zcdl/validationCode?"+Math.random());
        });

        form2.render();
    });


    //两次密码校验
    function mmjy() {
		mm = $("#mmReg").val();
		qrmm = $("#qrmm").val();
		if(mm == qrmm){
		}else{
		    msg("两次密码不匹配！",5);
            return false;
		}
		return true;
    }

    //获取姓名全拼
    function getHypy() {
        $.ajax({
            method: 'post'
			, url:'<%=basePath%>zcdl/getHypy'
			, data:{
                mc : $("#mc").val()
			}
			, success:function (returnValue) {
                $("#dlhReg").val(returnValue.dlh);
            }
        });
    }

</script>
</body>
</html>

