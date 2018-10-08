<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>即时通信</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
    <script type="text/javascript" src="<%=basePath%>layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/information.js"></script>
    <script type="text/javascript" src="<%=basePath%>layer/layer.js" ></script>
</head>
<body>
<div>
    <div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>${ltfj.fjmc}</legend>
        </fieldset>
    </div>
    <div>
        <div style="display: inline-block;width: 450px; height: 88%; padding: 10px; border: 1px solid #ddd; overflow: auto;">
            <div class="layui-col-md12">
                <fieldset class="layui-elem-field">
                    <legend>房间号</legend>
                    <div class="layui-field-box">
                        ${ltfj.fjh}
                    </div>
                </fieldset>
            </div>
            <div class="layui-col-md12">
                <fieldset class="layui-elem-field">
                    <legend>房间简介</legend>
                    <div class="layui-field-box">
                        ${ltfj.fjms}
                    </div>
                </fieldset>
            </div>
            <c:if test="${ltfj.cjr == user.id}">
                <div style="display: inline-block; margin-top: 10px">
                    <div class="layui-btn-group">
                        <button class="layui-btn" id="bjfjxx">编辑房间信息</button>
                        <button class="layui-btn" id="jsfj">解散房间</button>
                    </div>
                </div>
            </c:if>
            <c:if test="${ltfj.cjr != user.id}">
                <div style="display: inline-block; margin-top: 10px;float: right">
                    <button class="layui-btn" id="tcfj">退出房间</button>
                </div>
            </c:if>
            <div style="display: inline-block;width: 428px;margin-top: 10px; padding: 10px overflow: auto;">
                <fieldset class="layui-elem-field">
                    <legend>房间人员</legend>
                    <div class="layui-field-box">
                        <c:forEach items="${fjryList}" var="fjry">
                            <div style="display: inline-block;">
                                <label>${fjry.get("rymc")}</label>
                                <c:if test="${fjry.get('ryid') == ltfj.cjr}">
                                    <div style="display: inline-block;margin-left: 40px">
                                        <label>房主</label>
                                    </div>
                                </c:if>
                            </div>
                            <c:if test="${ltfj.cjr == user.id && ltfj.cjr != fjry.get('ryid')}">
                                <div style="float: right;">
                                    <div class="layui-btn-group">
                                        <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="yhjy(this,'${fjry.get("ryid")}','${fjry.get("fjid")}')">
                                            <c:if test="${fjry.get('zt') == GG_FJRYB_ZT_ZC}">
                                                禁言
                                            </c:if>
                                            <c:if test="${fjry.get('zt') != GG_FJRYB_ZT_ZC}">
                                                解禁
                                            </c:if>
                                        </button>
                                        <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="yhtc('${fjry.get("ryid")}','${fjry.get("fjid")}')">踢出</button>
                                    </div>
                                </div>
                            </c:if>
                            <hr class="layui-bg-gray">
                        </c:forEach>
                    </div>
                </fieldset>
            </div>
        </div>
        <div style="display: inline-block; width: 60%; height: 88%; padding: 10px; border: 1px solid #ddd; overflow: auto;">
            <iframe id="showXt_gnb" name="if_content" height="100%" width="100%" onload="this.height=if_content.document.body.scrollHeight" frameborder="0" marginwidth="0" marginheight="0"
                    src="<%=basePath%>ltyl/jstx/jstxWebSocket?fjid=${ltfj.id}">
            </iframe>
        </div>
    </div>
</div>
<script type="text/javascript">
    layui.use('layer', function() {
            var $ = layui.jquery
                , layer = layui.layer;

        $("#bjfjxx").on('click',function () {
            layer.open({
                type: 2,
                title: '编辑房间信息',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层 true可以关闭
                area : ['535px' , '400px'],
                resize : false,
                content: '<%=basePath%>ltyl/fjgl/editXjfj?ltfjid=${ltfj.id}'
            });
        });

        $("#jsfj").on('click',function () {
            layer.confirm('确认解散房间？', {icon: 3, title:'提示'}, function(index){
                $.ajax({
                    method:'post'
                    , url:'<%=basePath%>/ltyl/jstx/onJsfj'
                    , data:{
                        id:'${ltfj.id}'
                    }
                    , success:function (returnValue) {
                        popupOk(returnValue,function () {
                            <%--location.href = '<%=basePath%>ltyl/fjgl'--%>
                            closeThisWindow();
                        },function () {
                            
                        });
                    }
                    , error:function () {
                        alertMsg("发生未知异常！",2);
                    }
                });
                layer.close(index);
            });
        });

        $("#tcfj").on('click',function () {
            layer.confirm('确认退出房间？', {icon: 3, title:'提示'}, function(index){
                $.ajax({
                    method:'post'
                    , url:'<%=basePath%>/ltyl/jstx/onTcfj'
                    , data:{
                        id:'${ltfj.id}'
                    }
                    , success:function (returnValue) {
                        popupOk(returnValue,function () {
                            <%--location.href = '<%=basePath%>ltyl/fjgl'--%>
                            closeThisWindow();
                        },function () {

                        });
                    }
                    , error:function () {
                        alertMsg("发生未知异常！",2);
                    }
                });
                layer.close(index);
            });
        });
    });
    function yhjy(obj,ryid,fjid) {
        tsxx = "确认" +$(obj).html()+ "?";
        layer.confirm(tsxx,{icon:3,title:'提示'},function (index) {
            $.ajax({
                method:'post'
                , url:'<%=basePath%>/ltyl/jstx/onYhjy'
                , data:{
                    ryid:ryid
                    ,fjid:fjid
                }
                , success:function (returnValue) {
                    popupOk(returnValue.state,function () {
                        <%--location.href = '<%=basePath%>ltyl/jstx/jstxIndex?fjid=${ltfj.id}';--%>
                        $(obj).html(returnValue.showName);
                    },function () {

                    })
                }
                , error:function () {
                    alertMsg("发生未知异常！",2);
                }
            });
            layer.close(index);
        });
    }

    function yhtc(ryid,fjid) {
        layer.confirm("确认将用户踢出房间？",{icon:3,title:'提示'},function (index) {
            $.ajax({
                method:'post'
                , url:'<%=basePath%>/ltyl/jstx/onYhtc'
                , data:{
                    ryid:ryid
                    ,fjid:fjid
                }
                , success:function (returnValue) {
                    popupOk(returnValue,function () {
                        location.href = '<%=basePath%>ltyl/jstx/jstxIndex?fjid=${ltfj.id}';
                    },function () {

                    })
                }
                , error:function () {
                    alertMsg("发生未知异常！",2);
                }
            })
        });
    }

</script>
</body>
</html>
