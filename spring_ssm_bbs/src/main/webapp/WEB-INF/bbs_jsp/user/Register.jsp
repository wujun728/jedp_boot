<%--
  Created by IntelliJ IDEA.
  User: zlq
  Date: 2018/12/26
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>注册</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/resource/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resource/bbs_resourse/js/index.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bbs_resourse/css/global.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/header.jsp"/>
<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20="">
        <div class="layui-tab layui-tab-brief " lay-filter="user">
            <ul class="layui-tab-title">
                <li><a href="/city/login"> 登入</a></li>
                <li class="layui-this">注册</li>
            </ul>
            <div class="layui-form layui-tab-content" id="registerForm" style="padding: 20px 0">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form method="post" action="/Register" id="addregister">
                            <div class="layui-form-item">
                                <label for="L_username" class="layui-form-label">用户名</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_username" name="username" required lay-verify="required"
                                           class="layui-input" autocomplete="off">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_loginname" class="layui-form-label">账号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_loginname" name="loginname" required lay-verify="requires"
                                           class="layui-input" autocomplete="off">
                                    <%--<div class="layui-form-mid layui-word-aux">将成为你唯一的登入名</div>--%>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_pass" name="password" required
                                           lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_repass" class="layui-form-label">确认密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_repass" name="repassword" required
                                           lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_vercode" class="layui-form-label">验证码</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_vercode" name="vercode" required lay-verify="required"
                                           placeholder="请回答后面的问题" autocomplete="off" class="layui-input">
                                </div>
                                <div style="float:left;display: block;padding: 1px 0!important;line-height: 20px;margin-right: 10px">
                                    <span style="color: #c00;">
                                        <img id="randomImg" style="padding: inherit"
                                             onclick="freshImg()" src="/user/VerifyImg"></span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label style="color: #c00;">${message}</label>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn" lay-filter="*" onclick="addregister()">立即注册</button>
                                <%--<button type="button" id="register" class="layui-btn">立即注册</button>--%>
                            </div>
                            <div class="layui-form-item fly-form-app">
                                <span>或者直接使用社交账号快捷注册</span>
                                <a href="" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})"
                                   class="iconfont icon-qq" title="QQ登入"></a>
                                <a href="" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})"
                                   class="iconfont icon-weibo" title="微博登入"></a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/footer.jsp"/>

<script>

    function addregister() {
        var RegisterForum = $("#addregister");
        RegisterForum.submit();
    }
    //        //注册请求
    //        $("#register").on('click', function () {
    //
    //            var params = $('#addregister').serialize();
    //            $.ajax({
    //                type: "post",
    //                url: "/Register",
    //                cache: false,
    //                data: params,
    //                datatype: "json",
    //                success: function (data) {
    //                    layui.use('layer', function () {
    //                        var layer = layui.layer;
    //                        layer.msg("注册成功！");
    //                    });
    //                    window.location.reload();
    //                },
    //                error: function () {
    //                    layui.use('layer', function () {
    //                        var layer = layui.layer;
    //                        layer.msg("注册失败！");
    //                    });
    //                }
    //            });
    //        });
    function freshImg() {
        var obj = document.getElementById("randomImg");
        obj.style.display = "none";
        obj.src = "/VerifyImg";
        setTimeout(function () {
            obj.style.display = "";
            theform.verifyCode.focus();
        }, 200);
    }


</script>
</body>
</html>
