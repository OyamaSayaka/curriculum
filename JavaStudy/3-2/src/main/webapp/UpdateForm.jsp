<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,bean.bean"%>


<%
ArrayList<bean> list = (ArrayList<bean>) request.getAttribute("list");

String error = (String) request.getAttribute("error");
String product_code = (String) request.getAttribute("product_code");
String product_name = (String) request.getAttribute("product_name");
String price = (String) request.getAttribute("price");

%>
<!DOCTYPE html>
<html>
<head>
<title>データを変更、削除する</title>
</head>
<body>
	<div style="text-align: center">

		<h2 style=>商品の変更・削除</h2><br>
		
		<form action="/3-2/UpdateServlet" method="post">
			<table style="margin: 0 auto">
				<tr>
					<td style="width: 60">商品コード</td>
					<td><input type="hidden" name="product_name" value="<%=product_code%>"></input><%=product_code%></td>
				</tr>
				
				
				<tr>
					<td><input type="hidden" name="product_name" value="<%=product_name%>"></input><%=product_name%>
					<input type="hidden" name="price1" value="<%=price%>"></td>
				</tr>
				
				
				<tr>
					<td style="width: 70">商品名</td>
					<td><input type=text size="30" name="id"></input></td>
					<td><font color="red"></font></td>
				</tr>
				
				
				<tr>
					<td style="width: 60">単価</td>
					<td><input type=text size="10" name="price"></td>
					<td><font color="red"></font></td>
				</tr>
				
				
				<tr>
					<td colspan=2 style="text-align: right"><input type="submit"
						name='delete' value="削除"></td>
					<td colspan=2 style="text-align: right"><input type="submit"
						name='update' value="変更"></td>
				
				</tr>
			</table>
		</form>
		<a href="/3-2/menu.jsp">メニューに戻る</a> <br>
	</div>
</body>
</html>

