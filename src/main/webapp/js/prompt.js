function prompt(data){
    if(data.state == 1){
        layer.open({
            type: 1
            ,offset: 'auto'
            ,id: 'layerDemo'+'auto' //防止重复弹出
            ,content: '<div><i class="layui-icon layui-icon-face-smile" style="font-size: 30px; color: #4cae4c;"></i>' + data.text +'</div>'
            ,time:3000
            ,btn: '确定'
            ,btnAlign: 'r'
            ,shade: 0 //不显示遮罩
            ,yes: function(){
                layer.closeAll();
            }
        });
        // layer.msg(data.text, {time: 3000, icon:6},function () {
        //     window.parent.location.reload();//刷新父页面
        //     parent.layer.close(index);//关闭弹窗
        // });
    }
    if(data.state == 2){
        layer.open({
            type: 1
            ,offset: 'auto'
            ,id: 'layerDemo'+'auto' //防止重复弹出
            ,content: '<div><i class="layui-icon layui-icon-face-cry" style="font-size: 30px; color: #c9302c;"></i>' + data.text +'</div>'
            ,btn: '确定'
            ,btnAlign: 'r'
            ,shade: 0 //不显示遮罩
            ,yes: function(){
                layer.closeAll();
            }
        });
    }
}
