<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="header.jsp"%>

	<!-- name、idの入力エリアを作成しなさい -->
	<main>
		<div class="main">
			<table>
				<tr>
					<td><label>name</label></td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td><label>id</label></td>
					<td><input type="text" name="id"></td>
				</tr>
			</table>
		</div>
	</main>


	<%@ include file="footer.jsp"%>

</body>
</html>
