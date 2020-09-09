<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zlq
  Date: 2019/1/8
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帖子主页</title>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/commonjs.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/resource/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resource/bbs_resourse/js/index.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bbs_resourse/css/global.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/header.jsp"/>


<%--标题部分--%>
<div class="fly-panel fly-column">
    <div class="layui-container">

        <ul class="layui-clear">
            <c:choose>
                <c:when test="${empty sessionScope.session_User }">
                    <li class="layui-hide-xs layui-this"><a href="javascript:void(0);" onclick="searchForum(0)">首页</a></li>
                    <li><a href="javascript:void(0);" onclick="searchForum('提问')">提问</a></li>
                    <li><a href="javascript:void(0);" onclick="searchForum('分享')">分享<span class="layui-badge-dot"></span></a></li>
                    <li><a href="javascript:void(0);" onclick="searchForum('讨论')">讨论</a></li>
                    <li><a href="javascript:void(0);" onclick="searchForum('建议')">建议</a></li>
                    <li><a href="javascript:void(0);" onclick="searchForum('公告')">公告</a></li>
                    <li><a href="javascript:void(0);" onclick="searchForum('动态')">动态</a></li>
                </c:when>
                <%--用户登入后显示--%>
                <c:otherwise>
                    <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a
                            href="user/index.html">我发表的贴</a></li>
                    <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a
                            href="user/index.html#collection">我收藏的贴</a></li>
                </c:otherwise>
            </c:choose>
        </ul>

        <div class="fly-column-right layui-hide-xs">
            <span class="fly-search"><i class="layui-icon"></i></span>
            <a href="/Forum/add" class="layui-btn">发表新帖</a>
        </div>
        <div class="layui-hide-sm layui-show-xs-block"
             style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
            <a href="/Forum/add" class="layui-btn">发表新帖</a>
        </div>
    </div>
</div>


<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="fly-panel" style="margin-bottom: 0em">

                <div class="fly-panel-title fly-filter">
                    <a href="#" class="layui-this">综合</a>
                    <span class="fly-mid"></span>
                    <a href="#">未结</a>
                    <span class="fly-mid"></span>
                    <a href="#">已结</a>
                    <span class="fly-mid"></span>
                    <a href="#">精华</a>
                    <span class="fly-filter-right layui-hide-xs">
                    <a href="" class="layui-this">按最新</a>
                    <span class="fly-mid"></span>
                    <a href="">按热议</a>
                    </span>
                </div>

                <c:choose>
                    <c:when test="${ empty sessionScope.Forum.pagelist}">
                        <div class="fly-none">没有相关数据</div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${Forum.pagelist}" var="item" varStatus="vs">
                        <ul class="fly-list">
                            <li>
                                <a href="/city/userhome" class="fly-avatar">
                                    <img src="${request.getContextPath()}/getPhoto?imgUrl=${sessionScope.session_User.picPath}" alt="">
                                </a>
                                <h2>
                                    <a class="layui-badge">${item.bbstype}</a>
                                    <a href="javascript:void(0);" onclick="ForumDetaile(${item.forumid})">${item.title}</a>
                                </h2>
                                <div class="fly-list-info">
                                    <a href="/city/userhome">
                                        <cite>${item.author}</cite>
                                    </a>
                                    <span> ${item.makeTime}</span>

                                    <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻">
                                        <i class="iconfont icon-kiss"></i>${item.reward}
                                    </span>
                                    <span class="layui-badge fly-badge-accept layui-hide-xs">${item.status==0?'未结':'已结'}</span>

                                    <span class="fly-list-nums">
                                    <i class="iconfont icon-pinglun1" title="回答"></i> ${item.replyTimes}
                                    </span>
                                </div>
                                <div class="fly-list-badge">
                                    <!--<span class="layui-badge layui-bg-red">精帖</span>-->
                                </div>
                            </li>
                        </ul>
                        </c:forEach>
                        <div style="text-align: center">
                            <div class="laypage-main">
                                <span class="laypage-curr">1</span>
                                <a href="javascript:void(0)" onclick="ForumPage(${Forum.pageNo-1})">上一页</a>
                                <span>…</span>
                                <a href="javascript:void(0)" class="laypage-last" onclick="ForumPage(${Forum.totalPages})"
                                   title="尾页">尾页</a>
                                <a href="javascript:void(0)" onclick="ForumPage(${Forum.pageNo+1})"
                                   class="laypage-next">下一页</a>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="layui-col-md4">
            <dl class="fly-panel fly-list-one">
                <dt class="fly-panel-title">本周热议</dt>
                <c:choose>
                    <c:when test="${empty sessionScope.Forum.pagelist }">
                        <!-- 无数据时 -->
                        <div class="fly-none">
                             没有相关数据
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${Forum.pagelist}" var="Hot" varStatus="vs" begin="0" end="15">
                        <dd>
                            <a href="javascript:void(0);" onclick="HotForum(${Hot.forumid})">${Hot.title}</a>
                            <span><i class="iconfont icon-pinglun1"></i> ${Hot.replyTimes}</span>
                        </dd>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </dl>
            <shiro:guest >
            <div class="fly-panel">
                <div class="fly-panel-title">
                    这里可作为广告区域
                </div>
                <div class="fly-panel-main">
                    <a href="" target="_blank" class="fly-zanzhu" style="background-color: #393D49;">虚席以待</a>
                </div>
            </div>
            </shiro:guest>
            <div class="fly-panel fly-link">
                <h3 class="fly-panel-title">友情链接</h3>
                <dl class="fly-panel-main">
                    <dd><a href="http://www.layui.com/" target="_blank">layui</a><dd>
                    <dd><a href="http://layim.layui.com/" target="_blank">WebIM</a><dd>
                    <dd><a href="http://layer.layui.com/" target="_blank">layer</a><dd>
                    <dd><a href="http://www.layui.com/laydate/" target="_blank">layDate</a><dd>
                    <dd><a href="mailto:xianxin@layui-inc.com?subject=%E7%94%B3%E8%AF%B7Fly%E7%A4%BE%E5%8C%BA%E5%8F%8B%E9%93%BE" class="fly-link">申请友链</a><dd>
                </dl>
            </div>
        </div>


    </div>
</div>


<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/footer.jsp"/>
</body>
<script>

    //获取帖子分页数据
    function ForumPage(pageNo) {
        $.ajax({
            url: "/Forum/getForum",
            type: "post",
            data:{pageNo: pageNo},
            datatype:"json",
            success:function (data) {
                window.location.reload();
            },error:function (data) {

            }

        })


    }




    //筛选帖子的类型
    function searchForum(ForumType){
        var Type = ForumType;

        if (Type==0) {
            window.location.reload();
        }else {
            $.ajax({
                url: "/Forum/searchForum",
                type: "post",
                DataType: "json",
                data: {
                    params: ForumType
                },
                success:function () {
                    window.location.reload();
                },
                error:function () {

                }
            });
        }
    }
</script>
</html>
