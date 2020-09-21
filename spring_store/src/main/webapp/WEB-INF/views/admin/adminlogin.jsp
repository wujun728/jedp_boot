<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login...</title>
<meta name="keywords" content="这个网站真特么的都是特效" />
<link rel="stylesheet" type="text/css" href="css/default.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<style type="text/css">
	.button{
		background: #ff4081 none repeat scroll 0 0;
	    color: #fff;
	    cursor: pointer;
	    height: 40px;
	    line-height: 40px;
	    margin-top: 15px;
	    text-align: center;
	    width: 40%;
    }
</style>
</head>
<body>
<div class="panel-lite">
  <div class="thumbur">
	<div class="icon-lock"></div>
  </div>
  <h4>管理员登陆登录</h4>
  <form action="adminlogindo" method="post">
	  <div class="form-group">
		<input required="required" name="adminname" class="form-control user"/>
		<label class="form-label">用户名    </label>
	  </div>
	  <div class="form-group">
		<input type="password" required="required" name="adminpass" class="form-control password"/>
		<label class="form-label">密    码</label>
	  </div>
	  <input class="button" type="submit" value="登录">${msg}
  </form>
  <div class="submit">保存信息</div>
  <div class="reset">清除缓存</div>
</div>

<script src="js/jquery-2.1.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
if(window.localStorage){
	user = localStorage.getItem("userName");
	pwd = localStorage.getItem("password");
	$(".user").val(user);
	$(".password").val(pwd);
	$(".submit").click(function(){
		localStorage.setItem("userName", $(".user").val());
		localStorage.setItem("password", $(".password").val());
	})
	$(".reset").click(function(){
		localStorage.clear();
	})
}else{
	alert('对不起，您的浏览器不支持HTML5本地存储');
}
<!--

//-->
</script>
</body>
</html>