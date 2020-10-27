<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>后台管理系统</title>
<link href="<%=ctxPath %>/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=ctxPath %>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
<script src="<%=ctxPath %>/jquery/jquery-1.10.1.min.js"></script>
<script src="<%=ctxPath %>/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=ctxPath %>/js/md5.js"></script>
<script src="<%=ctxPath %>/admin/js/header_with_menu.js"></script>
<script type="text/javascript">
var ctxPath = '<%=ctxPath%>';
</script>
</head>
<body>
	<nav class="navbar navbar-default">
  		<div class="container-fluid">
  			<div class="navbar-header">
		      	<a class="navbar-brand" href="./index.jsp">
					<img alt="logo" src="./images/logo.jpg" width="35" height="35">
				</a>
		    </div>
    
  			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		    	<ul class="nav navbar-nav">
		        	<li class="active"><a href="#">用户管理 </a></li>
		        	<li><a href="#">菜单管理</a></li>
		        	<li class="dropdown">
		          		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">系统管理<span class="caret"></span></a>
		          		<ul class="dropdown-menu">
				            <li><a href="#">小师</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">国中</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">见红</a></li>
		          		</ul>
		        	</li>
		      	</ul>
		      	<ul class="nav navbar-nav navbar-right">
		      		<li class="dropdown">
		          		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">欢迎您，<sec:authentication property="principal.username" /><span class="caret"></span></a>
		          		<ul class="dropdown-menu">
		          			<li><a href="#" id="pwdModify">修改密码</a></li>
				            <li><a href="./logout.action">退出</a></li>
				        </ul>
					</li>
		      	</ul>
		 	</div>
		</div>
	</nav>
	
	<div class="modal fade" id="pwdModal" tabindex="-1" role="dialog" aria-labelledby="pwdModify">
		<div class="modal-dialog" role="document">
	    	<div class="modal-content">
	      		<div class="modal-header">
	        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        		<h4 class="modal-title" id="pwdModify">修改密码</h4>
	      		</div>
		      	<div class="modal-body">
		        	<div class="input-group" style="margin-top:15px;">
					  	<span class="input-group-addon" id="basic-addon1">旧密码</span>
					  	<input type="password" name="oldPassword" class="form-control" placeholder="旧密码" aria-describedby="basic-addon1">
					</div>
					<div class="input-group" style="margin-top:15px;">
					  	<span class="input-group-addon" id="basic-addon1">新密码</span>
					  	<input type="password" name="newPassword" class="form-control" placeholder="6-10位数字或字符" aria-describedby="basic-addon1">
					</div>
					<div class="input-group" style="margin-top:15px;">
					  	<span class="input-group-addon" id="basic-addon1">重复新密码</span>
					  	<input type="password" name="confirmPassword" class="form-control" placeholder="6-10位数字或字符" aria-describedby="basic-addon1">
					</div>
		      	</div>
		      	<div class="modal-footer">
		        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        	<button type="button" class="btn btn-primary" id="submit">提交</button>
		      	</div>
	    	</div>
	  	</div>
	</div>