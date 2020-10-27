<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list需要管理员权限才能访问</title>
</head>
<body>

管理员可访问<a href="<%=request.getContextPath()%>/sys/listUser" target="_blank">用户列表页面</a>  

<br/>  

<br/>  

<a href="<%=request.getContextPath()%>/sys/logout" target="_blank">Logout</a>
</body>
</html>