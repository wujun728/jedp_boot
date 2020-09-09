<%--
  Created by IntelliJ IDEA.
  User: zlq
  Date: 2019/1/8
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>帖子详情</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.js"></script>
    <%--<script type="text/javascript" src="http://malsup.github.io/jquery.form.js"></script>--%>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/commonjs.jsp"/>
    <script src="${pageContext.request.contextPath}/resource/bbs_resourse/js/index.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resource/bbs_resourse/js/jie.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resource/bbs_resourse/js/face.js" charset="utf-8"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/header.jsp"/>
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
                            href="user/index.html">我收藏的贴</a></li>
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
        <div class="layui-col-md8 content detail">
            <div class="fly-panel detail-box">
                <h1 id="forumtitle">
                    ${requestScope.userForum.title}
                </h1>
                <div class="fly-detail-info">
                    <%--<span class="layui-badge">审核中</span>--%>
                    <span class="layui-badge layui-bg-green fly-detail-column">${requestScope.userForum.bbsType}</span>
                    <c:choose>
                        <c:when test="${requestScope.userForum.status==0}">
                            <span class="layui-badge" style="background-color: #999;">未结</span>
                        </c:when>
                        <c:otherwise>
                            <span class="layui-badge" style="background-color: #5FB878">已结</span>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${requestScope.userForum.top_status==1}">
                        <span class="layui-badge layui-bg-black"> 置顶</span>
                    </c:if>
                    <c:if test="${requestScope.userForum.delicate_status==1}">
                        <span class="layui-badge layui-bg-red">精贴</span>
                    </c:if>

                    <c:choose>
                        <c:when test="${empty sessionScope.session_User}">
                            <div class="fly-admin-box" data-id="123"></div>
                        </c:when>
                        <c:otherwise>
                            <div class="fly-admin-box" data-id="123">
                                <c:if test="${sessionScope.session_User.loginname==requestScope.userForum.author||sessionScope.session_User.roleid=='gly'}">
                                    <span class="layui-btn layui-btn-xs jie-admin"
                                          onclick="deleteForum(${requestScope.userForum.forumid})">删除</span>
                                </c:if>

                                <c:if test="${sessionScope.session_User.roleid=='gly'}">
                                <span class="layui-btn layui-btn-xs jie-admin" field="stick"
                                      rank="1" onclick="top_forum(${requestScope.userForum.forumid},1)">置顶</span>
                                    <span class="layui-btn layui-btn-xs jie-admin" field="stick"
                                          rank="1" onclick="top_forum(${requestScope.userForum.forumid},0)">取消置顶</span>

                                    <span class="layui-btn layui-btn-xs jie-admin" field="stick"
                                          rank="1"
                                          onclick="delicate_forum(${requestScope.userForum.forumid},1)">加精</span>
                                    <span class="layui-btn layui-btn-xs jie-admin" field="stick"
                                          rank="1"
                                          onclick="delicate_forum(${requestScope.userForum.forumid},0)">取消加精</span>
                                </c:if>

                            </div>
                        </c:otherwise>
                    </c:choose>
                    <span class="fly-list-nums">
                            <a href="#comment"><i class="iconfont"
                                                  title="回答"> &#xe60c;</i> ${requestScope.userForum.replyTimes}</a>
                            <i class="iconfont" title="人气">&#xe60b;</i>${requestScope.userForum.readTimes}
                        </span>
                </div>
                <div class="detail-about">
                    <a class="fly-avatar" href="../user/home.html">
                        <img src="${request.getContextPath()}/getPhoto?imgUrl=${sessionScope.session_User.picPath}"
                             alt=""/>
                    </a>
                    <div class="fly-detail-user">
                        <a href="../user/home.html" class="fly-link">
                            <cite>${requestScope.userForum.author}</cite>
                            <i class="iconfont icon-renzheng" title="认证信息：${requestScope.userForum.author}"></i>
                            <i class="layui-badge fly-badge-vip">VIP3</i>
                        </a>
                        <span>${requestScope.userForum.makeDate}</span>
                    </div>
                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                        <span style="padding-right: 10px; color: #FF7200">悬赏飞吻:${requestScope.userForum.reward}</span>
                        <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="/Forum/add">编辑此贴</a></span>
                    </div>
                    <div class="detail-body photos">
                        <p>
                            ${requestScope.userForum.content}
                        </p>
                        <p> <!-- 更新日志：--></p>
                        <%--<pre>--%>
                        <%--# v3.0 2017-11-30--%>
                        <%--* 采用 layui 2.2.3 作为 UI 支撑--%>
                        <%--* 全面同步最新的 Fly 社区风格，各种细节得到大幅优化--%>
                        <%--* 更友好的响应式适配能力--%>
                        <%--${requestScope.userForum.content}--%>
                        <%--</pre>--%>
                        <%--下载--%>
                        <hr>
                        <p>
                            <%--官网：<a href="http://www.layui.com/template/fly/" target="_blank">http://www.layui.com/template/fly/</a><br>--%>
                            <%--码云：<a href="https://gitee.com/sentsin/fly/"--%>
                            <%--target="_blank">https://gitee.com/sentsin/fly/</a><br>--%>
                            <%--GitHub：<a href="https://github.com/layui/fly"--%>
                            <%--target="_blank">https://github.com/layui/fly</a>--%>
                        </p>
                        <%--封面--%>
                        <hr>
                        <p>
                            <%--<img src="${pageContext.request.contextPath}/resource/bbs_resourse/images/fly.jpg"--%>
                            <%--alt="Fly社区">--%>
                        </p>
                    </div>
                </div>

                <div class="fly-panel detail-box" id="flyReply">
                    <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                        <legend>回帖</legend>
                    </fieldset>


                    <ul class="jieda" id="jieda">
                        <c:forEach items="${requestScope.replyForum}" var="reply" varStatus="vs">
                            <li date-id="${reply.id}" class="jieda-daan">
                                <a name="item-111111"></a>
                                <div class="detail-about detail-about-reply">
                                    <a class="fly-avatar" href="/city/bbs">
                                        <img src="${request.getContextPath()}/getPhoto?imgUrl=${sessionScope.session_User.picPath}"/>
                                    </a>
                                    <div class="fly-detail-user">
                                        <a href="" class="fly-link">
                                            <cite>${reply.replyauthor}</cite>
                                            <i class="iconfont icon-renzheng"
                                               title="认证信息：${reply.replyauthor}"></i>
                                                <%--<i class="layui-badge fly-badge-vip">VIP3</i>--%>
                                        </a>

                                            <%--<span>(楼主)</span>--%>
                                        <!--
                                         <span style="color:#5FB878">(管理员)</span>
                                         <span style="color:#FF9E3F">（社区之光）</span>
                                          <span style="color:#999">（该号已被封）</span>
                                             -->
                                    </div>

                                    <div class="detail-hits">
                                        <span>${reply.makeDate}</span>
                                    </div>

                                    <c:if test="${reply.status==1}">
                                        <i class="iconfont icon-caina" title="最佳答案"></i>
                                    </c:if>

                                </div>

                                <div class="detail-body jieda-body photos">
                                    <p>${reply.content}</p>
                                </div>
                                <div class="jieda-reply">
                                <span class="jieda-zan " type="zan">
                                    <i class="iconfont icon-zan"></i>
                                    <em>${reply.supportTimes}</em>
                                </span>
                                    <span type="reply">
                                    <i class="iconfont icon-svgmoban53"></i>
                                    回复
                                </span>
                                    <div class="jieda-admin">
                                        <span onclick="">编辑</span>
                                            <%--type="edit"--%>
                                            <%--type="del"--%>
                                        <span onclick="DeleteReply(${reply.id})">删除</span>

                                        <c:if test="${reply.status!=1}">
                                            <%--type="accept"--%>
                                            <span class="jieda-accept" onclick="AcceptReply(${reply.id},${reply.forumid})">采纳</span>
                                        </c:if>

                                    </div>
                                </div>
                            </li>

                            <!-- 无数据时 -->
                            <!-- <li class="fly-none">消灭零回复</li> -->
                        </c:forEach>
                    </ul>


                    <div class="layui-form layui-form-pane">
                        <form id="replyForum">
                            <div class="layui-form-item layui-form-text">
                                <a name="comment"></a>
                                <div class="layui-input-block">
                                        <textarea id="L_content" name="content" required lay-verify="required"
                                                  placeholder="请输入内容" class="layui-textarea fly-editor"
                                                  style="height: 150px;"></textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <input type="hidden" id="author" name="author" value="${requestScope.userForum.author}">
                                <input type="hidden" id="forumid" name="forumid"
                                       value="${requestScope.userForum.forumid}">
                                <input type="hidden" id="title" name="title" value="${requestScope.userForum.title}">
                                <input type="hidden" id="bbsType" name="bbsType"
                                       value="${requestScope.userForum.bbsType}">
                                <input type="hidden" id="replyauthor" name="replyauthor"
                                       value="${sessionScope.session_User.username}">
                                <button type="button" id="tijiao" class="layui-btn">提交回复</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>

        <div class="layui-col-md4">
            <dl class="fly-panel fly-list-one">
                <dt class="fly-panel-title">本周热议</dt>
                <c:choose>
                    <c:when test="${empty sessionScope.Forum }">
                        <!-- 无数据时 -->
                        <div class="fly-none">没有相关数据
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${Forum.pagelist}" var="Hot" varStatus="vs">
                            <dd>
                                <a href="/ForumDetail">${Hot.title}</a>
                                <span><i class="iconfont icon-pinglun1"></i> ${Hot.replyTimes}</span>
                            </dd>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </dl>

            <div class="fly-panel">
                <div class="fly-panel-title">
                    这里可作为广告区域
                </div>
                <div class="fly-panel-main">
                    <a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu"
                       time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">基于 - layui 社区论坛</a>
                </div>
            </div>

            <div class="fly-panel" style="padding: 20px 0; text-align: center;">
                <%--<img src="${pageContext.request.contextPath}/resource/bbs_resourse/images/Weixin.jpg"--%>
                     <%--style="max-width: 100%;" alt="layui">--%>
                <p style="position: relative; color: #666;">微信扫码 加 微信号</p>
            </div>
        </div>

    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/footer.jsp"/>

<script>


    function deleteForum(forumid) {
        var ForumId = forumid;
//        console.log(ForumId);

        layui.use('layer', function () {

            var layer = layui.layer;
            //删帖操作
            layer.confirm('你确定要删除此贴嘛？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.ajax({
                    type: "post",
                    url: "/Forum/deleteForum",
                    datatype: "json",
                    data: {
                        ForumId: ForumId
                    },
                    success: function (data) {
                        layer.msg('已删除');
                        var time = 2;
                        var interval = setInterval(function () {
                            time = --time;
//                            console.log(time);
                            if (time <= 0) {
                                $.ajax({
                                    type: "get",
                                    url: "/Forum/getForum",
                                    datatype: "json",
                                    success: function () {
                                        window.location.href = '/city/bbs';
                                    }
                                });
                                clearInterval(interval);
                            }
                            ;

                        }, 1000);
                    },

                });

            }, function () {
                layer.msg('已取消');
            });
        });

    };


    function top_forum(ForumId, status) {
//            console.log(ForumId, status);
        layui.use('layer', function () {

            //置顶操作
            var layer = layui.layer;
            layer.confirm('你确定要置顶此贴嘛？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.ajax({
                    type: "post",
                    url: "/Forum/topForum",
                    datatype: "json",
                    data: {
                        ForumId: ForumId,
                        status: status
                    },
                    success: function () {
                        layer.msg("已置顶！")
                        $.ajax({
                            type: "get",
                            url: "/Forum/ForumDetail",
                            datatype: "json",
                            data: {
                                Forumid: ForumId
                            },
                            success: function (data) {
                                window.location.reload();
//                                    console.log(data);
                            }
                        });
                    }
                })
            }, function () {
                layer.msg("已取消！");
            });

        })
    }


    function delicate_forum(ForumId, status) {
        console.log(ForumId, status);

        layui.use('layer', function () {

            //加精操作
            var layer = layui.layer;
            layer.confirm('你确定要加精此贴嘛？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.ajax({
                    type: "post",
                    url: "/Forum/delicateForum",
                    datatype: "json",
                    data: {
                        ForumId: ForumId,
                        status: status
                    },
                    success: function () {
                        layer.msg("已加精！")
                        $.ajax({
                            type: "get",
                            url: "/Forum/ForumDetail",
                            datatype: "json",
                            data: {
                                Forumid: ForumId
                            },
                            success: function (data) {
                                window.location.reload();
//                                    console.log(data);
                            }
                        });
                    }
                });
            }, function () {
                layer.msg("已取消！");
            });

        })
    }

    //回帖请求
    $("#tijiao").on('click', function () {

        var replyauthor = $("#replyauthor").val();
        if (replyauthor == "") {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg("请登入后评论！");
            });
            console.log("asasa");
            return false;
        }

        var params = $('#replyForum').serialize();
        $.ajax({
            type: "post",
            url: "/Forum/replyForum",
            cache: false,
            data: params,
            datatype: "json",
            success: function (data) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg("回复成功！");
                });
                window.location.reload();
            },
            error: function () {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg("回复失败！");
                });
            }
        });


    });

    //    采纳请求
    function AcceptReply(id, forumid) {

        var replyauthor = $("#replyauthor").val();
        if (replyauthor == "") {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg("请登入后操作！");
            });
            console.log("asasa");
            return false;
        }

        var id = id;
        var Forumid = forumid;
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.confirm('你确定要采纳这个答案嘛？</br>采纳后，将自动结贴！', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.ajax({
                    type: "post",
                    url: "/Forum/AcceptReply",
                    datatype: "json",
                    data: {
                        id: id,
                        Forumid:Forumid
                    },
                    success: function () {
                        layer.msg("已采纳！");
                        window.location.reload();
                    }
                });
            }, function () {
                layer.msg("已取消！");
            });
        })

    }

    //    删除回复请求
    function DeleteReply(id, li) {

        var replyauthor = $("#replyauthor").val();
        if (replyauthor == "") {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg("请登入后操作！");
            });
            console.log("asasa");
            return false;
        }

        var id = id;
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.confirm("你确定要删除这条回复吗？", {
                    btn: ['确定', '取消']
                }, function () {
                    $.ajax({
                        type: "post",
                        url: "/Forum/DeleteReply",
                        datetype: "json",
                        data: {
                            id: id
                        },
                        success: function () {
                            layer.msg("删除成功！");
                            window.location.reload();
                        },
                        error: function () {
                            layer.msg("删除失败！");
                        }
                    })
                }
                , function () {
                    layer.msg("已取消！");
                }
            )
        })
    }
</script>
</body>
</html>
