<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="controller.UpdateController"%>
<%String code = (String) request.getAttribute("code");%>
<%String update = (String) request.getAttribute("update");%>
<%String delete = (String) request.getAttribute("delete");%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>変更完了画面</title>
</head>
<body>


	<%
	if (update != null && delete == null) {
	%>

	<div class="update" style="text-align: center">
		<h2 style="text-align: center">変更完了画面</h2>
		<hr style="height: 3; background-color: #0000ff" />
		<br> データを変更しました。 <br> <a href="/3-2/menu.jsp">メニューに戻る</a>

	</div>

	<%}%>




	<%
	if (delete != null && update == null) {
	%>


	<div class="delete" style="text-align: center">
		<h2 style="text-align: center">削除完了画面</h2>
		<hr style="height: 3; background-color: #0000ff" />
		<br> データを削除しました。 <br> <a href="/3-2/menu.jsp">メニューに戻る</a>

	</div>

	<%}%>

	



</body>
</html>