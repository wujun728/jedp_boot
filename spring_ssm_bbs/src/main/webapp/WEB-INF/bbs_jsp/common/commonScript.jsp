<%--
  Created by IntelliJ IDEA.
  User: zlq
  Date: 2019/5/14
  Time: 16:41
  To change this template use File | Settings | File Templates.
  所有通用的JS方法写在一起
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    function personalForum(userid) {
        console.log(userid);

        //使用layui组件
        layui.use('layer',function () {

            var layer = layui.layer;
            if (userid == null || userid == '') {
                layer.msg("请先登录！")
            }else {
                window.location.href="${request.getContextPath()}/user/index?userid="+userid+""
            }
        });

    }

    //    获取单条帖子信息
    function  ForumDetaile(forumid) {
        var Forumid=forumid;
        console.log(Forumid);
        window.location.href = "${request.getContextPath()}/Forum/ForumDetail?Forumid="+Forumid+"";
    }
</script>