<%--
  Created by IntelliJ IDEA.
  User: cjq
  Date: 2020-12-22
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageIndex>1}">
        <a href="${requestScope.page.url}&pageIndex=1">首页</a>
        <a href="${requestScope.page.url}&pageIndex=${requestScope.page.pageIndex-1}">上一页</a>
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
            <a href="${requestScope.page.url}&pageIndex=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <c:if test="${requestScope.page.pageIndex<requestScope.page.totalPage}">
        <a href="${requestScope.page.url}&pageIndex=${requestScope.page.pageIndex+1}">下一页</a>
        <a href="${requestScope.page.url}&pageIndex=${requestScope.page.totalPage}">末页</a>
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
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageIndex=" + pageIndex;
            })
        })
    </script>
</div>