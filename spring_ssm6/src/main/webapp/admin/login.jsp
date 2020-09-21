<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./common/header.jsp"></jsp:include>
<link href="./css/signin.css" rel="stylesheet">
<script type="text/javascript">
$(function(){
	$("#validateCodeImg").click(function(){
		$("#validateCodeImg").attr("src","../validateCode/create?timestamp="+new Date().getTime());
	});
});
</script>
<body>
	<div class="container">

      <form class="form-signin" action='./login.action' method='POST'>
        <h2 class="form-signin-heading">请登录</h2>
        <div class="form-group">
        	<label for="username" class="sr-only">用户名</label>
        	<input type="username" id="username" name="username" class="form-control" placeholder="用户名" required autofocus>
        </div>
        <div class="form-group">
        	<label for="password" class="sr-only">密码</label>
        	<input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
        </div>
        <div class="form-group">
        	<input style="width:45%;height:42px" id="validateCode" name="validateCode" class="form-control-inline" placeholder="验证码" required>
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img style="width:45%" id="validateCodeImg" alt="验证码" src="../validateCode/create">
        </div>
        <div class="checkbox">
          	<label>
            	<input type="checkbox" name="rememberMe" value="on"> 记住我
          	</label>
        </div>
        <div style="color:#F00;padding:5px;margin:10px"> 
	    ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message} 
	    </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>
    </div>
</body>
</html>