<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,bean.bean"%>
<%@page import="controller.SalesFormController"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<%ArrayList<bean> list = (ArrayList<bean>) session.getAttribute("list");%>
<%String nameError = (String) session.getAttribute("nameError");%>
<%String quantityError = (String) session.getAttribute("quantityError");%>
<%String error = (String) session.getAttribute("error");%>
<%
Date date = new Date();
SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
String today = df.format(date);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>売上登録</title>
</head>
<body>
	<div class=a style="margin: 0 auto;">
		<h2 style="text-align: left;">売上登録</h2>
		<hr style="height: 3; background-color: black;" />
		<p style="margin: 0 auto; text-align: center;">
			売上日
			<%=today%></p>
		<br>
		<form action="/3-2/SalesForm" method="post">
			<table style="margin: 0 auto;">
				<tr>
					<td style="width: 60;">商品名 <select name="name">
							<option disabled selected value="選択">選択してください</option>
							<option value="りんご">りんご</option>
							<option value="みかん">みかん</option>
							<option value="バナナ">バナナ</option>
							<option value="いちご">いちご</option>
							<option value="めろん">めろん</option>
							<option value="もも">もも</option>
							<option value="ぶどう">ぶどう</option>
							<option value="マンゴー">マンゴー</option>
							<option value="なし">なし</option>
							<option value="いよかん">いよかん</option>
							
					</select><font color="red">${nameError}</font> &emsp;
					</td>
					<td style="width: 70;">数量</td>
					<td><input type=text size="5" name="quantity"><font color="red">${quantityError}</font>&emsp;</td>
					<td><input type="submit" name=add value=追加></td>
				</tr>
			</table>
		</form>

		<hr style="height: 3; background-color: black;" />

		<div class=b style="text-align: center;">
			<table style="margin: 0 auto; border-collapse: collapse;">
				<tr>
					<th style="width: 100; border: 1px solid black;">商品名</th>
					<th style="width: 70; border: 1px solid black;">数量</th>
				</tr>
				<%
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
				%>
				<tr>
					<td style="width: 100; border: 1px solid black;"><%=list.get(i).getName()%></td>
					<td style="width: 600; border: 1px solid black;"><%=list.get(i).getQuantity()%></td>
				<tr>
					<%
					}
					%>
			</table>
			<br>
			<form action="/3-2/SalesRegistrationController" method="post">
				<input type="submit" name="registration" style="text-align: center;"
					value=登録>
				<%
				}
				%>
			</form>
			<a href="/3-2/menu.jsp">メニューに戻る</a> <br>
		</div>
	</div>
</body>
</html>