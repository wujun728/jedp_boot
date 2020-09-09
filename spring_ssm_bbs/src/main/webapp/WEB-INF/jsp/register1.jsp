<%--
  Created by IntelliJ IDEA.
  User: 张林强
  Date: 2018/07/19
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" >
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>登录</title>
    <link rel="stylesheet" href="${ctx}/resource/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/resource/css/login.css">
    <link rel="stylesheet" href="${ctx}/resource/css/sign-up-login.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/resource/css/inputEffect.css" />
    <link rel="stylesheet" href="${ctx}/resource/css/tooltips.css" />
    <link rel="stylesheet" href="${ctx}/resource/css/spop.min.css" />

    <script src="${ctx}/resource/js/jquery.min.js"></script>
    <script src="${ctx}/resource/js/snow.js"></script>
    <script src="${ctx}/resource/js/jquery.pure.tooltips.js"></script>
    <script src="${ctx}/resource/js/spop.min.js"></script>
    <script>

        //主题下按回车提交表单
        function onLoginEnterDown(){
            if(window.event.keyCode == 13){
                login();
            }
        }

        function onForgetEnterDown(){
            if(window.event.keyCode == 13){
                forget();
            }
        }

        function onRegisteEnterDown(){
            if(window.event.keyCode == 13){
                register();
            }
        }

        (function() {
            // trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
            if (!String.prototype.trim) {
                (function() {
                    // Make sure we trim BOM and NBSP
                    var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
                    String.prototype.trim = function() {
                        return this.replace(rtrim, '');
                    };
                })();
            }

            [].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
                // in case the input is already filled..
                if( inputEl.value.trim() !== '' ) {
                    classie.add( inputEl.parentNode, 'input--filled' );
                }

                // events:
                inputEl.addEventListener( 'focus', onInputFocus );
                inputEl.addEventListener( 'blur', onInputBlur );
            } );

            function onInputFocus( ev ) {
                classie.add( ev.target.parentNode, 'input--filled' );
            }

            function onInputBlur( ev ) {
                if( ev.target.value.trim() === '' ) {
                    classie.remove( ev.target.parentNode, 'input--filled' );
                }
            }
        })();

        $(function() {
            $('#login #login-password').focus(function() {
                $('.login-owl').addClass('password');
            }).blur(function() {
                $('.login-owl').removeClass('password');
            });
            $('#login #register-password').focus(function() {
                $('.register-owl').addClass('password');
            }).blur(function() {
                $('.register-owl').removeClass('password');
            });
            $('#login #register-repassword').focus(function() {
                $('.register-owl').addClass('password');
            }).blur(function() {
                $('.register-owl').removeClass('password');
            });
            $('#login #forget-password').focus(function() {
                $('.forget-owl').addClass('password');
            }).blur(function() {
                $('.forget-owl').removeClass('password');
            });
        });

        function goto_register(){
            console.dir(${ctx});

            $("#register-username").val("");
            $("#register-loginname").val("");
            $("#register-password").val("");
            $("#register-repassword").val("");
            $("#register-code").val("");
            $("#tab-2").prop("checked",true);
        }

        function goto_login(){
            $("#login-loginname").val("");
            $("#login-password").val("");
            $("#tab-1").prop("checked",true);
        }

        function goto_forget(){
            $("#forget-loginname").val("");
            $("#forget-password").val("");
            $("#forget-code").val("");
            $("#tab-3").prop("checked",true);
        }

        function login(){//登录
            var username = $("#login-loginname").val(),
                password = $("#login-password").val(),
                validatecode = null,
                flag = false;
            //判断用户名密码是否为空
            if(username == ""){
                $.pt({
                    target: $("#login-loginname"),
                    position: 'r',
                    align: 't',
                    width: 'auto',
                    height: 'auto',
                    content:"用户名不能为空"
                });
                flag = true;
            }
            if(password == ""){
                $.pt({
                    target: $("#login-password"),
                    position: 'r',
                    align: 't',
                    width: 'auto',
                    height: 'auto',
                    content:"密码不能为空"
                });
                flag = true;
            }
            //用户名只能是15位以下的字母或数字
            var regExp = new RegExp("^[a-zA-Z0-9_]{1,15}$");
            if(!regExp.test(username)){
                $.pt({
                    target: $("#login-loginname"),
                    position: 'r',
                    align: 't',
                    width: 'auto',
                    height: 'auto',
                    content:"用户名必须为15位以下的字母或数字"
                });
                flag = true;
            }

            if(flag){
                return false;
            }else{//登录
                //调用后台登录验证的方法
                var myForm = $("#loginfrom");
                myForm.submit();

            }
        }

        //注册
        function register(){
            var username = $("#register-username").val(),
                loginname= $("#register-loginname").val(),
                password = $("#register-password").val(),
                repassword = $("#register-repassword").val(),
                // code = $("#register-code").val(),
                flag = false,
                validatecode = null;
            //判断用户名密码是否为空
            if(username == ""){
                $.pt({
                    target: $("#register-username"),
                    position: 'r',
                    align: 't',
                    width: 'auto',
                    height: 'auto',
                    content:"用户名不能为空"
                });
                flag = true;
            }
            //判断账号是否为空
            if (loginname == "") {
                $.pt({
                    target: $("#register-loginname"),
                    position: 'r',
                    align: 't',
                    width: 'auto',
                    height: 'auto',
                    content:"账号不能为空"
                });
                flag = true;
            }
            if(password == ""){
                $.pt({
                    target: $("#register-password"),
                    position: 'r',
                    align: 't',
                    width: 'auto',
                    height: 'auto',
                    content:"密码不能为空"
                });
                flag = true;
            }else{
                if(password != repassword){
                    $.pt({
                        target: $("#register-repassword"),
                        position: 'r',
                        align: 't',
                        width: 'auto',
                        height: 'auto',
                        content:"两次输入的密码不一致"
                    });
                    flag = true;
                }
            }
            //用户名只能是15位以下的字母或数字
            var regExp = new RegExp("^[a-zA-Z0-9_]{1,15}$");
            if(!regExp.test(loginname)){
                $.pt({
                    target: $("#register-loginname"),
                    position: 'r',
                    align: 't',
                    width: 'auto',
                    height: 'auto',
                    content:"账号必须为15位以下的字母或数字"
                });
                flag = true;
            }
            //检查用户名是否已经存在
            //调后台代码检查用户名是否已经被注册

            //检查注册码是否正确
            //调后台方法检查注册码，这里写死为11111111
            // if(code != '11111111'){
            //     $.pt({
            //         target: $("#register-code"),
            //         position: 'r',
            //         align: 't',
            //         width: 'auto',
            //         height: 'auto',
            //         content:"注册码不正确"
            //     });
            //     flag = true;
            // }


            if(flag==true){
                return false;
            }else{//注册
                //调用后台登录验证的方法
                var registerform = $("#registerform");
                registerform.submit();
                // spop({
                //     template: '<h4 class="spop-title">注册成功</h4>即将于3秒后返回登录',
                //     position: 'top-center',
                //     style: 'success',
                //     autoclose: 3000,
                //     onOpen : function(){
                //         var second = 2;
                //         var showPop = setInterval(function(){
                //             if(second == 0){
                //                 clearInterval(showPop);
                //             }
                //             $('.spop-body').html('<h4 class="spop-title">注册成功</h4>即将于'+second+'秒后返回登录');
                //             second--;
                //         },1000);
                //     },
                //     onClose : function(){
                //         goto_login();
                //
                //     }
                // });

            }
        }

        //重置密码
        function forget(){
            var loginname = $("#forget-loginname").val(),
                password = $("#forget-password").val(),
                code = $("#forget-code").val(),
                flag = false,
                validatecode = null;
            //判断用户名密码是否为空
            if(loginname == ""){
                $.pt({
                    target: $("#forget-loginname"),
                    position: 'r',
                    align: 't',
                    width: 'auto',
                    height: 'auto',
                    content:"用户名不能为空"
                });
                flag = true;
            }
            if(password == ""){
                $.pt({
                    target: $("#forget-password"),
                    position: 'r',
                    align: 't',
                    width: 'auto',
                    height: 'auto',
                    content:"密码不能为空"
                });
                flag = true;
            }
            //用户名只能是15位以下的字母或数字
            var regExp = new RegExp("^[a-zA-Z0-9_]{1,15}$");
            if(!regExp.test(loginname)){
                $.pt({
                    target: $("#forget-loginname"),
                    position: 'r',
                    align: 't',
                    width: 'auto',
                    height: 'auto',
                    content:"用户名必须为15位以下的字母或数字"
                });
                flag = true;
            }
            //检查用户名是否存在
            //调后台方法

            //检查注册码是否正确
            if(code != '11111111'){
                $.pt({
                    target: $("#forget-code"),
                    position: 'r',
                    align: 't',
                    width: 'auto',
                    height: 'auto',
                    content:"注册码不正确"
                });
                flag = true;
            }



            if(flag){
                return false;
            }else{//重置密码
                spop({
                    template: '<h4 class="spop-title">重置密码成功</h4>即将于3秒后返回登录',
                    position: 'top-center',
                    style: 'success',
                    autoclose: 3000,
                    onOpen : function(){
                        var second = 2;
                        var showPop = setInterval(function(){
                            if(second == 0){
                                clearInterval(showPop);
                            }
                            $('.spop-body').html('<h4 class="spop-title">重置密码成功</h4>即将于'+second+'秒后返回登录');
                            second--;
                        },1000);
                    },
                    onClose : function(){
                        goto_login();
                    }
                });
                return false;
            }
        }







    </script>
    <style type="text/css">
        html{width: 100%; height: 100%;}

        body{

            background-repeat: no-repeat;

            background-position: center center ;

            background-color: #00BDDC;

            background-image: url(${ctx}/resource/images/snow.jpg);

            background-size: 100% 100%;

        }

            /*.snow-container { position: fixed; top: 0; left: 0; width: 100%; height: 100%; pointer-events: none; z-index: 100001; }*/

        .snow-container {
            position: absolute;
            height: 100%;
            width: 100%;
            max-width: 100%;
            top: 0;
            overflow: hidden;
            z-index: 2;
            pointer-events: none;
        }

        .snow {
            display: block;
            position: absolute;
            z-index: 2;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            pointer-events: none;
            -webkit-transform: translate3d(0, -100%, 0);
            transform: translate3d(0, -100%, 0);
            -webkit-animation: snow linear infinite;
            animation: snow linear infinite;
        }
        .snow.foreground {
            background-image: url("${ctx}/resource/images/snow1.png");
            -webkit-animation-duration: 15s;
            animation-duration: 15s;
        }
        .snow.foreground.layered {
            -webkit-animation-delay: 7.5s;
            animation-delay: 7.5s;
        }
        .snow.middleground {
            background-image: url(${ctx}/resource/images/snow2.png);
            -webkit-animation-duration: 20s;
            animation-duration: 20s;
        }
        .snow.middleground.layered {
            -webkit-animation-delay: 10s;
            animation-delay: 10s;
        }
        .snow.background {
            background-image: url(${ctx}/resource/images/snow3.png);
            -webkit-animation-duration: 30s;
            animation-duration: 30s;
        }
        .snow.background.layered {
            -webkit-animation-delay: 15s;
            animation-delay: 15s;
        }

        @-webkit-keyframes snow {
            0% {
                -webkit-transform: translate3d(0, -100%, 0);
                transform: translate3d(0, -100%, 0);
            }
            100% {
                -webkit-transform: translate3d(15%, 100%, 0);
                transform: translate3d(15%, 100%, 0);
            }
        }

        @keyframes snow {
            0% {
                -webkit-transform: translate3d(0, -100%, 0);
                transform: translate3d(0, -100%, 0);
            }
            100% {
                -webkit-transform: translate3d(15%, 100%, 0);
                transform: translate3d(15%, 100%, 0);
            }
        }

    </style>
</head>
<body  >
<!-- 雪花背景 -->
<%--<div class="snow-container"></div>--%>

<!-- 雪花背景样式调用 -->
<div class="snow-container">
    <div class="snow foreground"></div>
    <div class="snow foreground layered"></div>
    <div class="snow middleground"></div>
    <div class="snow middleground layered"></div>
    <div class="snow background"></div>
    <div class="snow background layered"></div>
</div>

<!-- 登录控件 -->
<div id="login" onkeydown="onLoginEnterDown();">
    <input id="tab-1" type="radio" name="tab" class="sign-in hidden" checked />
    <input id="tab-2" type="radio" name="tab" class="sign-up hidden" />
    <input id="tab-3" type="radio" name="tab" class="sign-out hidden" />
    <div class="wrapper">
        <!-- 登录页面 -->
        <div class="login sign-in-htm">
            <form class="container offset1 loginform"  action="/user/login" id="loginfrom" method="post">
                <!-- 猫头鹰控件 -->
                <div id="owl-login" class="login-owl">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>
                <div class="pad input-container">
                    <section class="content">
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="login-loginname"  name="loginname"
                                       autocomplete="off" placeholder="请输入账号" tabindex="1" maxlength="15" />
								<label class="input__label input__label--hideo" for="login-loginname">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="login-password" name="password" placeholder="请输入密码" tabindex="2" maxlength="15"/>
								<label class="input__label input__label--hideo" for="login-password">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
                            <label  >
                                ${error_massage}
                            </label>
                        </span>
                    </section>
                </div>
                <div class="form-actions">
                    <a tabindex="4" class="btn pull-left btn-link text-muted" onClick="goto_forget()">忘记密码?</a>
                    <a tabindex="5" class="btn btn-link text-muted" onClick="goto_register()">注册</a>
                    <input class="btn btn-primary" type="button" tabindex="3" onClick="login()" value="登录"
                           style="color:white;"/>
                </div>

            </form>
        </div>
        <!-- 忘记密码页面 -->
        <div class="login sign-out-htm" onkeydown="onForgetEnterDown();">
            <form action="/user/forgetPW" method="post" class="container offset1 loginform" id="ForgetFrom">
                <!-- 猫头鹰控件 -->
                <div id="owl-login" class="forget-owl">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>
                <div class="pad input-container">
                    <section class="content">
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="forget-loginname" autocomplete="off" placeholder="请输入账号" name="Forgetloginname"/>
								<label class="input__label input__label--hideo" for="forget-loginname">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="forget-code" autocomplete="off" placeholder="请输入注册码"/>
								<label class="input__label input__label--hideo" for="forget-code">
									<i class="fa fa-fw fa-wifi icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="forget-password" placeholder="请重置密码" name="Forgetpassword" />
								<label class="input__label input__label--hideo" for="forget-password">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                    </section>
                </div>
                <div class="form-actions">
                    <a class="btn pull-left btn-link text-muted" onClick="goto_login()">返回登录</a>
                    <input class="btn btn-primary" type="button" onClick="forget()" value="重置密码"
                           style="color:white;"/>
                </div>
            </form>
        </div>
        <!-- 注册页面 -->
        <div class="login sign-up-htm" onkeydown="onRegisteEnterDown();">
            <form action="/user/register" method="post" class="container offset1 loginform"   id="registerform">
                <!-- 猫头鹰控件 -->
                <div id="owl-login" class="register-owl">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>
                <div class="pad input-container">
                    <section class="content">
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="register-username" name="Registeusername"
                                       autocomplete="off" placeholder="请输入用户名" maxlength="15"/>
								<label class="input__label input__label--hideo" for="register-username">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="register-loginname" name="Registeloginname"
                                       autocomplete="off" placeholder="请输入账号" maxlength="15"/>
								<label class="input__label input__label--hideo" for="register-loginname">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="register-password" placeholder="请输入密码" maxlength="15" name="Registerpassword"/>
								<label class="input__label input__label--hideo" for="register-password">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="register-repassword" placeholder="请确认密码" maxlength="15"/>
								<label class="input__label input__label--hideo" for="register-repassword">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <%--<span class="input input--hideo">--%>
								<%--<input class="input__field input__field--hideo" type="text" id="register-code" autocomplete="off" placeholder="请输入注册码"/>--%>
								<%--<label class="input__label input__label--hideo" for="register-code">--%>
									<%--<i class="fa fa-fw fa-wifi icon icon--hideo"></i>--%>
									<%--<span class="input__label-content input__label-content--hideo"></span>--%>
								<%--</label>--%>
							<%--</span>--%>
                    </section>
                </div>
                <div class="form-actions">
                    <a class="btn pull-left btn-link text-muted" onClick="goto_login()">返回登录</a>
                    <input class="btn btn-primary" type="button" onClick="register()" value="注册"
                           style="color:white;"/>
                </div>
            </form>
        </div>
    </div>
</div>
<div style="text-align:center;">
</div>
</body>

</html>
