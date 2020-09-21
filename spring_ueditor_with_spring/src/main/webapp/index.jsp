<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ueditor with springmvc - 品互网络 www.pinhuba.com</title>

<script src="<%=request.getContextPath()%>/ueditor/ueditor.config.js" type="text/javascript" ></script>
<script src="<%=request.getContextPath()%>/ueditor/ueditor.all.min.js" type="text/javascript" ></script>
<script src="<%=request.getContextPath()%>/ueditor/lang/zh-cn/zh-cn.js" type="text/javascript" ></script>

</head>
<body>
<h1>ueditor with springmvc - 品互网络 www.pinhuba.com</h1>
<script id="txt" name="txt" type="text/plain" style="width:680px;"></script>
<script type="text/javascript">
	var editor= UE.getEditor('txt',{
		imageUrl:"<%=request.getContextPath()%>/ueditor/upload.do?Type=Image",
   		fileUrl:"<%=request.getContextPath()%>/ueditor/upload.do?Type=File",
   		catcherUrl:"<%=request.getContextPath()%>/ueditor/getRemoteImage.do",
   		imageManagerUrl:"<%=request.getContextPath()%>/ueditor/imageManager.do?picNum=50&insite=false",
   		wordImageUrl:"<%=request.getContextPath()%>/ueditor/upload.do?Type=File",
   		getMovieUrl:"<%=request.getContextPath()%>/ueditor/getmovie.do",
   		videoUrl:"<%=request.getContextPath()%>/ueditor/upload.do?Type=Media"
	});
</script>
</body>
</html>