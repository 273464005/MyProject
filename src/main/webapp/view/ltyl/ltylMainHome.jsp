<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>聊天娱乐</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
</head>
<body>
    <div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>房间列表</legend>
        </fieldset>
        <div>
            搜索房间：
            <div class="layui-inline">
                <input class="layui-input" name="cjrmc" id="cjrmc" placeholder="请输入房间号或房主名称"  autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload" id="amcss">搜索</button>
            <div style="display:inline-block;float: right;">
                <button class="layui-btn" data-type="reload" id="xjfj">新建房间</button>
            </div>
        </div>
        <div>
            <table class="layui-hide" id="fjlbTable" lay-filter="fjlbTable"></table>
        </div>
    </div>
    <script type="text/html" id="cz">
        {{# if(d.fjzt==0){ }}
            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="jr"><i class="layui-icon layui-icon-edit"></i>加入</a>
        {{# }else{ }}
            {{# if(d.GG_CZRY_QX_GLY < ${user.qx}){ }}
                该房间已被封禁。
            {{# }else{ }}
            {{# }}}
        {{# }}}
        {{# if(d.GG_CZRY_QX_GLY >= ${user.qx}){ }}
            <a class="layui-btn {{d.fjzt==0?'layui-btn-warm':'layui-btn'}} layui-btn-xs" lay-event="jy">
                {{# if(d.fjzt==0){ }}
                    <i class="layui-icon layui-icon-close"></i>封禁
                {{# }else{ }}
                    <i class="layui-icon layui-icon-ok"></i>解封
                {{# }}}
            </a>
        {{# }else{ }}
        {{# }}}
        {{d.GG_CZRY_QX_CJGLY == ${user.qx}?'<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>':''}}
        <%--{{d.GG_CZRY_QX_GLY >= ${user.qx}?'<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="jy">{{d.fjzt==0?'<i class="layui-icon layui-icon-close"></i>封禁':'<i class="layui-icon layui-icon-ok"></i>解封'}}</a>':''}}--%>
    </script>
    <script type="text/javascript" src="<%=basePath%>layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/information.js"></script>
    <script type="text/javascript">
        layui.use('table', function(){
            var table = layui.table
                , form = layui.form
                , $ = layui.jquery;

            table.render({
                elem: '#fjlbTable'
                ,url:'<%=basePath%>/ltyl/fjgl/getFjTab'
                ,cellMinWidth: 80
                ,cols: [[
                    {type:'numbers'}
                    ,{field:'fjh', title:'房间号'}
                    ,{field:'fjmc', title:'房间名称'}
                    ,{field:'fjms', title:'房间描述'}
                    ,{field:'cjrmc', title:'创建人'}
                    ,{field:'cjsjgsh', title:'创建时间'}
                    ,{field:'right', title: '操作',width:220, toolbar:"#cz"}
                ]]
                ,page: true
            });

            table.on('tool(fjlbTable)', function(obj){
                var data = obj.data;
                if (obj.event === 'jr'){
                    $.ajax({
                        method:'post'
                        ,url:'<%=basePath%>ltyl/fjgl/getFjmm?fjid='+data.id
                        ,success:function (date) {
                            if (date.state ==='w'){
                                location.href = '<%=basePath%>ltyl/jstx/jstxIndex?fjid='+data.id;
                            } else if (date.state === 'y'){
                                layer.prompt({title: '请输入密码', formType: 1}, function (fjmm, index1) {
                                    $.ajax({
                                        method:'post'
                                        ,url:'<%=basePath%>ltyl/fjgl/jstxIndexJymm'
                                        ,data:{
                                            id:data.id
                                            ,fjmm:fjmm
                                        }
                                        ,success:function (returnValue) {
                                            layer.close(index1);
                                            popupOk(returnValue, function () {
                                                location.href = '<%=basePath%>ltyl/jstx/jstxIndex?fjid='+data.id;
                                            }, function () {

                                            });
                                        }
                                    })
                                });
                            }
                        }
                        ,error:function () {
                            msg("发生未知异常，请联系管理员", 2);
                        }
                    });

                }
                if (obj.event === 'jy'){
                    tsmc = '';
                    if(data.fjzt==0){
                        tsmc = '<span style="color: #FF5722">封禁</span>';;
                    } else {
                        tsmc = '<span style="color: #009688">解封</span>';;
                    }
                    ymc = '<span style="color: #FFB800">'+data.fjmc+'</span>';
                    layer.confirm('确定'+tsmc+'房间['+ymc+']吗？', function(index){
                        $.ajax({
                            method:'post'
                            , url:'<%=basePath%>/ltyl/fjgl/editGg_ltfj_zt'
                            ,beforeSend: function () {
                                processIndex = layer.load();
                            }
                            , data:{
                                id:data.id
                                ,fjzt:data.fjzt
                            }
                            , success:function (data) {
                                popupOk(data,function () {
                                    table.reload('fjlbTable');
                                },function () {

                                });
                                layer.close(processIndex);
                            }
                            , error:function (data) {
                                layer.close(processIndex);
                            }
                        });
                        layer.close(index);
                    });
                }
                if (obj.event === 'del'){
                    layer.confirm('确定删除该房间吗？', function(index){
                        $.ajax({
                            method:'post'
                            , url:'<%=basePath%>/ltyl/fjgl/deleteGg_ltfj'
                            ,beforeSend: function () {
                                processIndex = layer.load();
                            }
                            , data:{
                                id:data.id
                            }
                            , success:function (data) {
                                popupOk(data,function () {
                                    table.reload('fjlbTable');
                                },function () {

                                });
                                layer.close(processIndex);
                            }
                            , error:function (data) {
                                layer.close(processIndex);
                            }
                        });
                        layer.close(index);
                    });
                }

            });

            var $ = layui.$, active = {
                reload: function(){
                    var cjrmc = $('#cjrmc');
                    //执行重载
                    table.reload('fjlbTable', {
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,where: {
                            cjrmc: cjrmc.val()
                        }
                        ,method:'post'
                    });
                }
            };
            $('#amcss').on('click', function(){
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });

            $("#xjfj").on('click',function () {
                $.ajax({
                    method:'post'
                    ,url:'<%=basePath%>ltyl/fjgl/getRyfj'
                    ,data:{
                        id:'${user.id}'
                    }
                    ,success:function (returnValue) {
                        popupOk(returnValue, function () {
                            <%--location.href = '<%=basePath%>ltyl/fjgl/editXjfj?ltfjid=""';--%>
                            layer.open({
                                type: 2,
                                title: '编辑房间信息',
                                maxmin: true,
                                shadeClose: true, //点击遮罩关闭层 true可以关闭
                                area : ['535px' , '400px'],
                                resize : false,
                                content: '<%=basePath%>ltyl/fjgl/editXjfj?ltfjid=""'
                            });
                        }, function () {

                        });
                    }
                    ,error:function () {
                        msg("发生未知异常！请联系管理员。",2);
                    }
                })
            })
        });

    </script>
</body>
</html>
