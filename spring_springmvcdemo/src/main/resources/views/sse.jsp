<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="static/js/jquery-1.10.2.min.js"></script>
</head>
<body>
<div id="msgPush">
 </div>
<script type="text/javascript">

    if (!!window.EventSource) {  //EventSource 是sse的客户端,只有新式浏览器才支持
        var source = new EventSource('push');
        s = '';
        source.addEventListener('message', function(e) {
            s += e.data +'<br/>';
            $("#msgPush").html(s);
        });

        source.addEventListener('open', function(e) {
            console.log('连接打开');
        }, false);

        source.addEventListener('error', function(e) {
            if (e.readyState == Event.CLOSED) {
                console.log('连接关闭');
            } else  {
                console.log(e.readyState);
            }
        }, false);
    } else {
        console.log('浏览器不支持SSE');
    }
</script>
</body>
</html>
