<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 11.04.2023
  Time: 0:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page </title>
</head>
<body>
<h1> Login Page </h1>
<form action="login" method="post">
    <input type="text" name="login_email" placeholder="${login_email}"/>
    <input type="password" name="login_password" placeholder="${login_password}"/>
    <input type="submit" value="${login_confirm}"/>
</form>
<a href="register"> Register </a><br>
<a href="/"> Back To Main Menu </a><br>
</body>
</html>
