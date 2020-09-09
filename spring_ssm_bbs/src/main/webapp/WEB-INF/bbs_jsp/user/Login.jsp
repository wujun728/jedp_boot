<%--
  Created by IntelliJ IDEA.
  User: dyl
  Date: 2018/12/25
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登入</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/resource/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bbs_resourse/css/global.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/header.jsp"/>
<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20="">
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title " style="right: 0;">
                <li class="layui-this">登入</li>
                <li><a href="/city/reg"  >注册</a></li>
            </ul>
            <div class="layui-form layui-tab-content" id="Loginform" style="padding: 35px 0;">
                <label style="color:#c00;";>${message}</label>
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form method="post" action="user/login" name="theform">
                            <div class="layui-form-item">
                                <label for="Loginname" class="layui-form-label">账号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="Loginname" name="loginname" required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="password" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="password" name="password" required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_vercode" class="layui-form-label">验证码</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_vercode" name="vercode" required lay-verify="required"
                                           placeholder="请回答后面的问题" autocomplete="off" class="layui-input">
                                </div>
                                <div  style="float:left;display: block;padding: 1px 0!important;line-height: 20px;margin-right: 10px">
                                    <span style="color: #c00;">
                                        <img id="randomImg" style="padding: inherit"
                                             onclick="freshImg()" src="/user/VerifyImg">
                                    </span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn" lay-filter="*" lay-submit>立即登录</button>
                                <span style="padding-left:20px;">
                                    <a href="forget.html">忘记密码？</a>
                                </span>
                            </div>
                            <%--<div class="layui-form-item fly-form-app">--%>
                                <%--<span>或者使用社交账号登入</span>--%>
                                <%--<a href="" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})"--%>
                                   <%--class="iconfont icon-qq" title="QQ登入"></a>--%>
                                <%--<a href="" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})"--%>
                                   <%--class="iconfont icon-weibo" title="微博登入"></a>--%>
                            <%--</div>--%>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/footer.jsp"/>
</body>

<script language="javascript">
    function freshImg() {
        var obj = document.getElementById("randomImg");
        obj.style.display = "none";
        obj.src = "/user/VerifyImg";
        setTimeout(function() {
            obj.style.display = "";
            theform.verifyCode.focus();
        }, 200);
    }
</script>

</html>
