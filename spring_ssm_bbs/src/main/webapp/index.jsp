<%--
  Created by IntelliJ IDEA.
  User: 张林强
  Date: 2018/07/19
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>欢迎来到程序员论坛交流</title>
    <script src="jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/login.js"></script>
    <link rel="stylesheet"  href="${pageContext.request.contextPath }/resource/css/bootstrap-theme.css">
    <link rel="stylesheet"  href="${pageContext.request.contextPath }/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath }/resource/css/style.css"/>
    <style type="text/css">
        .control-group{
            text-align: center;
        }
        .img-responsive{
            margin-top:-10px;
        }
    </style>
</head>
<body>
<!-- 导航条 -->
<nav class="navbar navbar-default navbar-fixed-top navbar-right" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">1</span>
                <span class="icon-bar">2</span>
                <span class="icon-bar">3</span>
                <span class="icon-bar">4</span>
            </button>
            <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath }/resource/img/logo.jpg" class="img-responsive" alt=""></a><span style="line-height: 50px;">程序改变世界</span>
            <!-- 搜索框-->
            <!-- <form class="navbar-form navbar-right" action="" role="search">
              <div class="form-group">
                  <input type="text" class="form-control" placeholder="输入要查询的内容">
              </div>
              <button type="submit" class="btn btn-default">提交</button>
            </form> -->
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <!-- 导航栏 -->
            <ul class="nav navbar-nav navbar-right">
                <li class="login-header"><a href="javascript:void(0);"><span class="glyphicon glyphicon-home"></span>登陆</a></li>
                <li><a href="${pageContext.request.contextPath }/user/registerpage.do"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
            </ul>
        </div>
    </div>
</nav>


<h2>Hello World!</h2>
</body>
</html>
