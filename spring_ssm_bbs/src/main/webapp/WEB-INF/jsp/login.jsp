<%--
  Created by IntelliJ IDEA.
  User: 张林强
  Date: 2018/07/19
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 导入样式-->
    <link href="${pageContext.request.contextPath}/resource/jspCSS/login.css" rel="stylesheet" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">

    <title>欢迎来到程序员论坛交流</title>
    <%--<script src="jquery-1.10.2.min.js"></script>--%>
    <!-- jQuery标签库-->
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <style type="text/css" href="${pageContext.request.contextPath}/resource/jspCSS/login.css">

        body {
            background: #93defe;
            background-size: cover;
        }

        ul li {
            float: left;
            list-style: none;
            margin: 0 30px;
        }

    </style>

    <script>
        function checke() {
            var loginname = $("#loginname").val();
            var loginpass = $("#loginpass").val();
            if (loginname == "" || loginpass == "") {
                alert("用户名或密码不能为空");
                return false;
            } else {
                return true;
            }
        }

        function submit() {
            if (checke()) {
                var myform = $("#myfrom");
                myform.submit();
            }
        }

    </script>

</head>
<body>
<!-- 导航条 -->
<%--<nav class="navbar navbar-default" role="navigation">--%>
<%--<div class="container-fluid">--%>
<%--<div class="navbar-header">--%>
<%--<a class="navbar-brand" href="#">属于我们的论坛</a>--%>
<%--</div>--%>
<%--<div>--%>
<%--<ul class="nav navbar-nav">--%>
<%--<li class="active"><a href="#">iOS</a></li>--%>
<%--<li><a href="#">SVN</a></li>--%>
<%--<li><a href="#">Bootstrap</a></li>--%>
<%--<li><a href="#">jQuery</a></li>--%>
<%--<li><a href="#">SQL</a></li>--%>
<%--<li><a href="#">Json</a></li>--%>
<%--<li class="dropdown">--%>
<%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">--%>
<%--Java--%>
<%--<b class="caret"></b>--%>
<%--</a>--%>
<%--<ul class="dropdown-menu">--%>
<%--<li><a href="#">jmeter</a></li>--%>
<%--<li><a href="#">EJB</a></li>--%>
<%--<li><a href="#">Jasper Report</a></li>--%>
<%--<li class="divider"></li>--%>
<%--<li><a href="#">分离的链接</a></li>--%>
<%--<li class="divider"></li>--%>
<%--<li><a href="#">另一个分离的链接</a></li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</div>--%>

<%--<!-- 搜索框架-->--%>
<%--<form class="navbar-form navbar-left" role="search">--%>
<%--<div class="form-group">--%>
<%--<input type="text" class="form-control" placeholder="Search">--%>
<%--</div>--%>
<%--<button type="submit" class="btn btn-default">提交</button>--%>
<%--</form>--%>
<%--<div id="Ready">--%>
<%--<ul class="nav navbar-nav navbar-right">--%>
<%--<li><a href="#"><span class="glyphicon glyphicon-user"></span> 注册</a></li>--%>
<%--<li id="login"><a href="#"><span class="glyphicon glyphicon-log-in" onclick="login()"></span> 登录</a></li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>

<%--</nav>--%>
<div class="login_box">
    <div class="login_l_img"><img src="${pageContext.request.contextPath}/resource/images/login-img.png"/></div>
    <div class="login">
        <div class="login_logo"><a href="#"><img
                src="${pageContext.request.contextPath}/resource/images/login_logo.png"/></a></div>
        <div class="login_name">
            <p>用户登录</p>
        </div>
        <%--<form method="post" action="/city/register"id="myfrom">--%>
        <%--<input  id="loginname" name="username" type="text"  value="用户名"  onfocus="this.value=''" onblur="if(this.value==''){this.value='用户名'}" >--%>
        <%--<span id="password_text" onclick="this.style.display='none';document.getElementById('password').style.display='block';document.getElementById('password').focus().select();" >密码</span>--%>
        <%--<input id="longinpass" name="password" type="password"  style="display:none;"  onblur="if(this.value==''){document.getElementById('password_text').style.display='block';this.style.display='none'};"/>--%>
        <%--&lt;%&ndash;<input  id="longinpass" name="password" type="password"  value="密码"  onfocus="this.value=''" onblur="if(this.value==''){this.value='密码'}" >&ndash;%&gt;--%>
        <%--<input value="登录" style="width:100%;" type="submit"  onclick="checke()">--%>
        <%--</form>--%>
        <form action="/user/login" method="post">
            <input id="loginname" type="text" name="loginname" class="name" placeholder="用户名" required="">
            <input id="longinpass" type="password" name="password" class="password" placeholder="密码" required="">
            <ul class=”box“>
                <li>
                    <input type="checkbox" id="brand1" value="">
                    <label for="brand1"><span></span>记住密码</label>
                </li>
                <li>
                    <a href="#">忘记密码?</a><br>
                    <div class="clear"></div>
                </li>
            </ul>
            <label>${error_msg}</label>
            <input type="submit" value="登录" onclick="checke()">
        </form>
    </div>
    <%--<div class="copyright">某某有限公司 版权所有©2016-2018 技术支持电话：000-00000000</div>--%>
    <div>
        <!-- 脚部 -->
        <footer class="fly-footer">
            <div class="copyright">
                <div style="float: right;">
                    <p>
                        <a href="#">联系我们</a>
                        <a href="#">公司地址</a>
                        <a href="#">服务声明</a>
                    </p>
                </div>
                <p>Copyrights © 2018 Bootstrap 响应式论坛网站 | 版权所有@695629423qq.com</p>
            </div>
        </footer>
    </div>
</div>


</body>

</html>
