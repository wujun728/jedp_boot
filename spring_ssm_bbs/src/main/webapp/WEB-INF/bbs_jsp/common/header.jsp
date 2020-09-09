<%--
  Created by IntelliJ IDEA.
  User: dyl
  Date: 2018/12/25
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="/">
            <%--<img src="${pageContext.request.contextPath}/resource/bbs_resourse/images/logo.png">--%>
        </a>

        <ul class="layui-nav fly-nav layui-hide-xs">
            <li class="layui-nav-item layui-this">
                <a href="/city/bbs"> <i class="iconfont icon-jiaoliu"> </i>首页</a>
            </li>
            <li class="layui-nav-item">
                <a href="case/case.html" onclick="">
                    <i class="iconfont icon-iconmingxinganli"></i>待定
                </a>
            </li>
            <li class="layui-nav-item">
                <a href="http://www.layui.com/" target="_blank" onclick="">
                    <i class="iconfont icon-ui"></i>待定
                </a>
            </li>
        </ul>
        <%--未登录状态显示的标题栏--%>
        <ul class="layui-nav fly-nav-user">
            <c:choose>
                <c:when test="${empty sessionScope.session_User }">
                    <li class="layui-nav-item">
                        <a class="iconfont icon-touxiang layui-hide-xs" href="/city/login"></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="/city/login">登入</a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="/city/reg">注册</a>
                    </li>
                    <%--<li class="layui-nav-item layui-hide-xs">--%>
                    <%--<a href="/app/qq/" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" title="QQ登入" class="iconfont icon-qq"></a>--%>
                    <%--</li>--%>
                    <%--<li class="layui-nav-item layui-hide-xs">--%>
                    <%--<a href="/app/weibo/" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" title="微博登入" class="iconfont icon-weibo"></a>--%>
                    <%--</li>--%>
                </c:when>
                <c:otherwise>
                    <!-- 登入后的状态 -->
                    <li class="layui-nav-item">
                        <a class="fly-nav-avatar" href="javascript:;">
                            <cite class="layui-hide-xs">${sessionScope.session_User.username}</cite>
                            <i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：layui 作者"></i>
                            <i class="layui-badge fly-badge-vip layui-hide-xs">VIP3</i>
                            <img src="${request.getContextPath()}/getPhoto?imgUrl=${sessionScope.session_User.picPath}">
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a href="/city/userset"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
                            <dd><a href="/city/usermessage"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a>
                            </dd>
                            <dd><a href="/user/userhome"><i class="layui-icon"
                                                             style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a>
                            </dd>
                            <hr style="margin: 5px 0;">
                            <dd><a href="javascript:;" onclick="doLogout()"  style="text-align: center;">退出</a></dd>
                        </dl>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>

    </div>
</div>