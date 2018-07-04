<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/uploadImg"+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <title>基本信息</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/layui/css/layui.css"/>
    <script src="<%=basePath%>js/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path%>/layer/layer.js"></script>
    <script type="text/javascript" src="<%=path%>/layui/layui.all.js"></script>
</head>
<body>
<div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>基本信息</legend>
    </fieldset>
    <form class="layui-form" action="" lay-filter="formCzry">
        <div hidden="">
            <input type="hidden" value="${gg_czry.id}" name="id">
            <input type="hidden" value="${gg_czry.zt}" name="zt">
            <c:if test="${user.qx >= GG_CZRY_QX_PTYH}">
                <input type="hidden" value="${gg_czry.qx}" name="qx">
            </c:if>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="mc" id="mc" lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">登录号</label>
            <div class="layui-input-inline">
                <input type="text" name="dlh" id="dlh" lay-verify="required" placeholder="请输入登录号" autocomplete="off" readonly="readonly" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">登陆系统使用</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">身份证号码</label>
            <div class="layui-input-inline">
                <input type="text" name="sfzh" lay-verify="identity" placeholder="请输入身份证号码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" lay-verify="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生年月</label>
            <div class="layui-input-inline">
                <input type="text" name="csnyr" id="date" lay-verify="date" placeholder="选择出生年月日" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input type="text" name="sjh" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input" value="">
            </div>
        </div>
        <c:if test="${user.qx < GG_CZRY_QX_PTYH}">
            <div class="layui-form-item">
                <label class="layui-form-label">权限</label>
                <div class="layui-input-inline">
                    <select name="qx">
                        <c:forEach items="${qxList}" var="qxs">
                            <c:if test="${gg_czry.qx == qxs.key}">
                                <option value="${qxs.key}" id="${qxs.key}" selected>${qxs.value}</option>
                            </c:if>
                            <c:if test="${gg_czry.qx != qxs.key}">
                                <option value="${qxs.key}" id="${qxs.key}">${qxs.value}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </c:if>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <div class="layui-upload">
                    <button type="button" class="layui-btn layui-btn-normal" id="xzwj">选择文件</button>
                    <%--<button type="button" class="layui-btn layui-btn-normal" id="sctp">上传图片</button>--%>
                    <div id="myImgeDiv">
                        <img src="${showImg}" alt="${gg_imgs.tpmc}" id="myImge" height="${showImgHeight}" width="${showImgWidth}">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="xb" value="0" title="男" checked="checked">
                <input type="radio" name="xb" value="1" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo" id="formDemo">保存</button>
                <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="<%=basePath%>js/information.js"></script>
<script type="text/javascript">
    layui.use(['form', 'laydate','upload'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate
            , upload = layui.upload;
        //日期
        laydate.render({
            elem: '#date'
        });

        //初始赋值
        form.val('formCzry', {
            "mc": '${gg_czry.mc}' // "name": "value"
            , "dlh": '${gg_czry.dlh}'
            , "sfzh": '${gg_czry.sfzh}'
            , "email": '${gg_czry.email}'
            , "csnyr": '${csnyr}'
            , "sjh": '${gg_czry.sjh}'
            , "xb": '${gg_czry.xb}'
        });

        //监听提交
        form.on('submit(formDemo)', function(data){
            data.field.csnyr = data.field.csnyr.replace(/-/g,'');
            $.ajax({
                url: '<%=basePath%>htgl/czry/saveGg_czry'
                , method: 'post'
                , data: data.field
                , beforeSend: function () {
                    loadIndex = layer.load();
                }
                , success: function (returnValue) {
                    popupOk(returnValue, function () {

                    },function () {

                    });
                    layer.close(loadIndex);
                }
                , error:function () {
                    layer.alert('发生未知异常！', {
                        icon: 5
                        , shade: 0
                        , anim: 6
                    });
                    layer.close(loadIndex);
                }
            });
            return false;
        });

        //文件上传
        upload.render({
            elem: '#xzwj'
            ,url: '<%=basePath%>htgl/czry/uploadImg'
            ,auto: false
            //,multiple: true
            ,accpt:'file'
            ,acceptMime:'image/*'
//            ,bindAction: '#sctp'
            ,bindAction:'#formDemo'
            ,data:{
                czryid:$("input[name=id]").val()
                ,imgid:'${gg_imgs.id}'
            }
            ,done: function(res){
                console.log(res)
            }
            ,choose:function (obj) {
                obj.preview(function(index, file, result){
                    var img = new Image();
                    img.src = result;
                    img.onload = function () { //初始化夹在完成后获取上传图片宽高，判断限制上传图片的大小。
                        wt = 190;
                        hg = wt * (img.height/img.width);
                        $("#myImge").attr('width', wt + 'px');
                        $("#myImge").attr('height', hg + 'px');
                    };
                    $('#myImge').attr('src', result); //图片链接（base64）
                });
            }
        });

        form.render();
    });


</script>
</body>
</html>

