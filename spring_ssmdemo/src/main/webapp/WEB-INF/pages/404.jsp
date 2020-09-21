<%--  Created by CDHong  2018/4/3--%>
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
    <h2>这是错误页面，出错了，页面找不到，请联系管理员！！</h2>
</body>
</html>
