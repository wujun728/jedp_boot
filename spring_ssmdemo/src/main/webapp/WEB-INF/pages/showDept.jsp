<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jstl标签库，用于页面判断操作--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h2>部门信息:</h2>
    <c:if test="${dept==null}">
       该部门不存在
    </c:if>
    ${dept.deptId}
    ${dept.deptName}
</body>
</html>
