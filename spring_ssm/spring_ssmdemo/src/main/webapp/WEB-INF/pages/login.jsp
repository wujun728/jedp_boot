<%--  Created by Administrator  2018/4/4--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>title</title>
</head>
<body>
    <h2>验证码：
        <img src="kaptcha.jpg"/>
        <a href="javascript:;">看不清</a><span></span>
    </h2>

    <script src="resource/js/jquery-3.1.1.min.js" ></script>
    <script>
        $(function(){
            $("a").click(function(){
                $("img").attr("src","kaptcha.jpg?rand"+Math.random());
            });
        });
    </script>
</body>
</html>
