<%--
  Created by IntelliJ IDEA.
  User: dyl
  Date: 2018/12/24
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<% request.setAttribute("context", request.getContextPath()); %>--%>
<html>
<head>
    <title>程序员成长社区</title>
    <%--//引入layui组件--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/resource/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resource/bbs_resourse/js/index.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resource/bbs_resourse/js/face.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bbs_resourse/css/global.css">

    <!-- jQuery标签库-->
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.messager.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery_2.1.4_jquery.js"></script>
</head>
<body>
<%--头部组件--%>
<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="/city/bbs">
            <%--<img src="${pageContext.request.contextPath}/resource/bbs_resourse/images/logo.png">--%>
        </a>

        <ul class="layui-nav fly-nav layui-hide-xs">
            <li class="layui-nav-item layui-this">
                <a href="javascript:void(0);" onclick="searchForum(0)"> <i class="iconfont icon-jiaoliu"> </i>交友</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:void(0);" onclick="searchForum(0)">
                    <i class="iconfont icon-iconmingxinganli"></i>恋爱
                </a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:void(0);"  onclick="searchForum(0)">
                    <i class="iconfont icon-ui"></i>学习
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
                            <dd><a href="/user/Set"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
                            <dd><a href="/user/message"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a>
                            </dd>
                            <dd><a href="/user/userhome"><i class="layui-icon"
                                                             style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a>
                            </dd>
                            <hr style="margin: 5px 0;">
                            <dd><a href="javascript:;" onclick="doLogout()" style="text-align: center;">退出</a></dd>
                        </dl>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>

    </div>
</div>

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
                            href="/user/index">我发表的贴</a></li>
                    <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a
                            href="/user/index">我收藏的贴</a></li>
                </c:otherwise>
            </c:choose>
        </ul>

        <div class="fly-column-right layui-hide-xs">
            <span class="fly-search"><i class="layui-icon"></i></span>
            <a href="/Forum/add" class="layui-btn">发表新帖</a>
        </div>
        <div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
            <a href="/Forum/add" class="layui-btn">发表新帖</a>
        </div>
    </div>
</div>

<%--发帖置顶部分--%>
<div class="layui-container">
    <div class="layui-row layui-col-space5">
        <div class="layui-col-md8">
            <div class="fly-panel">
                <div class="fly-panel-title fly-filter">
                    <a>置顶</a>
                    <a href="#signin" class="layui-hide-sm layui-show-xs-block fly-right"
                       id="LAY_goSignin" style="color: #FF5722;">去签到</a>
                </div>


                <c:forEach  items="${Forum.pagelist}"  var="item"  varStatus="vs" >
                    <c:if test="${item.top_status==1}">
                <ul class="fly-list">
                    <li>
                        <a href="/user/userhome" class="fly-avatar">
                            <img src="${request.getContextPath()}/getPhoto?imgUrl=${sessionScope.session_User.picPath}"
                                 alt="贤心">
                        </a>
                        <h2>
                            <a class="layui-badge">${item.bbstype}</a>
                            <a href="javascript:void(0)" onclick="ForumDetaile('${item.forumid}')">${item.title}</a>
                        </h2>
                        <div class="fly-list-info">
                            <a href="/user/userhome" link>
                                <cite>${item.author}</cite>
                                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                <i class="layui-badge fly-badge-vip">VIP3</i>
                            </a>
                            <span>${item.makeTime}</span>

                            <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> ${item.reward}</span>
                            <span class="layui-badge fly-badge-accept layui-hide-xs">${item.status==0?'未结':'已结'}</span>
                            <span class="fly-list-nums">
                             <i class="iconfont icon-pinglun1" title="回答"></i> ${item.replyTimes}
                                <i class="iconfont" title="人气">&#xe60b;</i>${item.readTimes}
                            </span>
                        </div>
                        <div class="fly-list-badge">
                            <!--
                            <span class="layui-badge layui-bg-black">置顶</span>
                            <span class="layui-badge layui-bg-red">精帖</span>
                            -->
                        </div>
                    </li>
                </ul>
                    </c:if>
                </c:forEach>
            </div>

            <%--帖子综合部分--%>
            <div class="fly-panel" style="margin-bottom: 0;">.

                <div class="fly-panel-title fly-filter">
                    <a href="javascript:void(0);" onclick="" class="layui-this">综合</a>
                    <span class="fly-mid"></span>
                    <a href="javascript:void(0);" onclick="">未结</a>
                    <span class="fly-mid"></span>
                    <a href="javascript:void(0);" onclick="">已结</a>
                    <span class="fly-mid"></span>
                    <a href="javascript:void(0);" onclick="">精华</a>
                    <span class="fly-filter-right layui-hide-xs">
                            <a href="javascript:void(0);" onclick="" class="layui-this">按最新</a>
                            <span class="fly-mid"></span>
                            <a href="javascript:void(0);" onclick="">按热议</a>
                     </span>
                </div>
                <c:forEach  items="${Forum.pagelist}"  var="item"  varStatus="vs" begin="0" end="10">
                <ul class="fly-list">
                    <li>
                        <a href="/user/userhome" class="fly-avatar">
                            <img src="${request.getContextPath()}/getPhoto?imgUrl=${item.authorPath}"
                                 alt="林强">
                        </a>
                        <h2>
                            <a class="layui-badge">${item.bbsType}</a>
                            <a href="javascript:void(0)" onclick="ForumDetaile('${item.forumid}')">${item.title}</a>
                        </h2>
                        <div class="fly-list-info">
                            <a href="/user/userhome" link>
                                <cite>${item.author}</cite>
                                <!--
                                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                <i class="layui-badge fly-badge-vip">VIP3</i>
                                -->
                            </a>
                            <span>
                                    ${item.makeTime}
                        </span>
                            <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i
                                    class="iconfont icon-kiss"></i> ${item.reward}</span>
                            <span class="layui-badge fly-badge-accept layui-hide-xs">${item.status==0?'未结':'已结'}</span>
                            <span class="fly-list-nums">
                            <i class="iconfont icon-pinglun1" title="回答"></i> ${item.replyTimes}
                                <i class="iconfont" title="人气">&#xe60b;</i>${item.readTimes}
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
                        <a href="/Forumindex" class="laypage-next">更多求解</a>
                    </div>
                </div>

            </div>
        </div>
        <div class="layui-col-md4">

            <div class="fly-panel">
                <h3 class="fly-panel-title">
                    温馨通道
                </h3>
                <ul class="fly-panel-main   fly-list-static">
                    <li>
                        <a href="https://gitee.com/zzlq/BBS_SSm" target="_blank">论坛项目 的  Gitee (码云)
                            仓库，欢迎Star</a>
                    </li>
                    <li>
                        <a href="https://gitee.com/zzlq/BBS_SSm" target="_blank">
                            项目 常见问题的处理和实用干货集锦
                        </a>
                    </li>
                </ul>
            </div>

            <%--签到榜--%>
            <div class=" fly-panel fly-signin">
                <div class="fly-panel-title">
                    签到
                    <i class="fly-mid"></i>
                    <a href="javascript:;" class="fly-link" id="LAY_signinHelp">说明</a>
                    <i class="fly-mid"></i>
                    <a href="javascript:;" class="fly-link" id="LAY_signinTop">活跃榜<span class="layui-badge-dot"></span></a>
                    <span class="fly-signin-days">已连续签到<cite>16</cite>天</span>
                </div>
                <div class="fly-panel-main fly-signin-main">
                    <c:choose>
                        <c:when test="${empty sessionScope.session_User }">
                            <button class="layui-btn layui-btn-danger" id="LAY_signin">今日签到</button>
                            <span>可获得<cite>5</cite>飞吻</span>
                        </c:when>
                        <c:otherwise>
                            <!-- 已签到状态 -->
                            <button class="layui-btn layui-btn-disabled">今日已签到</button>
                            <span>获得了<cite>5</cite>飞吻</span>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <%--回帖周榜榜单--%>
            <div class="fly-panel fly-rank fly-rank-reply" id="LAY_replyRank">
                <h3 class="fly-panel-title">回贴周榜</h3>
                <dl>
                    <c:choose>
                        <c:when test="${empty sessionScope.Weeks }">
                            <div  style="min-height: 200px; text-align: center; padding-top:50px; color: #999;">没有相关数据</div>
                        </c:when>
                        <c:otherwise>
                            <!--<i class="layui-icon fly-loading">&#xe63d;</i>-->
                            <c:forEach items="${sessionScope.Weeks}" var="weeks" varStatus="vs" begin="0" end="9">
                            <dd>
                                <a href="javascript:void(0);" onclick="userhome(${weeks.id},'${weeks.replyauthor}')">
                                    <img src="${request.getContextPath()}/getPhoto?imgUrl=${sessionScope.session_User.picPath}"><cite>${weeks.replyauthor}</cite><i>${weeks.Times}次回答</i>
                                </a>
                            </dd>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </dl>
            </div>

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
                            <a href="javascript:void(0);" onclick="HotForum(${Hot.forumid})">${Hot.title}</a>&nbsp;&nbsp;
                            <span><i class="iconfont icon-pinglun1"></i>${Hot.replyTimes}</span>
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
                       time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">放置 - 广告 的区域</a>
                </div>
            </div>


            <div class="fly-panel fly-link">
                <h3 class="fly-panel-title">友情链接</h3>
                <dl class="fly-panel-main">
                    <dd><a href="http://www.layui.com/" target="_blank">layui</a>
                    <dd>
                    <dd><a href="http://layim.layui.com/" target="_blank">WebIM</a>
                    <dd>
                    <dd><a href="http://layer.layui.com/" target="_blank">layer</a>
                    <dd>
                    <dd><a href="http://www.layui.com/laydate/" target="_blank">layDate</a>
                    <dd>
                    <dd>
                        <a href="mailto:xianxin@layui-inc.com?subject=%E7%94%B3%E8%AF%B7Fly%E7%A4%BE%E5%8C%BA%E5%8F%8B%E9%93%BE"
                           class="fly-link">申请友链</a>
                    <dd>
                </dl>
            </div>


        </div>
    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/footer.jsp"/>

<script>

    //退出登录
    function doLogout() {


        layui.use('layer', function () {

            var layer = layui.layer;
            //询问框
            layer.confirm('你确定要退出吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.ajax({
                    type: "get",
                    url:"/Forum/getForum",
                    data:{pageNo:1},
                    datatype:"json",
                    success:function () {
                        window.location.href = '${request.getContextPath()}/seeout';
                    }
                });
            }, function () {
                layer.msg('已取消');
            });
        });

    }

</script>


<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cspan id='cnzz_stat_icon_30088308'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D30088308' type='text/javascript'%3E%3C/script%3E"));

$(document).ready(function(){
    getForum();//执行函数
});


//    获取帖子信息
function getForum() {
    var form =new Array();
    $.ajax(
        {
            type: "POST",
            url:"/Forum/getForum",
            data:{pageNo:1},
            dataType:"json",
            success:function (data) {
//                alert('提示:数据获取成功!');
//                console.log(data);
                form =data;
//                layui.use('laypage', function(){
//                    var laypage = layui.laypage;
//
//                    //执行一个laypage实例
//                    laypage.render({
//                        elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
//                        ,count: 50 //数据总数，从服务端得到
//                    });
//                });

            },
            error:function() {
                $.messager.alert('提示', '系统响应超时!');
            }
        }
    );
    <%--window.location.href = '${request.getContextPath()}/Forum/getForum';--%>
}


//    获取单条帖子信息
    function  ForumDetaile(forumid) {
        var Forumid=forumid;
        console.log(Forumid);
        window.location.href = "${request.getContextPath()}/Forum/ForumDetail?Forumid="+Forumid+"";
//        $.ajax({
//            type: "post",
//            url: "/Forum/ForumDetail",
//            dataType: "json",
//            data:{
//                Forumid:Forumid
//            },
//            success:function () {
//                console.log("成功");
//                alert('提示:数据获取成功!');
//                window.location.href = "bbs_jsp/Forum/ForumDetail";
//            },
//            error:function () {
//                console.log("2");
//            }
//
//        });
    }
    //获取周榜用户详情
   function userhome(id,replyauthor) {
       var userid = id;
       var replyauthor= replyauthor;
       var params ={
           userid:userid,
           replyauthor:replyauthor
       };
       window.location.href = "${request.getContextPath()}/user/userhome?userid="+userid+"&replyauthor="+replyauthor+"";
   }

   //获取热帖详情
   function HotForum(id) {
       var forumid = id;
       window.location.href="${request.getContextPath()}/Forum/ForumDetail?Forumid="+forumid+""
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
                    bbsType: ForumType,
                    pageNo:1
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

</body>
</html>
