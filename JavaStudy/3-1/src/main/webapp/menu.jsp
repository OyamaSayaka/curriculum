<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
</head>
<body>
<h2>商品検索</h2>
 <form action="/3-1/serchServlet" method="post">
 商品名：<input type="text" name="name" size="20"/>
 <input type="submit" value="検索" />
</form>
</body>
</html>