function popupOk(data,successCallBack,errorCallBack){
    if(data.state == 1){
        layer.alert(data.text,{
            icon:1
            ,time:3000
            ,shade:0
            ,end:successCallBack
            ,title:'提示信息'
        });
        return true;
    }
    if(data.state == 2){
        layer.alert(data.text,{
            icon:2
            ,shade:0
            ,end:errorCallBack
            ,title:'提示信息'
        });
        return false;
    }
}
