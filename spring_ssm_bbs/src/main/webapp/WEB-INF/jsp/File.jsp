<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- jQuery标签库-->
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.messager.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery_2.1.4_jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery_form.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/layui/css/layui.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/layer/mobile/need/layer.css">--%>
    <script src="${pageContext.request.contextPath}/resource/layui/layui.js" charset="utf-8"></script>
    <%--<script src="${pageContext.request.contextPath}/resource/layer/layer.js" charset="utf-8"></script>--%>
</head>
<body class="layui-layout-body">
<form action="/FileUpload" id="uploadForm" method="post" enctype="multipart/form-data">

    <input type="file" name="userUpload">
    <input type="text" name="text" value="">
    <input type="text" name="id" value="">
    <input type="submit" value="提交" id="uploadFile">

</form>


<form method="POST"  enctype="multipart/form-data" id="form1" action="#">

    <label>上传文件: </label>
    <input id="upfile" type="file" name="upfile"><br> <br>

    <input type="submit" value="表单提交" onclick="return checkData()">
    <input type="button" value="ajax提交" id="btn" name="btn" >

</form>

<div id="sse">
    <a href="javascript:WebSocketTest()">运行 WebSocket</a>
</div>
</body>

<script>
    $(function () {
        $("#uploadFile").click(function () {
            $("#uploadForm").submit();
        });
    });
</script>

<script type="text/javascript">
    function WebSocket() {
        if ("WebSocket" in window) {
            alert("支持的。。。。");

            //打开一个WebSocket
            var web = new WebSocket("ws://localhost:8080/Forum/getForum");

            //打开连接
            web.onopen = function () {
                //Web Socket 已连接上，使用send()方法发送数据
                web.send("发送数据");
                alert("数据发送中。。。。");
            };

            //接收数据操作
            web.onmessage = function (data) {
                var received_msg = data;
                alert("数据接收。。。。");

            };

            //关闭连接
            web.onclose = function () {

                alert(
                    "连接关闭"
                );

            };

        } else {
            //浏览器不支持
            alert("浏览器不支持WebSocket!");
        }

    }



    /*  ajax 方式上传文件操作 */
    $(document).ready(function(){
        $("#btn").click(function(){ if(checkData()){
            $('#form1').ajaxSubmit({
                url:'uploadExcel/ajax',
                dataType: 'text',
                success: resutlMsg,
                error: errorMsg
            });
            function resutlMsg(msg){
                alert(msg);
                $("#upfile").val("");
            }
            function errorMsg(){
                alert("导入excel出错！");
            }
        }
        });
    });

    //JS校验form表单信息
    function checkData(){
        var fileDir = $("#upfile").val();
        var suffix = fileDir.substr(fileDir.lastIndexOf("."));
        if("" == fileDir){
            alert("选择需要导入的Excel文件！");
            return false;
        }
        if(".xls" != suffix && ".xlsx" != suffix ){
            alert("选择Excel格式的文件导入！");
            return false;
        }
        return true;
    }
</script>
</html>