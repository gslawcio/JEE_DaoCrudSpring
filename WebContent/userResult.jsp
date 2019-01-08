<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import="model.User"%>
    <% User user = (User)request.getAttribute("user"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User result</title>
</head>
<body>
	<h1>User result</h1>
	<h1>Wynik zapytania : <%=request.getAttribute("option") %></h1>
	<p>W wyniku Twojego zapytania otrzymano wynik :</p>
	<p>PESEL : <%=user.getPesel() %><br>
	Imię : <%= user.getFirstname() %><br>
	Nazwisko : <%= user.getLastname() %></p>
	<br>
	<a href="index.jsp">Powrót</a>
	
</body>
</html>