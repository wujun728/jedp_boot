<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/view/common/tagPage.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%
    String path = request.getContextPath();
%>
<head>
    <title>首页</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="${webRoot}/resources/js/test/test.js"></script>
</head>
<body>

<!-- 为 ECharts 准备一个具备大小（宽高）的Dom -->
<div id="child1" style="height:400px;"></div>
<div id="child2" style="height:400px;"></div>

</body>
</html>
