<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
    String headPath = request.getContextPath();
    String headBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+headPath+"/";
%>
<%
    response.setHeader("cache-control", "max-age=5,public,must-revalidate"); //one day
    response.setDateHeader("expires", -1);
    String cdntime = String.valueOf(System.currentTimeMillis());
    request.setAttribute("cdntime",cdntime);
%>
<c:set value="${pageContext.request.contextPath}" var="webRoot" />
<%
    String webRoot = "http://" + request.getServerName() + ":" + request.getServerPort();
    request.setAttribute("webRoot",webRoot);

%>

<script>
    //容器相对路径
    var CONTEXT_PATH='<%=request.getContextPath()%>';
</script>

<script>window.PROJECT_CONTEXT = "${webRoot}/";</script>

<%--Easyui相关样式--%>
<link rel="stylesheet" type="text/css" href="${webRoot}/resources/thiedparty/jquery-easyui-1.4.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${webRoot}/resources/thiedparty/jquery-easyui-1.4.5/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${webRoot}/resources/thiedparty/jquery-easyui-1.4.5/themes/color.css">

<%--Bootstrap CSS--%>
<%--<link rel="stylesheet" type="text/css" href="${webRoot}/resources/thiedparty/bootstrap-3.3.5-dist/css/bootstrap.min.css">--%>

<%--Bootstrap-table CSS--%>
<%--<link rel="stylesheet" type="text/css" href="${webRoot}/resources/thiedparty/bootstrap-table-master/dist/bootstrap-table.min.css">--%>



<%--引入jQuery--%>
<script type="text/javascript" src="${webRoot}/resources/thiedparty/jquery-easyui-1.4.5/jquery.min.js"></script>
<script type="text/javascript" src="${webRoot}/resources/thiedparty/jquery-easyui-1.4.5/easyloader.js"></script>
<%--<script type="text/javascript" src="${webRoot}/resources/js/jQuery-core/jquery-1.10.2.min.js"></script>--%>


<%--Bootstrap--%>
<%--<script type="text/javascript" src="${webRoot}/resources/thiedparty/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>--%>

<!-- Bootstrap-table  JS  -->
<%--<script type="text/javascript" src="${webRoot}/resources/thiedparty/bootstrap-table-master/dist/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${webRoot}/resources/thiedparty/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js"></script>--%>


<%--Easyui相关--%>
<script type="text/javascript" src="${webRoot}/resources/thiedparty/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>


<%--引入ECharts--%>
<script type="text/javascript" src="${webRoot}/resources/thiedparty/echarts.min.js"></script>


<%--工具js--%>
<script type="text/javascript" src="${webRoot}/resources/js/util.js"></script>
