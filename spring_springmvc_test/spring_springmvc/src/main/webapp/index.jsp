<%--
  Created by IntelliJ IDEA.
  User: liuburu
  Date: 2018/1/8
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // springmvc
    String contextPath = request.getContextPath();
    // http://localhost:8080/springmvc/
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
    request.setAttribute("ctx", basePath);
%>
<html>
<head>
    <title>欢迎页面</title>
</head>
<body>
<div style="text-align: center">
    <h1>This World Is Beautiful. ---KiWiPeach</h1>
</div>
<hr>
<h3>1.测试SpringMVC页面跳转，HelloWorld</h3>
<a href="${ctx}/demo/toEmpDetail?empno=7369">/demo/toEmpDetail?empno=7369</a>
<hr>
<h3>2.测试SpringMVC JSON数据返回</h3>
<a href="${ctx}/demo/queryEmpDetail?empno=7369">/demo/queryEmpDetail?empno=7369</a>
<hr>
<h3>3.测试SpringMVC 多数据源分页查询功能</h3>
<a href="${ctx}/demo/mulityds/pagequery?pageNo=1&pageSize=2&jobNameList=['MANAGER','SALESMAN']&dbtype=mysql">/demo/mulityds/pagequery?pageNo=1&pageSize=2&jobNameList=['MANAGER','SALESMAN']&dbtype=mysql</a>
<hr>
<h3>4.测试框架多数据源事务案例</h3>
<a href="${ctx}/demo/testMulityDTransactionService">/demo/testMulityDTransactionService</a>
<h3>5.测试参数解析器[@cn.kiwipeach.demo.framework.annotations.CurrentUser]</h3>
<a href="${ctx}/demo/argumentresolver">/demo/argumentresolver</a>
<hr>
<h3>3.测试参数转换器[cn.kiwipeach.demo.framework.converter.EmployConverter] </h3>
<form action="${ctx}/demo/converter" method="POST">
    <!-- [empno,ename,job] 实例：[8825,孙悟空,弼马翁]-->
    员工格式[8825,孙悟空,弼马翁]: <input type="text" name="employ" value="[8825,孙悟空,弼马翁]"/> <input type="submit" value="Submit"/>
</form>
<hr>
<hr>
<h3>7.地址栏Get方式乱码测试</h3>
<a href="${ctx}/demo/urlargs?urlArgs=中国">/demo/urlargs?urlArgs=中国</a>
<hr>
<h3>8.测试普通表单POJO传值以及乱码问题 </h3>
<form action="${ctx}/demo/pojo" method="POST">
    <table>
        <tr>
            <td>编号</td>
            <td><input type="text" name="empno" value="8825"/></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><input type="text" name="ename" value="孙悟空"/></td>
        </tr>
        <tr>
            <td>工作</td>
            <td><input type="text" name="job" value="弼马温"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
</form>
<hr>
<h3>9.@InitBander作用[job属性将会被忽略]</h3>
<form action="${ctx}/demo/initBander" method="POST">
    <table>
        <tr>
            <td>编号</td>
            <td><input type="text" name="empno" value="8825"/></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><input type="text" name="ename" value="孙悟空"/></td>
        </tr>
        <tr>
            <td>工作</td>
            <td><input type="text" name="job" value="弼马温"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
</form>
<hr>
<h3>10.资源国际化测试[需要浏览器语言切换试试]</h3>
<a href="${ctx}/demo/i18n">/demo/i18n</a>
<hr>
<h3>11.不经过Handler而直接访问页面</h3>
<a href="${ctx}/direct/i18n">/direct/i18n</a>
<hr>
<hr>
<h3>12.自定以试图解析器，视图地址默为自定义视图Bean的名称[myCustomView]</h3>
<a href="${ctx}/demo/customView">/demo/customView</a>
<hr>
<h3>13.员工信息管理系统</h3>
<a href="${ctx}/employ/home">/employ/home</a>
</body>
</html>
