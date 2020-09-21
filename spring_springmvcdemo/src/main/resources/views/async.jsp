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

    deferred();

    function  deferred() {
        $.get('defer', function(data){
            console.log(data);
            deferred();
        });
    }

</script>
</body>
</html>
