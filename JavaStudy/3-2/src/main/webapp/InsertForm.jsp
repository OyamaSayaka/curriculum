<%@page contentType="text/html; charset=UTF-8"%>
<%String productNameError = (String) request.getAttribute("productNameError");%>
<%String priceError = (String) request.getAttribute("priceError");%>

<html>
	<head>
		<title>データを登録する</title>
	</head>
	<body>
		<div style="text-align:center;">
 			<h2 style="text-align:center;">登録画面</h2>
 			<hr style="height:3; background-color:#0000ff;" />
 			<br>
 			登録する情報を入力してください。
 			<form action="/3-2/InsertServlet" method="post">
 				<table style="margin:0 auto;">
 					<tr>
 						<td style="width:60;">名前</td>
 						<td ><input type=text size="30" name="name"></input>
 						<font color="red">${productNameError}</font></td>
 					</tr>
 					<tr>
 						<td style="width:70;">単価</td>
 						<td ><input type=text size="30" name="price"></input>
 						<font color="red">${priceError}</font></td>
 					</tr>
 					<tr>
 						<td colspan=2 style="text-align:center;"><input type="submit" value="登録"></td>
 					</tr>
 				</table>
 			</form>
 			<a href= "/3-2/menu.jsp">メニューに戻る</a>
 			<br>
 		</div>
 	</body>
 </html>