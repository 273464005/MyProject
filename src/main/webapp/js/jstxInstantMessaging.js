/**
 * 即时通信信息
 * @param url 即时通信连接地址
 * @param homeId 会话标识
 * @param userId 发送人
 * @param showMsg 消息展示的地方
 * @param basePath 基础连接地址
 */
function webSocketSendMsg(url,homeId,userId,showMsg,basePath){
    var websocket = null;
    var conversation = homeId + "," + userId;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        //建立连接，这里的/websocket ，是ManagerServlet中开头注解中的那个值
        websocket = new WebSocket("ws:"+url+"websocket/"+conversation);
    } else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        alertMsg("发生未知异常！", 5);
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        // setMessageInnerHTML("WebSocket连接成功");
    };

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    };

    //连接关闭的回调方法
    websocket.onclose = function () {
        // setMessageInnerHTML("WebSocket连接关闭");
    };

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    };

    //将消息显示在网页上
    window.setMessageInnerHTML = function(innerHTML) {
        var showMsgDiv = document.getElementById("showMsgParent");
        document.getElementById(showMsg).innerHTML += '<blockquote class="layui-elem-quote">' +innerHTML +'</blockquote>';
        /*$("#msgUL").append('<li>'
            + '<div class="layim-chat-user">'
                + '<img src="../img/zanwu.jpg" class="layui-nav-img">'
                + '<cite>'
                + 123
                + '</cite>'
            + '</div><div class="layim-chat-text">'
                +innerHTML
            + '</div></li>');*/
        showMsgDiv.scrollTop = showMsgDiv.scrollHeight;
    };

    //关闭WebSocket连接
    window.closeWebSocket = function () {
        websocket.close();
    }

    //发送消息
    window.send =  function(inputId,homeId) {
        $.ajax({
            method:'post'
            ,url: basePath +'/ltyl/jstx/onJsxtSfjy'
            ,data:{
                id:homeId
            }
            , success:function (data) {
                if (data.state == 0){
                    var fsnr = document.getElementById(inputId).value;
                    if (fsnr == null || fsnr == ""){
                        msg("发送内容不能为空！",2);
                    } else {
                        var message = JSON.stringify({'msg':fsnr,'jsrId':homeId });
                        websocket.send(message);
                        document.getElementById('fsnr').value = "";
                    }
                } else if(data.state == 'ytc'){
                    msg("您已被踢出房间",2,function () {
                        // window.parent.location.href = basePath + 'ltyl/fjgl';
                        closeThisWindow();
                    })
                }else {
                    msg("您已被禁言，请联系管理员",2);
                }
            }
            , error:function () {
                alertMsg("发生未知异常！", 5);
            }
        });

    }

}

