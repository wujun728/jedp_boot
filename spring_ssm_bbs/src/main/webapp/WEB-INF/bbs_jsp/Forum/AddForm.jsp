<%--
  Created by IntelliJ IDEA.
  User: zlq
  Date: 2018/12/28
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>发表问题 编辑问题 公用</title>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/commonjs.jsp"/>
    <script src="${pageContext.request.contextPath}/resource/bbs_resourse/js/index.js" charset="utf-8"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/header.jsp"/>

<div class="layui-container  fly-marginTop">
    <div class="fly-panel" pad20="" style="padding-top: 5px;">
        <c:choose>
            <c:when test="${empty sessionScope.session_User}">
                <div class="fly-none">请先登入~亲！</div>
            </c:when>
            <c:otherwise>
                <div class="layui-form layui-form-pane">
                    <div class="layui-tab layui-tab-brief" lay-filter="user">
                        <ul class="layui-tab-title">
                            <li class="layui-this">发表新帖</li>
                                <%--<li class="layui-this">编辑帖子</li>--%>
                        </ul>
                        <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                            <div class="layui-tab-item layui-show">
                                <form action="/Forum/AddForum" method="post" id="AddForum">
                                    <div class="layui-row layui-col-space15 layui-form-item">
                                        <div class="layui-col-md3">
                                            <label class="layui-form-label">所在专栏</label>
                                            <div class="layui-input-block">
                                                <select lay-verify="required" name="bbsType" lay-filter="column">
                                                    <option></option>
                                                    <option value="提问">提问</option>
                                                    <option value="分享">分享</option>
                                                    <option value="讨论">讨论</option>
                                                    <option value="建议">建议</option>
                                                    <option value="公告">公告</option>
                                                    <option value="动态">动态</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="layui-col-md9">
                                            <label for="L_title" class="layui-form-label">标题</label>
                                            <div class="layui-input-block">
                                                <input type="text" id="L_title" name="title" required
                                                       lay-verify="required" autocomplete="off" class="layui-input">
                                                <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-row layui-col-space15 layui-form-item layui-hide"
                                         id="LAY_quiz">
                                        <div class="layui-col-md3">
                                            <label class="layui-form-label">所属产品</label>
                                            <div class="layui-input-block">
                                                <select name="project">
                                                    <option value="layui">layui</option>
                                                    <option value="独立版layer">独立版layer</option>
                                                    <option value="独立版layDate">独立版layDate</option>
                                                    <option value="LayIM">LayIM</option>
                                                    <option value="Fly社区模板">Fly社区模板</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="layui-col-md3">
                                            <label class="layui-form-label" for="L_version">版本号</label>
                                            <div class="layui-input-block">
                                                <input type="text" id="L_version" value="" name="version"
                                                       autocomplete="off" class="layui-input">
                                            </div>
                                        </div>
                                        <div class="layui-col-md6">
                                            <label class="layui-form-label" for="L_browser">浏览器</label>
                                            <div class="layui-input-block">
                                                <input class="layui-input" type="text" id="L_browser" name="browser"
                                                       value="" autocomplete="off" placeholder="浏览器名称及版本，如：IE 11">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-form-item layui-form-text">
                                        <div class="layui-input-block">
                                            <textarea id="L_content" name="content" required
                                                      placeholder="详细描述" class="layui-textarea fly-editor"
                                                      style="height: 260px;"></textarea>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <div class="layui-inline">
                                            <label class="layui-form-label">悬赏飞吻</label>
                                            <div class="layui-input-inline" style="width: 190px;">
                                                <select name="reward">
                                                    <option value="20">20</option>
                                                    <option value="30">30</option>
                                                    <option value="50">50</option>
                                                    <option value="60">60</option>
                                                    <option value="80">80</option>
                                                </select>
                                            </div>
                                            <div class="layui-form-mid layui-word-aux">发表后无法更改飞吻</div>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label for="L_vercode" class="layui-form-label">人类验证</label>
                                        <div class="layui-input-inline">
                                            <input type="text" id="L_vercode" name="vercode" required
                                                   lay-verify="required" placeholder="请回答后面的问题" autocomplete="off"
                                                   class="layui-input">
                                        </div>
                                        <div style="float:left;display: block;padding: 1px 0!important;line-height: 20px;margin-right: 10px">
                                            <span style="color: #c00;">
                                                  <img id="randomImg" style="padding: inherit"
                                                       onclick="freshImg()" src="/user/VerifyImg">
                                            </span>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <button class="layui-btn" lay-filter="*" onclick="addforum()">立即发布</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>


<jsp:include page="${pageContext.request.contextPath}/WEB-INF/bbs_jsp/common/footer.jsp"/>

<script>

    function addforum() {
        var AddForum = $("#AddForum");
        AddForum.submit();
    }

    function freshImg() {
        var obj = document.getElementById("randomImg");
        obj.style.display = "none";
        obj.src = "/user/VerifyImg";
        setTimeout(function() {
            obj.style.display = "";
            theform.verifyCode.focus();
        }, 200);
    }
</script>
</body>

</html>
