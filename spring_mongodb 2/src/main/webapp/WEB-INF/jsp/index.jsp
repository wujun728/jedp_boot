<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>-首页</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="<c:url value='/static/jquery/themes/base/jquery-ui.min.css?${version_css}'/>" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="row">
		<div class="jumbotron">
			<h1>Hello world</h1>
		</div>
	</div>
</body>
</html>
