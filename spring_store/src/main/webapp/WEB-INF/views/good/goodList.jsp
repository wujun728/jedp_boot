<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<title>商品列表</title>
		<link rel="stylesheet" type="text/css"
			href="css/bootstrap.css">
		<link rel="stylesheet" type="text/css"
			href="css/theme.css">
		<link rel="stylesheet"
			href="css/font-awesome.css">
		<script src="js/jquery-2.1.0.min.js" type="text/javascript"></script>
	</head>

	<body class="content1">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="page-header">
						<h3>
							后台数据添加
						</h3>
					</div>
					<ul class="nav nav-tabs">
						<li class="active">
							<a href="#">数据列表</a>
						</li>
						<li class="disabled">
							<a href="#">返回首页</a>
						</li>
					</ul>
					<form class="form-search">
						<input class="input-medium search-query" type="text" /> <button type="submit" class="btn">查找</button>
					</form>
					<table class="table">
						<thead>
							<tr>
								<th>
									商品ID
								</th>
								<th>
									商品图
								</th>
								<th>
									商品编号
								</th>
								<th>
									商品名称
								</th>
								<th>
									商品价格
								</th>
								<th>
									商品数量
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							
							<tr>
								<td>
									1
								</td>
								<td>
									1
								</td>
								<td>
									1
								</td>
								<td>
									TB - Monthly
								</td>
								<td>
									01/04/2012
								</td>
								<td>
									Default
								</td>
							</tr>
						</tbody>
					</table>
					<script type="text/javascript">
					 window.onload=function(){
							$.get("/getgoods",function(data){
								var tbody=$("table>tbody");
								$.each(data,function(i,item){
									var tr=$("<tr></tr>");
									$("<td></td>").html(item.goodid).appendTo(tr);
									$("<td></td>").html(
											$("<img></img>").attr("src","item.goodimg")	
										).appendTo(tr);
									$("<td></td>").html(item.goodno).appendTo(tr);
									$("<td></td>").html(item.goodname).appendTo(tr);
									$("<td></td>").html(item.goodprice).appendTo(tr);
									$("<td></td>").html(item.goodnum).appendTo(tr);
									$("<td></td>").html(
										$("<a></a>").html("删除").attr("href","/good/gooddelete?goodid="+item.goodid),	
										$("<a></a>").html("修改").attr("href","/good/goodupdate?goodid="+item.goodid)
									).appendTo(tr);
									//tbody.append(tr);
									tr.appendTo(tbody);
								})
							},"json")
						}
					</script>
					<div class="pagination pagination-right">
						<ul>
							<li>
								<a href="#">上一页</a>
							</li>
							<li>
								<a href="#">1</a>
							</li>
							<li>
								<a href="#">2</a>
							</li>
							<li>
								<a href="#">3</a>
							</li>
							<li>
								<a href="#">4</a>
							</li>
							<li>
								<a href="#">5</a>
							</li>
							<li>
								<a href="#">下一页</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
