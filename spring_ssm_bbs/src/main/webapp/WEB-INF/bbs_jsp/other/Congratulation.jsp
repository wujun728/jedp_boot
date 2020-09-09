<%--
  Created by IntelliJ IDEA.
  User: zlq
  Date: 2019/1/10
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发帖成功</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/congratulate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/congratulateindex.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/congratulatepublic.css">

    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery_2.1.4_jquery.js"></script>

    <script type="text/javascript">
        $(function () {
            var h = $(window).height();
            $('body').height(h);
            $('.mianBox').height(h);
            centerWindow(".tipInfo");
        });
        //        方法统一调用
        function centerWindow(a) {
            center(a);

//            自适应窗口
            $(window).bind('scroll resize', function () {
                center(a);

            });
        };

        //1.居中方法，传入需要剧中的标签
        function center(a) {
            var wWidth = $(window).width();
            var wHeight = $(window).height();
            var boxWidth = $(a).width();
            var boxHeight = $(a).height();
            var scrollTop = $(window).scrollTop();
            var scrollLeft = $(window).scrollLeft();
            var top = scrollTop + (wHeight - boxHeight) / 2;
            var left = scrollLeft + (wWidth - boxWidth) / 2;
            $(a).css({
                "top": top,
                "left": left
            });
        }

    </script>
</head>
<body>
<div class="mianBox">
    <img src="${pageContext.request.contextPath}/resource/images/CongratulateImage/yun0.png" alt="" class="yun yun0">
    <img src="${pageContext.request.contextPath}/resource/images/CongratulateImage/yun1.png" alt="" class="yun yun1">
    <img src="${pageContext.request.contextPath}/resource/images/CongratulateImage/yun2.png" alt="" class="yun yun2">
    <img src="${pageContext.request.contextPath}/resource/images/CongratulateImage/bird.png" alt="" class="bird">
    <img src="${pageContext.request.contextPath}/resource/images/CongratulateImage/san.png" alt="" class="san">

    <div class="tipInfo">
        <div class="in">
            <div class="textThis">
                <H2>发帖成功，5秒后即将返回主页！</H2>
                <p>
                 <span>页面自动<<a href="/city/bbs" id="href">跳转</a>
                </span>
                    <span>等待<b id="wait">5</b>秒</span>

                </p>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    (function () {
        var wait = document.getElementById('wait'), href = document.getElementById('href').href;
        var interval = setInterval(function () {
            var time = --wait.innerHTML;
            if (time <= 0) {
                $.ajax({
                    type: "get",
                    url:"/Forum/getForum",
                    data:{pageNo:1},
                    datatype:"json",
                    success:function () {
                        window.location.href = href;
                    }
                });
                clearInterval(interval);
            }
            ;
        }, 1000);
    })();
</script>


</body>
</html>
