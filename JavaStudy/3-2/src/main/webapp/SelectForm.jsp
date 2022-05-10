<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,bean.bean"%>


<%
ArrayList<bean> list = (ArrayList<bean>) request.getAttribute("list");

String error = (String) request.getAttribute("error");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全てのデータを表示する</title>
</head>
<body>

	<div style="text-align: center">

		<h2>商品検索</h2>

		<form action="/3-2/SerchServlet" method="post">
			商品名：<input type="text" name="name" size="20" /> <input type="submit"
				value="検索" />

			<table style="margin: 0 auto" border="1">
				<tr>
					<th style="width: 100">商品コード</th>
					<th style="width: 100">商品名</th>
					<th style="width: 100">単価</th>
					<th style="width: 100">登録日時</th>
					<th style="width: 100">更新日時</th>
					<th style="width: 100">削除日時</th>
					<th style="width: 100">操作</th>

				</tr>


				<%
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
				%>

				<tr>
					<td style="text-align: center; width: 100"><%=list.get(i).getCode()%></td>
					<td style="text-align: center; width: 100"><%=list.get(i).getName()%></td>
					<td style="text-align: center; width: 250"><%=list.get(i).getPrice()%></td>
					<td style="text-align: center; width: 250"><%=list.get(i).getRegisterdDate()%></td>
					<td style="text-align: center; width: 250"><%=list.get(i).getUpdateTime()%></td>
					<td style="text-align: center; width: 250"><%=list.get(i).getDeleteDate()%></td>
					<td style="text-align: center; width: 100"><input type="submit" value="編集"><input type="hidden"value=<%=list.get(i).getCode()%>></td>
				</tr>

				

				<%=error%>


				<%
				}
				}
				%>


			</table>
			
			<a href="/3-2/menu.jsp">メニューに戻る</a>
			
		</form>
	</div>
</body>
</html>

