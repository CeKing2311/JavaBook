<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <!--静态包含base标签、css样式、jquery文件--->
    <%@include file="/pages/common/head.jsp" %>
</head>
<script type="text/javascript">
    $(function () {
        //给删除的
        $("a.deleteBook").click(function () {
            //返回true表示点击了确定，返回false表示取消
            return confirm("确定要删除图书【" + $(this).parent().parent().find("td:first").text() + "】吗？");
        });
    })
</script>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%-- 静态包含manager管理模块的菜单  --%>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.page.data}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=bookInfo&id=${book.id}&pageIndex=${requestScope.page.pageIndex}">修改</a></td>
                <td><a class="deleteBook" href="manager/bookServlet?action=delete&id=${book.id}&pageIndex=${requestScope.page.pageIndex}">删除</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?totalPage=${requestScope.page.totalPage}">添加图书</a></td>
        </tr>
    </table>
    <div id="page_nav">
        <c:if test="${requestScope.page.pageIndex>1}">
            <a href="manager/bookServlet?action=queryPageList&pageIndex=1">首页</a>
            <a href="manager/bookServlet?action=queryPageList&pageIndex=${requestScope.page.pageIndex-1}">上一页</a>
        </c:if>

        <%-- 页码的输出--%>
        <c:choose>
            <c:when test="${requestScope.page.totalPage<=5}">
                <c:set var="begin" value="1"></c:set>
                <c:set var="end" value="${requestScope.page.totalPage}"></c:set>
            </c:when>
            <c:when test="${requestScope.page.totalPage>5}">
                <c:choose>
                    <%-- 小情况1：当前页码为前面三个：1,2,3的情况，页码范围是1-5--%>
                    <c:when test="${requestScope.page.pageIndex<=3}">
                        <c:set var="begin" value="1"></c:set>

                        <c:set var="end" value="5"></c:set>

                    </c:when>
                    <%-- 小情况2：当前页码为最后三个，8，9，10，页码范围是 ：总页码减 4--总页码--%>
                    <c:when test="${requestScope.page.pageIndex>=requestScope.page.totalPage - 3}">
                        <c:set var="begin" value="${requestScope.page.totalPage -4}"></c:set>
                        <c:set var="end" value="${requestScope.page.totalPage}"></c:set>

                    </c:when>
                    <%--  小情况3：4，5，6，7，8，页码范围是：当前页码减2--当前页码加2--%>
                    <c:otherwise>
                        <c:set var="begin" value="${requestScope.page.pageIndex -2}"></c:set>
                        <c:set var="end" value="${requestScope.page.pageIndex +2}"></c:set>

                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
        <c:forEach begin="${begin}" end="${end}"
                   var="i">
            <c:if test="${ i == requestScope.page.pageIndex}">
                【${i}】
            </c:if>
            <c:if test="${ i != requestScope.page.pageIndex}">
                <a href="manager/bookServlet?action=queryPageList&pageIndex=${i}">${i}</a>
            </c:if>
        </c:forEach>

        <c:if test="${requestScope.page.pageIndex<requestScope.page.totalPage}">
            <a href="manager/bookServlet?action=queryPageList&pageIndex=${requestScope.page.pageIndex+1}">下一页</a>
            <a href="manager/bookServlet?action=queryPageList&pageIndex=${requestScope.page.totalPage}">末页</a>
        </c:if>
        共${requestScope.page.totalPage}页，${requestScope.page.totalCount}条记录 到第<input
            value="${requestScope.page.pageIndex}" name="pn" id="pn_input"/>页
        <input id="searchPage" type="button" value="确定">
        <script type="text/javascript">
            $(function () {
                $("#searchPage").click(function () {
                    let pageIndex = $("#pn_input").val();
                    if (pageIndex < 1) {
                        pageIndex = 1;
                    } else if (pageIndex >${requestScope.page.totalPage}) {
                        pageIndex =
                        ${requestScope.page.totalPage}
                    }
                    location.href = "${pageScope.basePath}manager/bookServlet?action=queryPageList&pageIndex=" + pageIndex;
                })
            })
        </script>
    </div>
</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>