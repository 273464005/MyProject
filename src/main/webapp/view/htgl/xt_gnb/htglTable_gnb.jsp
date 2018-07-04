<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>功能列表</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
    <style type="text/css">

    </style>
</head>
<body>

<div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>功能列表</legend>
    </fieldset>
    <div>
        <div>
            搜索功能名称：
            <div class="layui-inline">
                <input class="layui-input" name="gnmc" id="mc" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload" id="amcss">搜索</button>
        </div>
        <div>
            <table class="layui-hide" id="gnTable" lay-filter="gnTable"></table>
        </div>
    </div>

    <script type="text/html" id="cz">
        <a class="layui-btn {{d.zt==0?'layui-btn-warm':'layui-btn'}} layui-btn-xs" lay-event="jy">{{d.zt==0?'禁用':'启用'}}</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>

</div>
<script src="<%=basePath%>layui/layui.all.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/myjs/layui-xtree.js"></script>
<script type="text/javascript">
    layui.use('table', function(){
        var table = layui.table
            , form = layui.form
            , $ = layui.jquery;

        table.render({
            elem: '#gnTable'
            ,url:'<%=basePath%>/htgl/xtgn/getXt_gnb_table'
            ,cellMinWidth: 80
            ,cols: [[
                {type:'numbers'}
                ,{field:'gnmc', title:'功能名称'}
                ,{field:'dyqx', title:'级别'}
                ,{field:'fid', title:'所属功能'}
                ,{field:'ljdz', title:'连接地址'}
                ,{field:'gnlb', title: '功能类别'}
                ,{field:'ztmc', title:'状态', width:80}
                ,{field:'right', title: '操作', width:177,toolbar:"#cz"}
            ]]
            ,page: true
        });

        table.on('tool(gnTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('确定删除该功能吗？', function(index){
                    $.ajax({
                        method:'post'
                        , url:'<%=basePath%>/htgl/czry/deleteGg_czry'
                        ,beforeSend: function () {
                            processIndex = layer.load();
                        }
                        , data:{
                            id:data.id
                            ,zt:data.zt
                            ,qx:data.qx
                        }
                        , success:function (data) {
                            popupOk(data,function () {
                                layer.close(processIndex);
                                table.reload('gnTable');
                            },function () {

                            });
                        }
                        , error:function (data) {
                            layer.close(processIndex);
                        }
                    });
                    layer.close(index);
                });
            }
            if (obj.event === 'jy'){
                tsmc = '';
                if(data.zt==0){
                    tsmc = '禁用';
                } else {
                    tsmc = '启用';
                }
                layer.confirm('确定'+tsmc+'该功能吗？', function(index){
                    $.ajax({
                        method:'post'
                        , url:'<%=basePath%>/htgl/czry/editGg_czry_zt'
                        ,beforeSend: function () {
                            processIndex = layer.load();
                        }
                        , data:{
                            id:data.id
                            ,zt:data.zt
                            ,qx:data.qx
                        }
                        , success:function (data) {
                            popupOk(data,function () {
                                layer.close(processIndex);
                                table.reload('gnTable');
                            },function () {

                            });
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
                var mc = $('#mc');
                //执行重载
                table.reload('gnTable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        mc: mc.val()
                    }
                    ,method:'post'
                });
            }
        };
        $('#amcss').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>
</body>
</html>
