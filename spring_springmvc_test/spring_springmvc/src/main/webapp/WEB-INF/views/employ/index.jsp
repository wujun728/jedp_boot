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
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
    request.setAttribute("ctx", basePath);
%>
<html>
<head>
    <title>员工信息首页</title>
</head>
<body>
<div style="text-align: center">
    <h2> <a href="${ctx}">返回案例列表 </a></h2>
    <h2>欢迎来到员工管理系统首页 </h2>
    <h2>
        <button id="add_bth">新增员工</button>
    </h2>
</div>
<div>
    <div>
        <table id="employTable" border="1" cellspacing="0" cellpadding="10" style="margin: 0 auto">
            <thead>
            <td>编号</td>
            <td>姓名</td>
            <td>职位</td>
            <td>雇用日期</td>
            <td>薪水</td>
            <td>奖金</td>
            <td>操作</td>
            </thead>
            <c:forEach items="${employList.data}" var="employ" varStatus="status">
                <tr>
                    <td>${employ.empno}</td>
                    <td>${employ.ename}</td>
                    <td>${employ.job}</td>
                    <td><fmt:formatDate value="${employ.hiredate}" pattern="yyyy年MM月dd日"/></td>
                    <td>${employ.sal}</td>
                    <td>${employ.comm}</td>
                    <td>
                        <button id="update_btn_${employ.empno}" onclick="employModel.update('${employ.empno}')">修改
                        </button>
                        <button id="delte_btn_${employ.empno}" onclick="employModel.delete('${employ.empno}')">删除
                        </button>
                        <button id="query_btn_${employ.empno}" onclick="employModel.deptInfoQuery('${employ.empno}')">
                            部门信息
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>
</body>
<script src="${ctx}/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/js/employ/employ_model.js"></script>
<script>
    $(function () {
        //初始化上下文
        employModel.URL.ctx = '${ctx}';
        //新增员工事件绑定
        employModel.getDom('add_bth').bind("click", function () {
            console.log("新增页面跳转");
            window.location.href = "${ctx}/employ/add";
        });
    })
</script>

</html>
