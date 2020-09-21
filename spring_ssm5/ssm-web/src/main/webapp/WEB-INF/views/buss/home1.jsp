<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page  isELIgnored="false" pageEncoding="utf-8" %>
<html>
<head>
	<jsp:include page="../common/tag.jsp"></jsp:include>
	<meta http-equiv="pragma" content="no-cache">
	<title>Home</title>
	<meta charset="UTF-8">
	<script src="resource/scripts/jquery/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
		function checkRole() {
			$.get("rolePage", function(data){
				alert("Data Loaded: " + data);
			});
		}
		function checkPermi() {
			$.get("premissPage", function(data){
				alert("Data Loaded: " + data);
			});
		}
		function checkpremissDeletePage() {
			$.get("premissDeletePage", function(data){
				alert("Data Loaded: " + data);
			});
		}
	</script>
</head>
<body>
<P>  首页 </P>
<c:choose>
	<c:when test="${!empty user}">
		<a href="acc/loginOut">登出</a>
		<p>用户信息</p>
		<ul>
			<li>用户名：${user.userName}</li>
			<li>年龄：${user.age}</li>
			<li>性别：${user.sex==1?'男':'女'}</li>
			<li></li>
		</ul>
	</c:when>
	<c:otherwise>
		<a href="loginPage">登陆页</a>
	</c:otherwise>
</c:choose>
<button style="color: blue" onclick="checkRole()">验证是否有ROOT角色 </button>
<button style="color: blue" onclick="checkPermi()">验证是否有用户删除的权限 </button>
<button style="color: blue" onclick="checkpremissDeletePage()">验证是否有权限删除的权限 </button>
</body>
</html>
