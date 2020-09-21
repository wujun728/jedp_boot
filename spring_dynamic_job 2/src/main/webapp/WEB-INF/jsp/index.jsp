<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>spring-dynamic-job</title>
</head>
<body>
<h3>
    spring-dynamic-job is working...
    <br/>
    ${date}
</h3>

<c:if test="${empty param.result}">
    <p>
        <a href="add_job.xhtm">Add Dynamic Job</a>
    </p>
</c:if>

<c:if test="${not empty param.result}">
    <p style="color:blue;">
        A new DynamicJob added, please see console log...
    </p>
    <a href="remove_job.xhtm">Remove Added Dynamic Job</a>
</c:if>
</body>
</html>