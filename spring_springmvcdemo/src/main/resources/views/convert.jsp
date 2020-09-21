<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>convert</title>
</head>
<script type="text/javascript" src="static/js/jquery-1.10.2.min.js"></script>
<body>
    <div id="resp"><input type="button" value="请求" onclick="req()"></div>

<script type="text/javascript">
    function req() {
        $.ajax({
            url: "convert",
            data: "1-zhangsan",
            type: "post",
            contentType: "application/x-wisely",   //contentType: 指定请求的媒体类型.  此处自定为我们自定义的媒体类型
            success: function (data) {
                $("#resp").html(data);
            }
        });
    }
</script>
</body>
</html>
