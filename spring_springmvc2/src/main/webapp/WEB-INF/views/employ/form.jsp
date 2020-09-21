<%--
  Created by IntelliJ IDEA.
  User: liuburu
  Date: 2018/1/26
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--spring的form标签--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
    request.setAttribute("ctx", basePath);
%>
<html>
<head>
    <title>员工新增修改页面</title>
</head>
<body>
<%--
使用form标签，方便表单的回显
--%>
<div style="text-align: center">
    <h2>欢迎来到员工管理系统首页</h2>
    <h2><a href="${ctx}/employ/home">返回首页</a></h2>
    <h2 style="color: red">
        <%=
        request.getParameter("info") == null ? "操作提示" : request.getParameter("info")
        %>
    </h2>
</div>
<div>
    <div>
        <table id="employTable" border="1" cellspacing="0" cellpadding="10" style="margin: 0 auto">
            <thead>
            <c:if test="${employ.empno != null }">
                <td>编号</td>
            </c:if>
            <td>姓名</td>
            <td>职位</td>
            <td>雇用日期</td>
            <td>薪水</td>
            <td>奖金</td>
            <td>经理</td>
            <td>部门</td>
            <td>操作</td>
            </thead>
            <form:form id="employForm" modelAttribute="employ" action="${ctx}/employ/saveOrUpdate" method="post">
                <tr>
                    <c:choose>
                        <c:when test="${employ.empno != null }">
                            <td><form:input path="empno" disabled="true"></form:input></td>
                            <form:hidden path="empno"></form:hidden>
                        </c:when>
                        <c:otherwise>
                            <form:hidden path="empno"></form:hidden>
                        </c:otherwise>
                    </c:choose>
                    <td><form:input path="ename"></form:input></td>
                    <td><form:input path="job"></form:input></td>
                    <td><form:input path="hiredate"></form:input></td>
                    <td><form:input path="sal"></form:input></td>
                    <td><form:input path="comm"></form:input></td>
                    <td>
                            <%--理解：从items取itemLabel的itemValue传递给path--%>
                        <form:select path="mgr" items="${managers}" itemLabel="ename" itemValue="empno"></form:select>
                    </td>
                    <td>
                            <%--理解：从items取itemLabel的itemValue传递给path--%>
                        <form:select path="deptno" items="${depts}" itemLabel="dname" itemValue="deptno"></form:select>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${employ.empno == null }">
                                <input type="submit" value="新增"/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="修改"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </form:form>
        </table>

    </div>
</div>
</body>
<script src="${ctx}/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/js/employ/employ_model.js"></script>
<script>
    $(function () {
        employModel.getDom('add_btn').bind('click', function () {
            getDom('employForm').submit();
        });
    })
</script>

</html>
