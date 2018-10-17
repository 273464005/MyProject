/**
 * 登录号校验
 * @param obj 登录号对象（this)
 * @returns {boolean}是否符合
 * @constructor
 */
function LoginNameCheck(obj){
    var reg = /^[a-zA-Z0-9_-]{3,18}$/;
    var dlh = $(obj).val();
    if(!reg.test(dlh)){
        tipsMsg("请输入合法的登录号（不能包含除_-之外的特殊字符，长度3-18位）",obj,4,5000)
        return false;
    }
    return true;
}

/**
 * 密码校验
 * @param obj 验证对象
 * @returns {boolean} 是否符合
 * @constructor
 */
function LoginPasswordCheck(obj){
    var reg = /^[a-zA-Z0-9_\.-=\+~!@#$%^&*\(\)\\\{\}\[\]\`]{4,18}$/;
    var mm = $(obj).val();
    if(!reg.test(mm)){
        tipsMsg("请输入合法的密码（不能包含空格、中文字符，长度4-18位）",obj,4,5000)
        return false;
    }
    return true;
}
