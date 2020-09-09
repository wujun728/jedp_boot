<%--
  Created by IntelliJ IDEA.
  User: dyl
  Date: 2018/12/27
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/resource/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resource/bbs_resourse/js/index.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resource/bbs_resourse/js/face.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bbs_resourse/css/global.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/header.jsp"/>
<div class="fly-home fly-panel" style="background-image: url();">
    <img src="${request.getContextPath()}/getPhoto?imgUrl=${sessionScope.session_User.picPath}" alt="${requestScope.personal.username}">
    <i class="iconfont icon-renzheng" title="社区认证用户"></i>
    <h1>
        ${requestScope.personal.username}
        <c:if test="${requestScope.personal.sex=='男'}">
            <i class="iconfont icon-nan"></i>
        </c:if>
        <c:if test="${requestScope.personal.sex=='女'}">
            <!--否则显示女 -->
            <i class="iconfont icon-nv"></i>
        </c:if>
        <i class="layui-badge fly-badge-vip">VIP3</i>

        <c:if test="${requestScope.personal.roleid=='gly'}">
            <span style="color:#c00;">（管理员）</span>
        </c:if>
        <!--
        <span style="color:#5FB878;">（社区之光）</span>
        <span>（该号已被封）</span>
        -->
    </h1>

    <p style="padding: 10px 0; color:#5FB878;">认证信息：社区论坛 作者</p>

    <p class="fly-home-info">
        <i class="iconfont icon-kiss" title="飞吻"></i><span style="color:#FF7200;">120 飞吻</span>
        <i class="iconfont icon-shijian"></i><span>${requestScope.personal.joindate} 加入</span>
        <i class="iconfont icon-chengshi"></i><span>来自${requestScope.personal.place}</span>
    </p>

    <c:choose>
        <c:when test="${empty requestScope.personal.signature}">
            <p class="fly-home-sign">这家伙很懒，什么也没写</p>
        </c:when>
        <c:otherwise>
            <p class="fly-home-sign">${requestScope.personal.signature}</p>
        </c:otherwise>
    </c:choose>

    <div class="fly-sns" data-user="">
        <a href="javascript:void(0);" class="layui-btn layui-btn-primary fly-imActive" data-type="addFriend">加为好友</a>
        <a href="javascript:void(0);" class="layui-btn layui-btn-normal fly-imActive" data-type="chat">发起会话</a>
    </div>

</div>

<div class="layui-container">
    <div class="layui-row layui-col-space5">
        <div class="layui-col-md6  fly-home-jie">
                <div class="fly-panel">
                    <h3 class="fly-panel-title">${requestScope.personal.username} 最近的提问</h3>

                    <ul class="jie-row">
                        <c:forEach items="${requestScope.Forum}" var="Forum" varStatus="vs">
                        <li>
                            <c:if test="${Forum.delicate_status==1}">
                                <span class="fly-jing">精</span>
                            </c:if>
                            <a href="#" class="jie-title">${Forum.title}</a>
                            <i>${Forum.make_date}</i>
                            <em class="layui-hide-xs">${Forum.read_times}阅/${Forum.reply_times}答</em>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
        </div>
        <div class="layui-col-md6 fly-home-da">
            <div class="fly-panel">
                <h3 class="fly-panel-title">${requestScope.personal.username} 最近的回答</h3>
                <ul class="home-jieda">

                    <c:choose>
                        <c:when test="${empty requestScope.answered}">
                            <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;">
                                <span>没有回答任何问题</span></div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${requestScope.answered}" var="answered" varStatus="vs">
                                <li>
                                    <p>
                                        <span>${answered.make_time}</span>
                                        在<<a href="#" target="_blank">${answered.title}</a>中回答：
                                    </p>
                                    <div class="home-dacontent">
                                            ${answered.content}
                                        <pre>
                                                <%--full:true--%>
                                        </pre>
                                            <%--文档没有提及--%>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>

    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/footer.jsp"/>
</body>
</html>
