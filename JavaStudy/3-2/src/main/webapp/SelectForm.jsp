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


		</form>




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
				<td style="text-align: center; width: 100">

					<form action="/3-2/UpdateFormServlet" method="post">
						<input type="submit" value="編集" /><input type="hidden"name="serialcode" value="<%=list.get(i).getCode()%>" />
						<input type="hidden"name="serialid" value="<%=list.get(i).getCode()%>" />
							<input type="hidden"name="serialname" value="<%=list.get(i).getName()%>" />
							<input type="hidden"name="serialprice" value="<%=list.get(i).getPrice()%>" />
							
					</form>
				</td>


			</tr>



			<%=error%>


			<%
			}
			}
			%>


		</table>



		<a href="/3-2/menu.jsp">メニューに戻る</a>


	</div>
</body>
</html>

