<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,bean.bean"%>
<%@page import="controller.UpdateController"%>
<%@page import="controller.SelectController"%>
<%@page import="controller.UpdateFormController"%>
<%String productNameError = (String) request.getAttribute("productNameError");%>
<%String priceError = (String) request.getAttribute("priceError");%>
<%String error = (String) request.getAttribute("error");%>
<%bean editbean = (bean) request.getAttribute("editform");%>



<!DOCTYPE html>
<html>
<head>
<title>データを変更、削除する</title>
</head>
<body>
	<div style="text-align: center">

		<h2 style=>商品の変更・削除</h2>
		<br>
		<%if(error != null) %>
		<div><%=error%></div>
		<form action="/3-2/UpdateServlet" method="post">
			<table style="margin: 0 auto">
				
				<tr>
					<td style="width: 60">商品コード</td>
					<td><input type="hidden" name="product_code"value="<%=editbean.getCode()%>"><%=editbean.getCode()%></td>
				</tr>

				<tr>
					<td>
					 <input type="hidden"name="serialprice" value="<%=editbean.getPrice()%>>">
					<input type="submit" name="selected_datetime"	value="<%=editbean.getUpdateTime()%>"></td>
					
				</tr>



				<tr>
					<td style="width: 60">商品名</td>
					<td><input type=text size="20" name="name"></input></td>
					<td><font color="red">${productNameError}</font></td>
				</tr>


				<tr>
					<td style="width: 60">単価</td>
					<td><input type=text size="20" name="price"></td>
					<td><font color="red">${priceError}</font></td>
				</tr>


				<tr>
					<td colspan=2 style="text-align: right"><input type="submit"
						name='delete' value="削除"></td>
					<td colspan=2 style="text-align: right"><input type="submit"
						name='update' value="変更"></td>
				</tr>


			</table>

		</form>

		<a href="/3-2/SelectForm.jsp">検索画面に戻る</a> <br> <a
			href="/3-2/menu.jsp">メニューに戻る</a> <br>
	</div>
</body>
</html>

