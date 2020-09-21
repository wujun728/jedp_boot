<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品添加页</title>

<link rel="stylesheet" href="css/style.css"/>

<script type="text/javascript" src="js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="js/easyform.js"></script>

</head>
<body>
<br/>
<div class="form-div">
    <form id="reg-form" action="" method="post">

        <table>
            <tr>
                <td>商品名</td>
                <td><input name="goodname" type="text" id="uid" easyform="length:4-16;char-normal;real-time;" message="用户名必须为4—16位的英文字母或数字" easytip="disappear:lost-focus;theme:blue;" ajax-message="用户名已存在!"/>
                </td>
            </tr>
            <tr>
                <td>商品编号</td>
                <td><input name="goodno" type="password" id="psw1" easyform="length:6-16;" message="密码必须为6—16位" easytip="disappear:lost-focus;theme:blue;"/></td>
            </tr>
            <tr>
                <td>商品价格</td>
                <td><input name="goodprice" type="password" id="psw2" easyform="length:6-16;equal:#psw1;" message="两次密码输入要一致" easytip="disappear:lost-focus;theme:blue;"/></td>
            </tr>
            <tr>
                <td>商品数量</td>
                <td><input name="goodnum" type="text" id="email" easyform="email;real-time;" message="Email格式要正确" easytip="disappear:lost-focus;theme:blue;" ajax-message="这个Email地址已经被注册过，请换一个吧!"/></td>
            </tr>
            <tr>
                <td>商品图片</td>
                <td><input name="goodimg" type="file" id="nickname" easyform="length:2-16" message="必须选择一个商品图标" easytip="disappear:lost-focus;theme:blue;"/></td>
            </tr>            
        </table>

		<div class="buttons">
			<input value="提交商品" type="submit" style="margin-right:20px; margin-top:20px;"/>
			<input value="重置清空" type="reset" style="margin-right:45px; margin-top:20px;"/>
        </div>
		
        <br class="clear"/>
    </form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#reg-form').easyform();
});
</script>
<div style="text-align:center;clear:both;">
</div>
</body>
</html>
