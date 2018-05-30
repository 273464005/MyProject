
$(function(){
	//---------姓名验证---------------
	function xmyz(){
        nameval = $("input[name=uname]").val();
        if(nameval == ""){
            $("span[id=uname]").css("color","#FC4343");
            $("span[id=uname]").append("&#xe80d; 姓名不能为空");
            return false;
        }else{
            $("span[id=uname]").css("color","#5BC92E");
            $("span[id=uname]").html("&#xe6bb; 正确");
            return true;
        }
	}

	$("input[name=uname]").focus(function(){
		$("span[id=uname]").html("");
	});
	//--------登录号验证-------------
	function dlhyz(){
        reg = /^[a-zA-Z][a-zA-Z0-9]{3,15}$/;//用户名正则
        nameval = $("input[name=userName]").val();
        if(nameval == ""){
            $("span[id=userName]").css("color","#FC4343");
            $("span[id=userName]").append("&#xe80d; 用户名不能为空");
            return false;
        }else{
            if(!reg.test(nameval)){
                $("span[id=userName]").css("color","#FC4343");
                $("span[id=userName]").append("&#xe655; 用户名格式错误");
                return false;
            }else{
                $("span[id=userName]").css("color","#5BC92E");
                $("span[id=userName]").html("&#xe6bb; 正确");
                return true;
            }
        }
	}
	$("input[name=userName]").focus(function(){
		$("span[id=userName]").html("");
	});
	//--------密码验证--------------
	function mmyz(){
        reg =  /^[a-zA-Z0-9]{3,16}$/;//密码正则
        nameval = $("input[name=pwd]").val();
        if(nameval == ""){
            $("span[id=pwd]").css("color","#FC4343");
            $("span[id=pwd]").append("&#xe80d; 密码不能为空");
            return false;
        }else{
            if(!reg.test(nameval)){
                $("span[id=pwd]").css("color","#FC4343");
                $("span[id=pwd]").append("&#xe655; 密码必须为3~16位的数字和字母组合");
                return false;
            }else {
                $("span[id=pwd]").css("color","#5BC92E");
                $("span[id=pwd]").html("&#xe6bb; 正确");
                return true;
            }
        }
	}
	$("input[name=pwd]").focus(function(){
		$("span[id=pwd]").html("");
	});
	//----------------确认密码-----------------------------
	function repmmyz(){
        reppwd = $("input[name=reppwd]").val();
        nameval = $("input[name=pwd]").val();
        if(reppwd == ""){
            $("span[id=reppwd]").css("color","#FC4343");
            $("span[id=reppwd]").append("&#xe80d; 密码不能为空");
            return false;
        }else{
            if(reppwd != nameval){
                $("span[id=reppwd]").css("color","#FC4343");
                $("span[id=reppwd]").append("&#xe655; 密码不匹配");
                return false;
            }else {
                $("span[id=reppwd]").css("color","#5BC92E");
                $("span[id=reppwd]").html("&#xe6bb; 正确");
                return true;
            }
        }
	}
	$("input[name=reppwd]").focus(function(){
		$("span[id=reppwd]").html("");
	});
	//----------------手机号验证-----------------------------
	function sjhyz(){
        reg = /^1(3|5|8|9|7)[0-9]{9}$/;//手机号正则
        nameval = $("input[name=phone]").val();
        if(nameval == ""){
            $("span[id=phone]").css("color","#FC4343");
            $("span[id=phone]").append("&#xe80d; 手机号不能为空");
            return false;
            $("button[id=getcode]").removeClass("btn-primary");
            $("button[id=getcode]").addClass("btn-default");
            $("button[id=getcode]").html("");
            $("button[id=getcode]").append("请输入手机号");
            $("button[id=getcode]").attr("disabled",true);
        }else{
            if(!reg.test(nameval)){
                $("span[id=phone]").css("color","#FC4343");
                $("span[id=phone]").append("&#xe655; 手机号不正确");
                return false;
                $("button[id=getcode]").removeClass("btn-primary");
                $("button[id=getcode]").addClass("btn-default");
                $("button[id=getcode]").html("");
                $("button[id=getcode]").append("手机号不正确");
                $("button[id=getcode]").attr("disabled",true);
            }else {
                $("span[id=phone]").css("color","#5BC92E");
                $("span[id=phone]").html("&#xe6bb; 正确");
                return true;
                $("button[id=getcode]").removeClass("btn-default");
                $("button[id=getcode]").addClass("btn-primary");
                $("button[id=getcode]").html("");
                $("button[id=getcode]").append("免费获取验证码");
                $("button[id=getcode]").removeAttr("disabled");
            }
        }
	}

	$("input[name=phone]").focus(function(){
		$("span[id=phone]").html("");
	});
	//-------------身份证验证-------------------------------
	function sfzyz(){
        reg =/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;//身份证正则
        nameval = $("input[name=idcard]").val();
        if(nameval == ""){
            $("span[id=idcard]").css("color","#FC4343");
            $("span[id=idcard]").append("&#xe80d; 身份证号不能为空");
            return false;
        }else{
            if(!reg.test(nameval)){
                $("span[id=idcard]").css("color","#FC4343");
                $("span[id=idcard]").append("&#xe655; 身份证号不正确");
                return false;
            }else {
                $("span[id=idcard]").css("color","#5BC92E");
                $("span[id=idcard]").html("&#xe6bb; 正确");
                return true;
            }
        }
	}
	$("input[name=idcard]").focus(function(){
		$("span[id=idcard]").html("");
	});
	//------------------邮箱验证-----------------------
	function emaliyz(){
        reg = /\w+@[a-zA-z0-9]{2,4}\.[a-zA-z]{2,3}(\.[a-zA-Z]{2.3})?$/;//邮箱正则
        nameval = $("input[name=emails]").val();
        if(nameval == ""){
            $("span[id=emails]").css("color","#FC4343");
            $("span[id=emails]").append("&#xe80d; 邮箱不能为空");
            return false;
        }else{
            if(!reg.test(nameval)){
                $("span[id=emails]").css("color","#FC4343");
                $("span[id=emails]").append("&#xe655; 邮箱不正确");
                return false;
            }else {
                $("span[id=emails]").css("color","#5BC92E");
                $("span[id=emails]").html("&#xe6bb; 正确");
                return true;
            }
        }

    }
	$("input[name=emails]").focus(function(){
		$("span[id=emails]").html("");
	});
	//------------------重置按钮----------------------
	$("button[type=reset]").click(function(){
		$("span[id!=colorred]").html("");
	});
	//----------------提交按钮------------------------
	$("button[name=submit]").click(function(){
		if($("span[id=submit]").text() != ""){
			$("span[id=submit]").html("");
		}
		
		if(bool){
			$("button[id=submit]").attr("type","submit")
		}else{
			$("span[id=submit]").css("color","#FC4343");
			$("span[id=submit]").append("&#xe80d; 请将信息填写完整");
		}
		
	});
	//-----------发送验证码按钮---------------------
	
	$("button[id=getcode]").html("");
	$("button[id=getcode]").append("请输入手机号");
	$("button[id=getcode]").attr("disabled",true);
	
	$("button[id=getcode]").click(function(){
		i =60;
		btn = $("button[id=getcode]");
		//倒计时
		var t=setInterval(function(){
			if(i<=0){
				clearInterval(t);
				btn.html("");
				btn.removeAttr("disabled");
				btn.append("免费获取验证码");
				i = 60;
			}else{
				btn.html("");
				btn.attr("disabled",true);
				btn.append("重新发送(" + i + ")");
				i--;
			}
		},1000);
	});
	
//	var countdown=10; 
//	function settime(val) { 
//		if (countdown == 0) { 
//			val.removeAttribute("disabled"); 
//			val.value="免费获取验证码"; 
//			countdown = 10; 
//		} else { 
//			val.setAttribute("disabled", true); 
//			val.value="重新发送(" + countdown + ")"; 
//			countdown--; 
//		} 
//		setTimeout(function(){
//			settime(val)},1000)
//	} 
	

	
})