<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<!--静态包含base标签、css样式、jquery文件--->
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cartInfo.items}">
				<tr>
					<td colspan="5"> <a href="${basePath}">当前购物车为空，请选择商品！</a> </td>
				</tr>
			</c:if>
			<c:if test="${ not empty sessionScope.cartInfo.items}">
				<c:forEach items="${sessionScope.cartInfo.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
<%--							<input type="button" value="-">--%>
							<input type="text" min="0" bookId="${entry.value.id}" name="count" style="width: 36px;text-align: center;" value="${entry.value.count}">
<%--							<input type="button" value="+">--%>
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteClass" href="cartServlet?action=deleteItem&Id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

		</table>
		<c:if test="${ not empty sessionScope.cartInfo.items }">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cartInfo.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cartInfo.totalPrice}</span>元</span>
				<span class="cart_span"><a class="clearClass" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="pages/cart/checkout.jsp">去结账</a></span>
			</div>
		</c:if>
	</div>
	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp" %>
<script type="text/javascript">
	$(function (){
		$("a.deleteClass").click(function () {
			let name = $(this).parent().parent().find("td:first").text();
		   return confirm("您确定要删除商品【"+name+"】吗？");
		})
		$("a.clearClass").click(function () {
			return confirm("您确定要清空购物车吗？");
		})
		//失去焦点事件
		$("input[name='count']").change(function () {
			let name = $(this).parent().parent().find("td:first").text();
			let id = $(this).attr("bookId");
			let newCount = $(this).val();
			let cof = confirm("确定要修改商品【"+name+"】数量为:"+newCount+"吗？");
			if (cof){
				location.href = "${basePath}cartServlet?action=updateCount&Id="+id+"&Count="+newCount;
			}else {
				$(this).val(this.defaultValue);
			}
		})
	})
</script>
</body>
</html>