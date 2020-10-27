<%--
  Created by IntelliJ IDEA.
  User: wiggins
  Date: 2016/8/15
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
    <jsp:include page="../common/tag.jsp"></jsp:include>
    <meta charset="utf-8">
    <meta http-equiv="pragma" content="no-cache">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS -->
    <style type="text/css">
        /* cyrillic-ext */
        *{
            margin: 0;
            padding: 0;
            border: 0;
            vertical-align: baseline;
        }
       body { background:#111; height:100%; }
       img { border:none; }

       #supersized-loader { position:absolute; top:50%; left:50%; z-index:0; width:60px; height:60px; margin:-30px 0 0 -30px; text-indent:-999em; background:url(${baseurl}resource/imags/login/progress.gif) no-repeat center center;}

       #supersized {  display:block; position:fixed; left:0; top:0; overflow:hidden; z-index:-999; height:100%; width:100%; }
       #supersized img { width:auto; height:auto; position:relative; display:none; outline:none; border:none; }
       #supersized.speed img { -ms-interpolation-mode:nearest-neighbor; image-rendering: -moz-crisp-edges; }	/*Speed*/
       #supersized.quality img { -ms-interpolation-mode:bicubic; image-rendering: optimizeQuality; }			/*Quality*/

       #supersized li { display:block; list-style:none; z-index:-30; position:fixed; overflow:hidden; top:0; left:0; width:100%; height:100%; background:#111; }
       #supersized a { width:100%; height:100%; display:block; }
       #supersized li.prevslide { z-index:-20; }
       #supersized li.activeslide { z-index:-10; }
       #supersized li.image-loading { background:#111 url(${baseurl}resource/imags/login/progress.gif) no-repeat center center; width:100%; height:100%; }
       #supersized li.image-loading img{ visibility:hidden; }
       #supersized li.prevslide img, #supersized li.activeslide img{ display:inline; }

        body {
            background: #f8f8f8;
            font-family: 'PT Sans', Helvetica, Arial, sans-serif;
            text-align: center;
            color: #fff;
        }

        .page-container {
            margin: 120px auto 0 auto;
        }

        h1 {
            font-size: 3em;
            font-weight: 700;
            text-shadow: 0 1px 4px rgba(0,0,0,.2);
        }

        .divForm {
            position: relative;
            width: 25%;
            margin: 15px auto 0 auto;
            text-align: center;
        }

        input {
            width: 90%;
            height: 42px;
            margin-top: 25px;
            padding: 0 15px;
            background: #2d2d2d; /* browsers that don't support rgba */
            background: rgba(45,45,45,.15);
            -moz-border-radius: 6px;
            -webkit-border-radius: 6px;
            border-radius: 6px;
            border: 1px solid #3d3d3d; /* browsers that don't support rgba */
            border: 1px solid rgba(255,255,255,.15);
            -moz-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
            -webkit-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
            box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
            font-family: 'PT Sans', Helvetica, Arial, sans-serif;
            font-size: 14px;
            color: #fff;
            text-shadow: 0 1px 2px rgba(0,0,0,.1);
            -o-transition: all .2s;
            -moz-transition: all .2s;
            -webkit-transition: all .2s;
            -ms-transition: all .2s;
        }

        input:-moz-placeholder { color: #fff; }
        input:-ms-input-placeholder { color: #fff; }
        input::-webkit-input-placeholder { color: #fff; }

        input:focus {
            outline: none;
            -moz-box-shadow:
                    0 2px 3px 0 rgba(0,0,0,.1) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
            -webkit-box-shadow:
                    0 2px 3px 0 rgba(0,0,0,.1) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
            box-shadow:
                    0 2px 3px 0 rgba(0,0,0,.1) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
        }

        button {
            cursor: pointer;
            width: 100%;
            height: 44px;
            margin-top: 40px;
            padding: 0;
            background: rgba(255,255,255,.25);
            -moz-border-radius: 6px;
            -webkit-border-radius: 6px;
            border-radius: 6px;
            border: 1px solid rgba(45,45,45,.25);
            -moz-box-shadow:
                    0 15px 30px 0 rgba(255,255,255,.25) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
            -webkit-box-shadow:
                    0 15px 30px 0 rgba(255,255,255,.25) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
            box-shadow:
                    0 15px 30px 0 rgba(255,255,255,.25) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
            font-family: 'PT Sans', Helvetica, Arial, sans-serif;
            font-size: 14px;
            font-weight: 700;
            color: #fff;
            text-shadow: 0 1px 2px rgba(0,0,0,.1);
            -o-transition: all .2s;
            -moz-transition: all .2s;
            -webkit-transition: all .2s;
            -ms-transition: all .2s;
        }

        button:hover {
            -moz-box-shadow:
                    0 15px 30px 0 rgba(255,255,255,.15) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
            -webkit-box-shadow:
                    0 15px 30px 0 rgba(255,255,255,.15) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
            box-shadow:
                    0 15px 30px 0 rgba(255,255,255,.15) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
        }

        button:active {
            -moz-box-shadow:
                    0 15px 30px 0 rgba(255,255,255,.15) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
            -webkit-box-shadow:
                    0 15px 30px 0 rgba(255,255,255,.15) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
            box-shadow:
                    0 5px 8px 0 rgba(0,0,0,.1) inset,
                    0 1px 4px 0 rgba(0,0,0,.1);

            border: 0px solid #ef4300;
        }

        .error {
            display: none;
            position: absolute;
            top: 27px;
            right: -55px;
            width: 40px;
            height: 40px;
            background: #2d2d2d; /* browsers that don't support rgba */
            background: rgba(45,45,45,.25);
            -moz-border-radius: 8px;
            -webkit-border-radius: 8px;
            border-radius: 8px;
        }

        .error span {
            display: inline-block;
            margin-left: 2px;
            font-size: 40px;
            font-weight: 700;
            line-height: 40px;
            text-shadow: 0 1px 2px rgba(0,0,0,.1);
            -o-transform: rotate(45deg);
            -moz-transform: rotate(45deg);
            -webkit-transform: rotate(45deg);
            -ms-transform: rotate(45deg);

        }

        .message {
            color: cyan;
            font-family: 'PT Sans', Helvetica, Arial, sans-serif;
            margin-top: 10px;
        }
    </style>
    <%--js--%>
    <script src="resource/scripts/jquery/jquery-2.2.3.min.js"></script>
    <script src="resource/scripts/login/supersized.3.2.7.min.js"></script>
    <script src="resource/scripts/login/supersized-init.js"></script>
    <script src="resource/scripts/login/scripts.js"></script>
    <script type="text/javascript">

    </script>
</head>

<body>

<div class="page-container">
    <h1>Sign in to start your session</h1>
    <div class="divForm">
        <input type="text" name="username" class="username" placeholder="Username">
        <input type="password" name="password" class="password" placeholder="Password">
        <button>开 启 旅 程</button>
        <div class="error"><span>+</span></div>
        <div class="message"><span>${msg}</span></div>
    </div>
</div>
</body>
</html>

