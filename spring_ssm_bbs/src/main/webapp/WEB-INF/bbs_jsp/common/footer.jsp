<%--
  Created by IntelliJ IDEA.
  User: dyl
  Date: 2018/12/25
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="fly-footer">
    <p><a href="#" target="_blank">Fly社区</a> 2018 &copy; <a href=""
                                                            target="_blank">张猿猴 出品</a></p>
    <p>
        <a href="#" target="_blank">学习计划</a>
        <a href="#" target="_blank">获取最新学习资料</a>
        <a href="#" target="_blank">微信公众号</a>
    </p>
</div>

<script>
    layui.config({
        version: "3.0.0"
        , base: '${pageContext.request.contextPath}/resource/bbs_resource/js/' //这里实际使用时，建议改成绝对路径
    }).extend({
        fly: 'index'
    }).use('fly');


    //退出登录
    function doLogout() {

        layui.use('layer', function () {

            var layer = layui.layer;
            //询问框
            layer.confirm('你确定要退出系统吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                window.location.href = '${request.getContextPath()}/seeout';
            }, function () {
                layer.msg('已取消');
            });
        });

    }
</script>
