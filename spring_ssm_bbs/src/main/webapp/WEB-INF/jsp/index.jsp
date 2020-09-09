<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- jQuery标签库-->
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.messager.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery_2.1.4_jquery.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/layui/css/layui.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/layer/mobile/need/layer.css">--%>
    <script src="${pageContext.request.contextPath}/resource/layui/layui.js" charset="utf-8"></script>
    <%--<script src="${pageContext.request.contextPath}/resource/layer/layer.js" charset="utf-8"></script>--%>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div style=" position: absolute; left: 0;top: 0;width: 200px; height: 100%;line-height: 60px;text-align: center;color: #009688; font-size: 16px;">
            后台布局
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="#">控制台</a></li>
            <li class="layui-nav-item"><a href="#">商品管理</a></li>
            <li class="layui-nav-item"><a href="#">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="#">邮件管理</a></dd>
                    <dd><a href="#">消息管理</a></dd>
                    <dd><a href="#">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav " style="  position: absolute !important;right: 0;top: 0">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${request.getContextPath()}/getPhoto?imgUrl=${sessionScope.session_User.picPath}"
                         style="width: 30px;height: 30px;margin-right: 10px;border-radius: 50%">
                    ${sessionScope.session_User.loginname}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" onclick="getUerMgr()">基本资料</a></dd>
                    <dd><a href="javascript:;" onclick="Repassword()">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="#" onclick="doLogout()">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">所有商品</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" onclick="iframe(1)">列表一</a></dd>
                        <dd><a href="javascript:;" onclick="iframe(2)">列表二</a></dd>
                        <dd><a href="javascript:;">列表三</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="#">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="height: 600px">
        <div class="layui-tab" lay-filter="demo">
            <ul class="layui-tab-title">
                <li class="layui-this">登录页面</li>
                <li>商品管理</li>
                <li>订单管理</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <!-- 内容主体区域 -->
                    <iframe id="myiframe" src="" frameborder="0" style="width:1200px;height:600px;"></iframe>
                </div>
                <div class="layui-tab-item">
                    <!-- 内容主体区域 -->
                    <iframe id="iframe" src="" frameborder="0" style="width:auto;height:auto;"></iframe>
                </div>
                <div class="layui-tab-item">内容3
                </div>
            </div>
        </div>

    </div>


    <div class="layui-footer">


    </div>


</div>
<div style="display: block" id="getUser">
    adasdasdadasda
</div>
</body>


<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
    });
    layui.use('layer', function () {
        var layer = layui.layer;
    });

    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?d214947968792b839fd669a4decaaffc";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();

    //列表内容切换
    function iframe(i) {
        switch (i) {
            case 1:
                $("#myiframe").attr("src", "/city/re");
            case 2:
                $("#iframe").attr("src", "/city/user");

            default:
                return;
        }
    }
    //退出登录
    function doLogout() {
        <%--var con;--%>
        <%--con =window.confirm("你确定要退出吗？");--%>
        <%--if (con==true) {--%>
        <%--}--%>
        <%--else {--%>
        <%--window.alert("取消退出");--%>
        <%--}--%>

        layui.use('layer', function () {

            var layer = layui.layer;
            //询问框
            layer.confirm('你确定要退出系统吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                window.location.href = '${request.getContextPath()}/user/seeout';
            }, function () {
                layer.msg('已取消');
            });
        });


    }
    //获取用户信息
    function getUerMgr() {
        var loginName = "";
        $.ajax(
            {
                type: "POST",
                url: "",
                data: {
                    Loginname: loginName
                },
                datatype: "json",
                async: false,
                success: function (data) {


                },
                error: function (data) {


                }
            }
        );

    }
    function Repassword() {
//        $("#getUser").css("display", "block");

        //默认prompt
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.prompt(function(val, index){
                titel:"我是标题"
                layer.msg('得到了'+val);
                layer.close(index);
            });
        });

    }



    function setIframeHeight(iframe) {
        if (iframe) {
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if (iframeWin.document.body) {
                iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
                iframe.width = iframeWin.document.documentElement.scrollWidth || iframeWin.document.body.scrollWidth;
            }
        }
    };

    window.onload = function () {
        setIframeHeight(document.getElementById('myiframe'));
        setIframeHeight(document.getElementById('iframe'));
    };
</script>
</html>