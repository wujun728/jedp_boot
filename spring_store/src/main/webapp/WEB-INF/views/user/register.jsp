<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册页面</title>

<link rel="stylesheet" href="css/style.css"/>

<script type="text/javascript" src="js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="js/easyform.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

</head>
<body>
<br/>
<div class="form-div">
    <form id="reg-form" action="" method="post">

        <table>
            <tr>
                <td>用户名</td>
                <td><input name="username" type="text" id="uid" easyform="length:4-16;char-normal;real-time;" message="用户名必须为4—16位的英文字母或数字" easytip="disappear:lost-focus;theme:blue;" ajax-message="用户名已存在!"/>
                </td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input name="userpass" type="password" id="psw1" easyform="length:6-16;" message="密码必须为6—16位" easytip="disappear:lost-focus;theme:blue;"/></td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td><input name="psw2" type="password" id="psw2" easyform="length:6-16;equal:#psw1;" message="两次密码输入要一致" easytip="disappear:lost-focus;theme:blue;"/></td>
            </tr>
            <tr>
                <td>email</td>
                <td><input name="email" type="text" id="email" easyform="email;real-time;" message="Email格式要正确" easytip="disappear:lost-focus;theme:blue;" ajax-message="这个Email地址已经被注册过，请换一个吧!"/></td>
            </tr>
            <tr>
                <td>昵称</td>
                <td><input name="nickname" type="text" id="nickname" easyform="length:2-16" message="昵称必须为2—16位" easytip="disappear:lost-focus;theme:blue;"/></td>
            </tr>            
        </table>

		<div class="buttons">
			<input value="注 册" onclick="sub()" type="button" style="margin-right:20px; margin-top:20px;"/>
			<input value="我有账号，我要登录" onclick="exit()" type="button" style="margin-right:45px; margin-top:20px;"/>
        </div>
		
        <br class="clear"/>
    </form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#reg-form').easyform();
});
var  exit=function(){
	window.close();
}
var  sub=function(){
	/* var param = $("form").serializeJson(); */
	var param = {"username":$("#uid").val(),"userpass":$("#psw1").val()};
	var param2=JSON.stringify(param);
	$.ajax({
		url:"/registerdo",
		type:"post",
		dataType:"json",
		data:param2,
		contentType:"application/json",
		success:function(data){
			if(data>0){
				window.close();
			}else{
				alert("注册失败，请稍后再试！");
			}
		}
	})
}
</script>
<div style="text-align:center;clear:both;">
</div>
</body>
</html>
