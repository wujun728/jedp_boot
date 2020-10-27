<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String headPath = request.getContextPath();
    String headBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+headPath+"/";
%>
<base href="<%=headBasePath%>
