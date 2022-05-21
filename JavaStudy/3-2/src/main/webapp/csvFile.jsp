<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%String error = (String) request.getAttribute("error");%>
<%String itemCsv = (String) request.getAttribute("itemCsv");%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CSVダウンロード</title>
</head>
<body>
	<div class=a style="margin: 0 auto;">
		<h2>CSVダウンロード</h2>

		<form action="/3-2/csvController" method="post">
			<input type="submit" name="itemCsv" value="csvダウンロード"/>
		</form>${error}





		<div class=b style="text-align: center;">
			<input type="button" value="csvダウンロード"
				onclick="location.href='csvController'"><br />
		</div>
		<br />

		<hr style="height: 3; background-color: black;" />


		<a href="/3-2/menu.jsp">メニューに戻る</a> <br>

	</div>
</body>
</html>