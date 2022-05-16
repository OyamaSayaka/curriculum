<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,bean.bean"%>
<%@page import="controller.SalesRegistrationController"%>
<%ArrayList<bean> list = (ArrayList<bean>) session.getAttribute("list");%>
<%String name = (String) session.getAttribute("name");%>
<%String price = (String) session.getAttribute("price");%>
<%String error = (String) session.getAttribute("error");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>売上登録</title>
</head>
<body>

	<h2 style="text-align: left;">売上登録</h2>
	<hr style="height: 3; background-color: black;" />


	<p>売上日</p>


	<form action="/3-2/SalesRegistration" method="post">
		<table style="margin: 0 auto;">
			<tr>
				<td style="width: 60;">商品名 <select name="productName">
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

				</select>
				&emsp;</td>
				<td style="width: 70;">数量</td>
				<td><input type=text size="5" name="quantity">&emsp;</td>
				<td><input type="submit" name=add value=追加></td>
			</tr>
		</table>
	</form>
	<hr style="height: 3; background-color: black;" />

	<form action="/3-2/SalesRegistrationComplete" method="post">

		<table style="margin: 0 auto; border-collapse: collapse;">
			<tr style="text-align: center;">

				<th style="width: 100; border: 1px solid black;">商品名</th>
				<th style="width: 70; border: 1px solid black;">数量</th>
			</tr>
			
			
			
			<tr>
			
			
			<%
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {%>
				
				<td style="width: 100; border: 1px solid black;"><%=list.get(i).getName()%></td>
				<td style="width: 600; border: 1px solid black;"><%=list.get(i).getQuantity()%></td>
					<%
			}
			}
			%>
			<tr>
		</table>
		<input type="submit" style="text-align: right;" value=登録>
	</form>



<%=error%>






	<a href="/3-2/menu.jsp">メニューに戻る</a>
	<br>

</body>


</html>