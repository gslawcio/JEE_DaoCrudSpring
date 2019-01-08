<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library</title>
</head>
<body>
	<h1>Library</h1><br>
	<form action="BookServlet" method="post">
		<input placeholder="ISBN" name="isbn" type="text"><br>
		<input placeholder="Title" name="title" type="text"><br>
		<input placeholder="Description" name="description" type="text"><br>
		
		Szukaj<input type="radio" name="option" value="search"><br>
		Dodaj<input type="radio" name="option" value="add"><br>
		Modyfikuj<input type="radio" name="option" value="update"><br>
		Usuń<input type="radio" name="option" value="delete"><br>
		
	<input type="submit" value="Wyślij">
	<br>
	<a href="user.jsp">User Controller</a>
	</form>
</body>
</html>