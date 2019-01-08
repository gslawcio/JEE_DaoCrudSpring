<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User</title>
</head>
<body>
	<h1>User -użytkownik</h1>
	<form action="UserServlet" method="post">
		<input placeholder="PESEL" name="pesel" type="text"><br>
		<input placeholder="Imię" name="firstname" type="text"><br>
		<input placeholder="Nazwisko" name="lastname" type="text"><br>
		<br>
		Szukaj<input type="radio" name="option" value="search"><br>
		Dodaj<input type="radio" name="option" value="add"><br>
		Modyfikuj<input type="radio" name="option" value="update"><br>
		Usuń<input type="radio" name="option" value="delete"><br>
	<br>
	<input type="submit" value="Wyślij">
	<br>
	 <a href="index.jsp">Powrót</a>	
	
	</form>
</body>
</html>