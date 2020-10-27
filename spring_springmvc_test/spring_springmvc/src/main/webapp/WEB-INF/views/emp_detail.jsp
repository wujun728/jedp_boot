<%--
  Created by IntelliJ IDEA.
  User: liuburu
  Date: 2018/1/8
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // springmvc
    String contextPath = request.getContextPath();
    // http://localhost:8080/springmvc/
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
    request.setAttribute("ctx", basePath);
%>
<html>
<head>
    <title>员工详情页面</title>
</head>
<body>
<table>
    <tr> <td>员工编号</td> <td>${employ.empno}</td> </tr>
    <tr> <td>员工姓名</td> <td>${employ.ename}</td> </tr>
    <tr> <td>职    位</td> <td>${employ.job}</td> </tr>
</table>
</body>
<%--加载静态资源--%>
<script src="${ctx}/js/jquery-1.11.1.min.js"></script>
<script>
    $(function () {
        console.log("静态资源加载成功!")
    })
</script>
</html>
