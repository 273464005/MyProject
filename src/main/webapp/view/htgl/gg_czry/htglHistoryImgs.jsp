<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>历史头像</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/layui/css/layui.css"/>
    <script src="${basePath}js/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="${basePath}layer/layer.js"></script>
    <script type="text/javascript" src="${basePath}layui/layui.all.js"></script>
    <script type="text/javascript" src="${basePath}js/information.js"></script>
</head>
<body>
    <div style="margin-left: 20px">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend><span style="color: red">${czry.mc}</span>曾经使用过的头像</legend>
        </fieldset>

        <div id="historyImgs">
            <ul id="imgUL">
                <c:forEach items="${showImgPathList}" var="imgs">
                    <li style="display: inline-block;margin-top: 5px">
                        <img lay-src="${imgs.get("showImgPath")}" layer-pid alt="${imgs.get("tpmc")}"style="width: 190px;height: 119px">
                        <button class="layui-btn layui-btn-xs" style="display: block;" onclick="sztx('${czry.txdz}','${imgs.get("imgid")}')">
                            设为头像
                            <c:if test="${czry.txdz == imgs.get('imgid')}">
                                （当前头像）
                            </c:if>
                        </button>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <script type="text/javascript">
        layui.use('flow', function() {
            var flow = layui.flow;
            flow.lazyimg({
                elem: '#imgUL img'
            });

            layer.photos({
                photos: '#imgUL'
                ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
            });
        });
        function sztx(dqtx,tpid) {
            if (dqtx == tpid){
                msg("所选图片已是当前头像！",2)
            } else {
                $.ajax({
                    method: 'post'
                    , url:'${basePath}htgl/czry/updateRyTx'
                    , data:{
                        ryid:${czry.id}
                        , tpid:tpid
                    }
                    , success:function (returnValue) {
                        jsonMsg(returnValue,function () {
                            reloadParentWindow();
                            window.location.href = "${basePath}zcdl/htglMainHome"
                        },function () {

                        })
                    }
                    , error:function () {
                        alertMsg("发生未知异常",2)
                    }
                });
            }
        }
    </script>
</body>
</html>
