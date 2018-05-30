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
	<link rel="stylesheet" type="text/css" href="<%=path%>/regcss/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/regcss/css/font-span.css"/>
	<script src="<%=path%>/js/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/formcheck.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="<%=path%>/layer/layer.js"></script>
</head>
<body>

<div class="panel panel-primary magin_center">
	<div class="panel-heading">用户注册</div>
	<div class="panel-body">
		<form method="post" action="htgl.zcczry.action">
			<table class="table table-hover">
				<tr>
					<td>姓名<span style="color: red">*</span></td>
					<td colspan="2"><input class="form-control" type="text"
										   name="mc"  value="" placeholder="请输入姓名" onchange="qc(this)">
						<span id="uname"></span>
					</td>
				</tr>
				<tr>
					<td>登录号<span style="color: red">*</span></td>
					<td colspan="2"><input class="form-control" type="text" name="dlh"
										   value="" placeholder="请输入登录号" onchange="qc(this)" />
						<span id="userName"></span>
					</td>
				</tr>
				<tr>
					<td>密码<span style="color: red">*</span></td>
					<td colspan="2"><input class="form-control" type="password"
										   name="mm" value="" placeholder="密码长度3~16位" onchange="qc(this)"/>
						<span id="pwd"></span>
					</td>
				</tr>
				<tr>
					<td>确认密码<span style="color: red">*</span></td>
					<td colspan="2"><input class="form-control" type="password"
										   name="repmm" value="" placeholder="请再次输入密码" onchange="qc(this)"/>
						<span id="reppwd"></span>
					</td>
				</tr>
				<tr>
					<td>身份证号<span style="color: red">*</span></td>
					<td colspan="2"><input class="form-control" type="text"
										   name="sfzh" value="" placeholder="请输入身份证号" onchange="qc(this)"/>
						<span id="idcard"></span>
					</td>
				</tr>
				<tr>
					<td>邮箱<span style="color: red">*</span></td>
					<td colspan="2"><input class="form-control" type="email"
										   name="email" value="" placeholder="请输入邮箱" onchange="qc(this)"/>
						<span id="email"></span>
					</td>
				</tr>
				<tr>
					<td>手机号<span style="color: red">*</span></td>
					<td colspan="2"><input class="form-control" type="text"
										   name="sjh" value="" placeholder="请输入手机号" onchange="qc(this)"/>
						<span id="phone"></span>
					</td>
				</tr>
				<tr>
					<td>验证码<span style="color: red">*</span></td>
					<td><input class="form-control" type="text" name="code"
							   value="" placeholder="请输入验证码" />
						<span id="code"></span>
					</td>
					<td><button type="button" name="getcode"
								class="btn btn-default">免费获取验证码<span id="getcode"></span></button>
					</td>
				</tr>
				<tr>
					<td>性别</td>
					<td colspan="2"><label> <input type="radio"
												   name="xb" checked="checked" value="0" />男
					</label> <span style="display: inline-block; width: 50px;"></span> <label>
						<input type="radio" name="xb" value="1" />女
					</label></td>
				</tr>
				<tr align="center">
					<td colspan="3">
						<button type="submit" name="submit" class="btn btn-primary" onclick="return checkForm(this.form)">注 册</button>
						<button type="reset" class="btn btn-warning">重 置</button>

					</td>
				</tr>
				<tr>
					<td colspan="3">
						<span id="submit"></span>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div align="right">
		<button type="button" title="login" class="btn btn-link" onclick="yyzh()">已有账号？点我登陆</button>
	</div>
	<div id="msg" hidden="hidden">
		<input type="text" value="${msg}" name="msg"/>
	</div>
</div>
<script type="text/javascript">

	//----------------------消息提示开始-------------------------
    var msg = $("input[name=msg]").val();
		if(msg != "") {
            layer.msg(msg);
//            location.href = "login.jsp";
        }
	//----------------------消息提示结束-------------------------

	function yyzh() {
//        location.href = "login.jsp";

    }

	//提交校验
    function checkForm(form){
        var values = $(form).serializeArray();
        var i;
        for(i = 0;i < values.length;++i){
            var inputName = values[i].name;
            var inp = $("input[name=" + inputName + "]");
            //空值校验
            if(values[i].value == ""){
                if(inp.next().text()==""){
                    inp.next().css("color","#FC4343");
                    inp.next().append("&#xe80d; 该值不能为空！");
				}
                inp.focus();
                return false;
            }

            //两次密码值校验
			if(inputName == "repmm"){
                return repmm(inp);
			}

        }
        return true;
    }

    function qc(obj) {
		$(obj).next().text("");
    }

    //再次输入密码校验
    function repmm(obj){
        var remm = obj.val();
        var mm = $("input[name=mm]").val();
        if(remm != mm){
            if(obj.next().text()==""){
                obj.next().css("color","#FC4343");
                obj.next().append("&#xe655; 密码不匹配");
			}
            obj.focus();
            return false;
		}
        return true;
	}

	//登录号校验
	function  dlhyz(obj) {
        /*$.ajax(

        );*/
    }

</script>
</body>
</html>

