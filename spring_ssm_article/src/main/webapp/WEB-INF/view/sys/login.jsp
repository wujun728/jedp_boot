<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>登陆页面</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="/static/common_css/bootstrap.3.3.0.min.css">
<link rel="stylesheet" href="/static/common_css/font-awesome.min.css">
<link rel="stylesheet" href="/static/common_css/AdminLTE.min.css">
<link rel="stylesheet" href="/static/common_css/all-skins.min.css">
<link rel="stylesheet" href="/static/common_css/main.css">

<link rel="stylesheet" href="/static/css/sys/login.css">
	<link rel="shortcut icon" href="/favicon.ico" />

</head>
<body class="hold-transition login-page">
<div class="login-box" id="younger-app" v-cloak>
	<div class="login-logo">
		<b>Younger学习系统</b>
	</div>
	<!-- login-body -->
	<div class="login-box-body">
		<p class="login-box-msg">用户登陆</p>
		<div v-if="error" class="alert alert-danger alert-dismissable">
			<h4 style="margin-bottom: 0px;"><i class="fa fa-exclamation-triangle">{{errorMsg}}</i></h4>
		</div>
		<div class="form-group has-feedback">
			<input type="text" class="form-control" v-model="account" placeholder="请输入您的账号" value="account1"/>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		</div>
		<div class="form-group has-feedback">
			<input type="password" class="form-control" v-model="password" placeholder="请输入您的密码" value="guest"/>
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		</div>
		<div class="form-group has-feedback">
			<input type="checkbox" v-model="rememberMe"/>
			<span> 记住我</span>
			<%--<div class="checkbox icheck">
				<label class="">
					<div class="icheckbox_square-blue" aria-checked="false" aria-disabled="false" style="position: relative;">
						<input type="checkbox" style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;">
						<ins class="iCheck-helper" style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
					</div> 记住我
				</label>
			</div>--%>
		</div>
		<div class="row">
			<div class="col-xs-8">
				<div class="checkbox icheck"></div>
			</div>
			<div class="col-xs-4">
				<button type="button" class="btn btn-primary btn-block btn-file" @click="login">登陆</button>
			</div>
		</div>

	</div>
</div>
<!--引入js-->
<script src="/static/common_js/jquery.1.11.1.min.js" type="text/javascript"></script>
<script src="/static/common_js/vue.min.js" type="text/javascript"></script>
<script src="/static/common_js/bootstrap.3.3.0.min.js" type="text/javascript"></script>
<script src="/static/common_js/jquery.slimscroll.min.js" type="text/javascript"></script>
<!--  -->
<script type="text/javascript">
	var vm=new Vue({
		el:"#younger-app",
		data:{
			account:'',
			password:'',
			rememberMe:'',
			error:false,
			errorMsg:''

		},
		beforeCreate:function () {
			if(self !=top){
				top.location.href=self.location.href;
			}
		},
		methods:{
			login:function(event){
				//var data="account="+vm.account+"&password="+vm.password;
				if($("input[type='checkbox']").is(":checked")){
					vm.rememberMe=true;
				}else {
					vm.rememberMe=false;
				}
				var data={
					account:vm.account,
					password:vm.password,
					rememberMe:vm.rememberMe
				}
				$.ajax({
					type:"POST",
					url:"sys/login",
					data:data,
					dataType:"json",
					success:function(result){
						if(window.console){
							console.log(result.code);
						}
						if(result.code){//登陆成功，跳转页面
							vm.errorMsg=result.message;
							vm.error=true;
							window.location.href="/sys/index"
						}else{
							vm.errorMsg=result.message;
							vm.error=true;
						}
					}
				})

			}
		}
	})









</script>
</body>
</html>