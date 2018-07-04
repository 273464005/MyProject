<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户管理</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
</head>
<body>
    <div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>用户管理</legend>
        </fieldset>
        <div>
            搜索用户名：
            <div class="layui-inline">
                <input class="layui-input" name="mc" id="mc" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload" id="amcss">搜索</button>
        </div>
        <div>
            <table class="layui-hide" id="czryTable" lay-filter="czryTable"></table>
        </div>
    </div>

    <script type="text/html" id="xb">
        <i class="layui-icon {{d.xb==0?'layui-icon-male layui-bg-blue':'layui-icon-female layui-bg-red'}}"></i> {{d.xbmc}}
    </script>

    <script type="text/html" id="cz">
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="bj"><i class="layui-icon layui-icon-edit"></i>编辑</a>
        <a class="layui-btn {{d.zt==0?'layui-btn-warm':'layui-btn'}} layui-btn-xs" lay-event="jy">{{d.zt==0?'禁用':'启用'}}</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
    </script>
    <script src="<%=basePath%>layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/information.js"></script>
    <script type="text/javascript">
        layui.use('table', function(){
            var table = layui.table
                , form = layui.form
                , $ = layui.jquery;

            table.render({
                elem: '#czryTable'
                ,url:'<%=basePath%>/htgl/czry/getAllCzry'
                ,where:{
                    qx:${user.qx}
                }
                ,cellMinWidth: 80
                ,cols: [[
                    {type:'numbers'}
                    ,{field:'mc', title:'用户名'}
                    ,{field:'dlh', title:'登录号'}
                    ,{field:'qxmc', title:'级别'}
                    ,{field:'email', title:'邮箱'}
                    ,{field:'sjh', title:'手机号'}
                    ,{field:'sfzh', title: '身份证号'}
                    ,{field:'xbmc', title:'性别', width:80,toolbar:'#xb'}
                    ,{field:'ztmc', title:'状态', width:80}
                    ,{field:'right', title: '操作', width:200,toolbar:"#cz"}
                ]]
                ,page: true
            });

            table.on('tool(czryTable)', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    layer.confirm('确定删除该用户吗？', function(index){
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
                                    table.reload('czryTable');
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
                if (obj.event === 'jy'){
                    tsmc = '';
                    if(data.zt==0){
                        tsmc = '禁用';
                    } else {
                        tsmc = '启用';
                    }
                    layer.confirm('确定'+tsmc+'该用户吗？', function(index){
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
                                    table.reload('czryTable');
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

                if (obj.event === 'bj'){
                    location.href = '<%=basePath%>htgl/czry/editGg_czry?czryid='+data.id;
                }

            });

            var $ = layui.$, active = {
                reload: function(){
                    var mc = $('#mc');
                    //执行重载
                    table.reload('czryTable', {
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
