<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>错误页面</title>
    <!--静态包含base标签、css样式、jquery文件--->
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: #ff0000;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="/static/img/logo.gif">
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

   很抱歉，你访问的资源不存在! <br>
    <a href="index.jsp">返回首页</a>

</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>