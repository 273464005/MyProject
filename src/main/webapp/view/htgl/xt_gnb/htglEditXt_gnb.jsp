<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <title>添加功能</title>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>编辑功能</legend>
</fieldset>
<div>
    <form class="layui-form" action="" method="post" id="formId">
        <div hidden="hidden">
            <input name="id" value="${xt_gnb.id}">
            <input name="sxh" value="${xt_gnb.sxh}">
            <c:if test="${xt_gnb==null}">
                <input id="sfxz" value="true">
            </c:if>
            <c:if test="${xt_gnb!=null}">
                <input id="sfxz" value="false">
            </c:if>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">功能名称</label>
            <div class="layui-input-inline">
                <input type="text" name="gnmc" lay-verify="required" placeholder="请输入功能名称" autocomplete="off" class="layui-input" value="${xt_gnb.gnmc}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">功能类别</label>
            <div class="layui-input-block">
                <c:if test="${xt_gnb==null || xt_gnb.gnlb == 0}">
                    <input type="radio" name="gnlb" value="1" title="目录" >
                    <input type="radio" name="gnlb" value="0" title="节点" checked="checked">
                </c:if>
                <c:if test="${xt_gnb.gnlb == 1}">
                    <input type="radio" name="gnlb" value="1" title="目录" checked="checked">
                    <input type="radio" name="gnlb" value="0" title="节点">
                </c:if>
            </div>
        </div>
        <c:if test="${xt_gnb==null ||xt_gnb.gnlb == 0}">
            <div class="layui-form-item" id="ssgn">
                <label class="layui-form-label">连接地址</label>
                <div class="layui-input-inline">
                    <input type="text" name="ljdz" lay-verify="required" placeholder="请输入连接地址" autocomplete="off" class="layui-input" value="${xt_gnb.ljdz}">
                </div>
            </div>
        </c:if>
        <c:if test="${xt_gnb.gnlb == 1}">
            <div class="layui-form-item" id="ssgn" hidden="hidden">
                <label class="layui-form-label">连接地址</label>
                <div class="layui-input-inline">
                    <input type="text" name="ljdz" placeholder="请输入连接地址" autocomplete="off" class="layui-input" value="${xt_gnb.ljdz}" onchange="">
                </div>
            </div>
        </c:if>
        <div class="layui-form-item">
            <label class="layui-form-label">所属功能</label>
            <div class="layui-input-inline">
                <select name="fid">
                    <option value="">默认（默认为父级功能）</option>
                    <c:forEach items="${xt_gnbList}" var="gns">
                        <c:if test="${xt_gnb.fid == gns.id}">
                            <option value="${gns.id}" id="${gns.id}" selected>${gns.gnmc}</option>
                        </c:if>
                        <c:if test="${xt_gnb.fid != gns.id}">
                            <option value="${gns.id}" id="${gns.id}">${gns.gnmc}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="layui-form-mid layui-word-aux">默认为父级功能</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权限</label>
            <div class="layui-input-inline">
                <select name="dyqx">
                    <c:forEach items="${qxList}" var="qxs">
                        <c:if test="${xt_gnb.dyqx == qxs.key}">
                            <option value="${qxs.key}" id="${qxs.key}" selected>${qxs.value}</option>
                        </c:if>
                        <c:if test="${xt_gnb.dyqx != qxs.key}">
                            <option value="${qxs.key}" id="${qxs.key}">${qxs.value}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">禁用/启用</label>
            <div class="layui-input-block">
                <c:if test="${xt_gnb.zt == gnjy}">
                    <input type="checkbox" checked  name="zt" lay-skin="switch" value="1" lay-filter="switchTest" lay-text="禁用|启用">
                </c:if>
                <c:if test="${xt_gnb.zt != gnjy}">
                    <input type="checkbox"  name="zt" lay-skin="switch" value="1" lay-filter="switchTest" lay-text="禁用|启用">
                </c:if>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js" ></script>
<script type="text/javascript" src="<%=basePath%>layui/layui.all.js" ></script>
<script type="text/javascript" src="<%=basePath%>js/information.js"></script>
<script type="text/javascript" src="<%=basePath%>layer/layer.js" ></script>
<script type="text/javascript">
    layui.use('form', function () {
        var form = layui.form
            , $ = layui.jquery;
        form.on('radio', function (data) {
            if(data.value==="1") {
                $("input[name=ljdz]").removeAttr("lay-verify");
                $("#ssgn").attr("hidden", "hidden");

            }else{
                $("#ssgn").removeAttr("hidden");
                $("input[name=ljdz]").attr("lay-verify")
            }
        });
        form.on('submit(formDemo)',function (data) {
            $.ajax({
                url:"<%=basePath%>htgl/xtgn/saveXt_gnb",
                method:"post",
                data:$("#formId").serialize(),
                beforeSend:function () {
                    index = layer.load();
                },
                success:function (data) {
                    layer.close(index);
                    var sfxz = $("#sfxz").val();
                    popupOk(data,function () {
                        if (sfxz == true || sfxz == "true"){
                            childrenDelParentThisTab
                        } else {
                            childrenDelParentThisTab
                            <%--location.href = '${basePath}htgl/xtgn';--%>
                        }
                    },function () {

                    })
                },
                error:function (data) {
                    layer.close(index);
                }
            });
            return false;
        });
    });
</script>
</body>
</html>