function popupOk(data,callBack){
    if(data.state == 1){
        layer.alert(data.text,{
            icon:1
            ,time:3000
            ,shade:0
            ,end:callBack
        });
        return true;
    }
    if(data.state == 2){
        layer.alert(data.text,{
            icon:2
            ,shade:0
            ,end:callBack
        });
        return false;
    }
}
