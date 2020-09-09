<%--
  Created by IntelliJ IDEA.
  User: dyl
  Date: 2018/12/25
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%--标题部分--%>
<div class="fly-panel fly-column">
    <div class="layui-container">

        <ul class="layui-clear">
            <c:choose>
                <c:when test="${empty sessionScope.session_User }">
                    <li class="layui-hide-xs layui-this"><a href="/">首页</a></li>
                    <li><a href="jie/index.html">提问</a></li>
                    <li><a href="jie/index.html">分享<span class="layui-badge-dot"></span></a></li>
                    <li><a href="jie/index.html">讨论</a></li>
                    <li><a href="jie/index.html">建议</a></li>
                    <li><a href="jie/index.html">公告</a></li>
                    <li><a href="jie/index.html">动态</a></li>
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
            <a href="jie/add.html" class="layui-btn">发表新帖</a>
        </div>
        <div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
            <a href="jie/add.html" class="layui-btn">发表新帖</a>
        </div>
    </div>
</div>
