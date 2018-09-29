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
 * 提示信息
 * @param text 提示内容
 * @param type 提示类型
 */
function msg(text,type) {
    var time = 0;
    if (type > 1 && type%2 === 0){
        time = 3000;
    } else {
    }
    layer.alert(text, {
        icon: type
        , shade: 0
        , time: time
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
