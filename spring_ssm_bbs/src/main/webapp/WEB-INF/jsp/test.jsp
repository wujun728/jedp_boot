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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>欢迎来到程序员论坛交流</title>
    <script src="jquery-1.10.2.min.js"></script>
    <!-- jQuery标签库-->
    <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

    <style type="text/css">
        .control-group {
            text-align: center;
        }

        .img-responsive {
            margin-top: -10px;
        }
    </style>
    <script>
        $(".navbar navbar-default").click(function () {
            $(".container-fluid").click(function () {
                $("#Ready").click(function () {
                    $("ul")
                })
            })
        });

    </script>
</head>
<body>
<div class="jumbotron text-center" style="margin-bottom:0">
    <h1>我们的个人论坛主页页面</h1>
</div>
<!-- 导航栏-->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">属于我们的论坛</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">iOS</a></li>
                <li><a href="#">SVN</a></li>
                <li><a href="#">Bootstrap</a></li>
                <li><a href="#">jQuery</a></li>
                <li><a href="#">SQL</a></li>
                <li><a href="#">Json</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Java
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">jmeter</a></li>
                        <li><a href="#">EJB</a></li>
                        <li><a href="#">Jasper Report</a></li>
                        <li class="divider"></li>
                        <li><a href="#">分离的链接</a></li>
                        <li class="divider"></li>
                        <li><a href="#">另一个分离的链接</a></li>
                    </ul>
                </li>
            </ul>
        </div>

        <!-- 搜索框架-->
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">提交</button>
        </form>
        <div id="Ready">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                <li id="login"><a href="city/register"><span class="glyphicon glyphicon-log-in"
                                                             onclick="login()"></span> 登录</a></li>
            </ul>
        </div>
    </div>

</nav>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h2>关于我</h2>
            <h5>我的照片:</h5>
            <div class="fakeimg"></div>
            <p>我承认的我经验不够，没有那些大牛那么多的开发经验！但是我够在年轻，不懈努力的学习下去才能更强！趁着年轻学啥都快！<br/>
                谁没有经历过这个阶段！只要有一颗上进学习的心，没有啥是学不来，这是信念！</p>
            <h3>链接</h3>
            <p>描述文本。</p>
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="#">链接 1</a></li>
                <li><a href="#">链接 2</a></li>
                <li><a href="#">链接 3</a></li>
            </ul>
            <hr class="hidden-sm hidden-md hidden-lg">
        </div>
        <div class="col-sm-8">
            <h2>JAVA</h2>
            <h5>java学习之路</h5>
            <div class="fakeimg"></div>
            <p>Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。
                <br/>Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程</p>
            <p>菜鸟的学习之路，学的不仅是技术，更是梦想！！！菜鸟的学习之路，学的不仅是技术，更是梦想！！！菜鸟的学习之路，学的不仅是技术，更是梦想！！！</p>
            <br>
            <h2>SQL</h2>
            <h5>SQL的学习之路</h5>
            <div class="fakeimg"></div>
            <p>结构化查询语言(Structured Query Language)简称SQL(发音：/ˈes kjuː ˈel/
                "S-Q-L")，是一种特殊目的的编程语言，是一种数据库查询和程序设计语言，用于存取数据以及查询、更新和管理关系数据库系统；同时也是数据库脚本文件的扩展名。
                <br/>结构化查询语言是高级的非过程化编程语言，允许用户在高层数据结构上工作。它不要求用户指定对数据的存放方法，也不需要用户了解具体的数据存放方式，所以具有完全不同底层结构的不同数据库系统,
                可以使用相同的结构化查询语言作为数据输入与管理的接口。结构化查询语言语句可以嵌套，这使它具有极大的灵活性和强大的功能。
            </p>
            <p>菜鸟的学习之路，学的不仅是技术，更是梦想！！！菜鸟的学习之路，学的不仅是技术，更是梦想！！！菜鸟的学习之路，学的不仅是技术，更是梦想！！！</p>
        </div>
    </div>
</div>

<div class="jumbotron text-center" style="margin-bottom:0">
    <p>底部内容</p>
</div>

<!-- 脚部 -->
<footer>
    <div class="container">
        <div style="float: right;">
            <p>
                <a href="#">联系我们</a>
                <a href="#">公司地址</a>
                <a href="#">服务声明</a>
            </p>
        </div>
        <p>Copyrights © 2018 Bootstrap 响应式论坛网站 | 版权所有</p>
    </div>
</footer>


</body>
</html>
