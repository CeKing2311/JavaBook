<%--
  Created by IntelliJ IDEA.
  User: cjq
  Date: 2020-12-21
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="orderServlet?action=getOrder">我的订单</a>
    <a href="userServlet?action=loginOut">注销</a>
    <a href="index.jsp">返回</a>
</div>
