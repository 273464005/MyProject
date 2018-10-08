function popupOk(data,successCallBack,errorCallBack){
    if(data.state == 1){
        layer.alert(data.text, {
            icon: 1
            , time: 3000
            , shade: 0
            , end: successCallBack
            , title: '提示信息'
        });
        return true;
    }
    if(data.state == 2){
        layer.alert(data.text, {
            icon: 2
            , shade: 0
            , end: errorCallBack
            , title: '提示信息'
        });
        return false;
    }
}

/**
 * 提示信息（处理json字符串）
 * @param data 提示内容（json字符串）
 * @param successCallBack 成功执行函数
 * @param errorCallBack 失败执行函数
 * @returns {boolean} 执行结果
 */
function jsonMsg(data,successCallBack,errorCallBack){
    if(data.state == 1){
        layer.msg(data.text, {
            icon: 1,
            time: 3000 //2秒关闭（如果不配置，默认是3秒）
        }, successCallBack);
        return true;
    }
    if(data.state == 2){
        layer.msg(data.text, {
            icon: 2,
            time: 3000 //2秒关闭（如果不配置，默认是3秒）
        }, errorCallBack);
        return false;
    }
}

/**
 * 提示信息（自定义提示信息）
 * @param text 提示内容
 * @param type 提示类型（1-√，2-×，3-？，4-锁，5-哭脸，6-笑脸，7-！）
 * @param callBack 结束后执行函数
 * @param time 显示时间
 */
function msg(text,type,callBack,time) {
    if (time == '' || time == null){
        time = 3000
    }
    layer.msg(text,{
        icon: type
        ,time: time
    },callBack);
}

/**
 * 弹窗提示信息提示信息
 * @param text 提示内容
 * @param type 提示类型（1-√，2-×，3-？，4-锁，5-哭脸，6-笑脸，7-！）
 */
function alertMsg(text,type) {
    var time = 0;
    if (type > 1 && type%2 === 0){
        time = 3000;
    } else {
    }
    layer.alert(text, {
        icon: type
        , shade: 0
        , time: time
        , title: '提示信息'
    });
}

/**
 * 加载层
 * @param text 加载层提示文字
 * @param time 加载层显示时间
 * @returns {*} 加载层层数（用来在回调中关闭加载层）
 */
function loading(text,time){

    if(text === '' || text === null){
        text = '数据加载中';
    }
    if(time === null){
        time = 0;
    }
    index = layer.load(1, {
        content: text,
        shade: [0.4, '#393D49'],
        time: time,
        success: function (layero) {
            layero.css('padding-left', '30px');
            layero.find('.layui-layer-content').css({
                'padding-top': '40px',
                'width': '70px',
                'text-align':'center',
                'background-position-x': '16px'
            });
        }
    });
    return index;
}

/**
 * 关闭当前窗口
 */
function closeThisWindow() {
    var userAgent = navigator.userAgent;
    if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") !=-1) {
        window.location.href="about:blank";
        window.close();
    } else {
        window.opener = null;
        window.open("", "_self");
        window.close();
    }
}