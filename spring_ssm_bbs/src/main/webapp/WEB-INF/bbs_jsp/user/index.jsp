<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dyl
  Date: 2018/12/29
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户中心</title>
    <%--//引入layui组件--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/resource/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resource/bbs_resourse/js/index.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bbs_resourse/css/global.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/header.jsp"/>
<div class="layui-container fly-marginTop  fly-user-main">
    <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
        <li class="layui-nav-item">
            <a href="/user/userhome">
                <i class="layui-icon">&#xe609;</i>我的主页
            </a>
        </li>
        <li class="layui-nav-item">
            <a onclick="personalForum(${sessionScope.session_User.id})" href="javascript:void(0)">
                <i class="layui-icon">&#xe612;</i>用户中心
            </a>
        </li>
        <li class="layui-nav-item">
            <a href="/user/Set">
                <i class="layui-icon">&#xe620;</i>基本设置
            </a>
        </li>
        <li class="layui-nav-item">
            <a href="/user/message">
                <i class="layui-icon">&#xe611;</i>我的消息
            </a>
        </li>
    </ul>

    <div class="site-tree-mobile layui-hide">
        <i class="layui-icon">&#xe602;</i>
    </div>
    <div class="site-mobile-shade"></div>

    <div class="site-tree-mobile layui-hide">
        <i class="layui-icon">&#xe602;</i>
    </div>
    <div class="site-mobile-shade"></div>

    <div class="fly-panel fly-panel-user" pad20="">
        <!--
    <div class="fly-msg" style="margin-top: 15px;">
      您的邮箱尚未验证，这比较影响您的帐号安全，<a href="activate.html">立即去激活？</a>
    </div>
            -->
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title " id="LAY_mine">
                <li data-type="mine-jie" lay-id="index" class="layui-this">我发的贴(<span>89</span>)</li>
                <li data-type="collection" data-url="/collection/find/" lay-id="collection">我收藏的帖（<span>16</span>）</li>
            </ul>
            <div class="layui-tab-content" style="padding: 20px  0;">
                <div class="layui-tab-item layui-show">
                    <c:forEach items="${requestScope.userForum}" var="userForum" varStatus="vs">
                    <ul class="mine-view jie-row">
                        <li>
                            <a class="jie-title" href="javascript:void(0);" onclick="ForumDetaile(${userForum.forumid})"target="_blank">${userForum.title}</a>
                            <i>${userForum.makeTime}</i>
                            <a class="mine-edit" href="/jie/edit/8116">编辑</a>
                            <em>${userForum.readTimes}阅/${userForum.replyTimes}答</em>
                        </li>
                    </ul>
                    </c:forEach>
                    <div id="LAY_page"></div>
                </div>
                <div class="layui-tab-item">
                    <ul class="mine-view jie-row">
                        <li>
                            <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
                            <i>收藏于23小时前</i>  </li>
                    </ul>
                    <div id="LAY_page1"></div>
                </div>

            </div>
        </div>
    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/footer.jsp"/>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/commonScript.jsp"/>
</html>
