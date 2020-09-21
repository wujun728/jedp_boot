<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<style>
body {
	margin: 10;
	font-size: 62.5%;
	line-height: 1.5;
}

.blue-button {
	background: #25A6E1;
	padding: 3px 20px;
	color: #fff;
	font-size: 12px;
	border-radius: 2px;
	-moz-border-radius: 2px;
	-webkit-border-radius: 4px;
	border: 1px solid #1A87B9
}

table {
	width: 70%;
}

th {
	background: SteelBlue;
	color: white;
}

td, th {
	border: 1px solid gray;
	font-size: 12px;
	text-align: left;
	padding: 5px 10px;
	overflow:hidden; 
	white-space:nowrap; 
	text-overflow:ellipsis;
	max-width: 200px;
}
</style>
</head>
<script type="text/javascript">
	function formReset() {
		window.top.location.href = "$\{pageContext.request.contextPath}/${instanceName}/";
	}
</script>
<body>
	<jsp:include page="/" />
	<form:form method="post" modelAttribute="item" action="$\{pageContext.request.contextPath}/${instanceName}/add">
		<table>
			<tr>
				<th colspan="3">Add or Edit Item</th>
				<form:hidden path="${pk.fieldName}" />
			</tr>
			<#list columns as column>
			<tr>
				<td><form:label path="${column.fieldName}">${column.fieldName}:</form:label></td>
				<td><form:input path="${column.fieldName}" size="30" maxlength="30"></form:input></td>
				<td>${column.javaType} <form:errors path="${column.fieldName}" cssStyle="color:red" /></td>
			</tr>
			</#list>
			<tr>
				<td colspan="3" style="text-align: center;"><input type="submit" class="blue-button" /> <input type="reset" class="blue-button" onclick="formReset()" /></td>
			</tr>
		</table>
	</form:form>
	<h3>Data List</h3>
	<c:if test="$\{!empty page.items}">
		<table>
			<tr>
				<td align="left">&nbsp;共 <b><font color="red">$\{page.total}</font> </b>条记录- 分 <b><font color="red">$\{page.totalPage }</font> </b>页 - 这是第 <b> <font color="red">$\{page.pageNo}</font>
				</b>页&nbsp; | &nbsp; <a href="list?pageNo=1">首页</a>&nbsp; <a href="list?pageNo=$\{page.pageNo-1}">前一页</a>&nbsp; <a href="list?pageNo=$\{page.pageNo+1 gt page.totalPage?page.totalPage:page.pageNo+1}">后一页</a>&nbsp;
					<a href="list?pageNo=$\{page.totalPage}">末页</a>&nbsp;
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<th width="5">序号</th>
				<th width="5">Edit</th>
				<th width="5">Delete</th>
				<th width="100">${pk.fieldName}</th>
				<#list columns as column>
				<th width="150">${column.fieldName}</th>
				</#list>
			</tr>
			<c:forEach items="$\{page.items}" var="it" varStatus="vs">
				<tr>
					<td>$\{(page.pageNo-1)*page.pageSize+vs.count}</td>
					<td><a href="<c:url value='/${instanceName}/update/$\{it.id}' />">Edit</a></td>
					<td><a href="<c:url value='/${instanceName}/delete/$\{it.id}' />">Delete</a></td>
					<td>$\{it.${pk.fieldName}}</td>
					<#list columns as column>
					<td>$\{it.${column.fieldName}}</td>
					</#list>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
