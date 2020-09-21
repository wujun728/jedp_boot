<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table>
	<tr>
		<th colspan="$\{fn:length(urls)+1}">Navigate</th>
	</tr>
	<tr>
		<c:forEach var="item" items="$\{urls}">
			<td><a href="$\{pageContext.request.contextPath}$\{item}/">$\{item}</a></td>
		</c:forEach>
	</tr>
</table>
