<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/view/common/tagPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-CN">
<%
    String path = request.getContextPath();
%>
<head>
    <title>完整demo</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${webRoot}/resources/css/ueditor/upload.css">
    <script src="${webRoot}/resources/thiedparty/ueditor_jsp/ueditor.config.js"></script>
    <script src="${webRoot}/resources/thiedparty/ueditor_jsp/ueditor.all.min.js"></script>
    <script src="${webRoot}/resources/thiedparty/ueditor_jsp/lang/zh-cn/zh-cn.js"></script>
    <script src="${webRoot}/resources/js/ueditor/upload.js"></script>

    <style type="text/css">
       /* div{
            width:100%;
        }*/
    </style>
</head>
<body>
<div style="padding: 1% 0 0 1%">
<form id="uploadForm" method="post">
    <div style="margin-bottom: 20px">
        <span class="filterTip">标题：</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div id="tt" name="tt" style="width: 200px" class="easyui-textbox" data-options="required:true"></div><br>
        <span class="filterTip">一级目录：</span><div id="spCol" name="spCol" style="width: 200px" class="easyui-combobox"></div><br>
        <span class="filterTip">二级目录：</span><div id="childCol" name="childCol" style="width: 200px" class="easyui-combobox" ></div><br>
        <span class="filterTip">封面图片：</span>
            <input type="text" id="f_picture" name="picture" />
            <p style="color:#9a9a9a;margin:6px 3px 0;font-size:12px;">仅支持JPG格式</p>

        <span class="filterTip">视频文件：</span>
        <input type="text" id="f_video" name="video" />
        <p style="color:#9a9a9a;margin:6px 3px 0;font-size:12px;">仅支持MP4格式</p>

    </div>

    <script id="f_content" type="text/plain" name="content"
            style="width: 76%; height: 350px;">

    </script> <script type="text/javascript">
        var ue = UE.getEditor('f_content');
    </script><br>
    <a href="javascript:void(0)" class="easyui-linkbutton c1" onclick="submitForm()">提交</a>
</form>
</div>
<%--<div>--%>
    <%--<h1>完整demo</h1>--%>
    <%--<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>--%>
<%--</div>--%>
<%--<div id="btns">--%>
    <%--<div>--%>
        <%--<button onclick="getAllHtml()">获得整个html的内容</button>--%>
        <%--<button onclick="getContent()">获得内容</button>--%>
        <%--<button onclick="setContent()">写入内容</button>--%>
        <%--<button onclick="setContent(true)">追加内容</button>--%>
        <%--<button onclick="getContentTxt()">获得纯文本</button>--%>
        <%--<button onclick="getPlainTxt()">获得带格式的纯文本</button>--%>
        <%--<button onclick="hasContent()">判断是否有内容</button>--%>
        <%--<button onclick="setFocus()">使编辑器获得焦点</button>--%>
        <%--<button onmousedown="isFocus(event)">编辑器是否获得焦点</button>--%>
        <%--<button onmousedown="setblur(event)" >编辑器失去焦点</button>--%>

    <%--</div>--%>
    <%--<div>--%>
        <%--<button onclick="getText()">获得当前选中的文本</button>--%>
        <%--<button onclick="insertHtml()">插入给定的内容</button>--%>
        <%--<button id="enable" onclick="setEnabled()">可以编辑</button>--%>
        <%--<button onclick="setDisabled()">不可编辑</button>--%>
        <%--<button onclick=" UE.getEditor('editor').setHide()">隐藏编辑器</button>--%>
        <%--<button onclick=" UE.getEditor('editor').setShow()">显示编辑器</button>--%>
        <%--<button onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高</button>--%>
    <%--</div>--%>

    <%--<div>--%>
        <%--<button onclick="getLocalData()" >获取草稿箱内容</button>--%>
        <%--<button onclick="clearLocalData()" >清空草稿箱</button>--%>
    <%--</div>--%>

<%--</div>--%>
<%--<div>--%>
    <%--<button onclick="createEditor()">--%>
        <%--创建编辑器</button>--%>
    <%--<button onclick="deleteEditor()">--%>
        <%--删除编辑器</button>--%>
<%--</div>--%>

<%--<script type="text/javascript">--%>

    <%--//实例化编辑器--%>
    <%--//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例--%>
    <%--var ue = UE.getEditor('editor');--%>


    <%--function isFocus(e){--%>
        <%--alert(UE.getEditor('editor').isFocus());--%>
        <%--UE.dom.domUtils.preventDefault(e)--%>
    <%--}--%>
    <%--function setblur(e){--%>
        <%--UE.getEditor('editor').blur();--%>
        <%--UE.dom.domUtils.preventDefault(e)--%>
    <%--}--%>
    <%--function insertHtml() {--%>
        <%--var value = prompt('插入html代码', '');--%>
        <%--UE.getEditor('editor').execCommand('insertHtml', value)--%>
    <%--}--%>
    <%--function createEditor() {--%>
        <%--enableBtn();--%>
        <%--UE.getEditor('editor');--%>
    <%--}--%>
    <%--function getAllHtml() {--%>
        <%--alert(UE.getEditor('editor').getAllHtml())--%>
    <%--}--%>
    <%--function getContent() {--%>
        <%--var arr = [];--%>
        <%--arr.push("使用editor.getContent()方法可以获得编辑器的内容");--%>
        <%--arr.push("内容为：");--%>
        <%--arr.push(UE.getEditor('editor').getContent());--%>
        <%--alert(arr.join("\n"));--%>
    <%--}--%>
    <%--function getPlainTxt() {--%>
        <%--var arr = [];--%>
        <%--arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");--%>
        <%--arr.push("内容为：");--%>
        <%--arr.push(UE.getEditor('editor').getPlainTxt());--%>
        <%--alert(arr.join('\n'))--%>
    <%--}--%>
    <%--function setContent(isAppendTo) {--%>
        <%--var arr = [];--%>
        <%--arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");--%>
        <%--UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);--%>
        <%--alert(arr.join("\n"));--%>
    <%--}--%>
    <%--function setDisabled() {--%>
        <%--UE.getEditor('editor').setDisabled('fullscreen');--%>
        <%--disableBtn("enable");--%>
    <%--}--%>

    <%--function setEnabled() {--%>
        <%--UE.getEditor('editor').setEnabled();--%>
        <%--enableBtn();--%>
    <%--}--%>

    <%--function getText() {--%>
        <%--//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容--%>
        <%--var range = UE.getEditor('editor').selection.getRange();--%>
        <%--range.select();--%>
        <%--var txt = UE.getEditor('editor').selection.getText();--%>
        <%--alert(txt)--%>
    <%--}--%>

    <%--function getContentTxt() {--%>
        <%--var arr = [];--%>
        <%--arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");--%>
        <%--arr.push("编辑器的纯文本内容为：");--%>
        <%--arr.push(UE.getEditor('editor').getContentTxt());--%>
        <%--alert(arr.join("\n"));--%>
    <%--}--%>
    <%--function hasContent() {--%>
        <%--var arr = [];--%>
        <%--arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");--%>
        <%--arr.push("判断结果为：");--%>
        <%--arr.push(UE.getEditor('editor').hasContents());--%>
        <%--alert(arr.join("\n"));--%>
    <%--}--%>
    <%--function setFocus() {--%>
        <%--UE.getEditor('editor').focus();--%>
    <%--}--%>
    <%--function deleteEditor() {--%>
        <%--disableBtn();--%>
        <%--UE.getEditor('editor').destroy();--%>
    <%--}--%>
    <%--function disableBtn(str) {--%>
        <%--var div = document.getElementById('btns');--%>
        <%--var btns = UE.dom.domUtils.getElementsByTagName(div, "button");--%>
        <%--for (var i = 0, btn; btn = btns[i++];) {--%>
            <%--if (btn.id == str) {--%>
                <%--UE.dom.domUtils.removeAttributes(btn, ["disabled"]);--%>
            <%--} else {--%>
                <%--btn.setAttribute("disabled", "true");--%>
            <%--}--%>
        <%--}--%>
    <%--}--%>
    <%--function enableBtn() {--%>
        <%--var div = document.getElementById('btns');--%>
        <%--var btns = UE.dom.domUtils.getElementsByTagName(div, "button");--%>
        <%--for (var i = 0, btn; btn = btns[i++];) {--%>
            <%--UE.dom.domUtils.removeAttributes(btn, ["disabled"]);--%>
        <%--}--%>
    <%--}--%>

    <%--function getLocalData () {--%>
        <%--alert(UE.getEditor('editor').execCommand( "getlocaldata" ));--%>
    <%--}--%>

    <%--function clearLocalData () {--%>
        <%--UE.getEditor('editor').execCommand( "clearlocaldata" );--%>
        <%--alert("已清空草稿箱")--%>
    <%--}--%>
<%--</script>--%>
</body>

</html>
