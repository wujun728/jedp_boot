<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>新闻列表</title>

<link href="${ctx}/static/jquery/themes/base/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<script src="${ctx}/static/jquery/ui/jquery-ui.custom.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/ui/jquery.ui.datepicker.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/ui/i18n/jquery.ui.datepicker-zh-CN.min.js" type="text/javascript"></script>
</head>
<body>

	<form:form class="form-horizontal">
		<div class="form-group">
			<div class="col-sm-4">
				<input type="text" class="form-control" name="keywords" id="keywords" />
			</div>
			<div class="col-sm-2">
				<button class="btn btn-default" type="button" id="searchBtn">搜索</button>
			</div>
		</div>
	</form:form>
	<div class="row">
		<table class="table table-condensed">
			<thead>
				<tr>
					<th>问题</th>
					<th>问题描述</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody id="newsBody">
				<c:forEach var="s" items="${surveys }">
					<tr>
						<td>${s.name }</td>
						<td>${s.description }</td>
						<td>${s.createtime }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>

