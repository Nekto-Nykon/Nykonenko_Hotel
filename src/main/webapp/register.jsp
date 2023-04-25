<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 11.04.2023
  Time: 0:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
</head>
<body>
<h1> Register Page </h1>

<form action="register" method="post">
    <input type="text" name="register_name" placeholder="${register_name}"/>
    <input type="text" name="register_email" placeholder="${register_email}"/>
    <input type="password" name="register_password" placeholder="${register_password}"/>
    <input type="submit" value="${register_confirm}"/>
</form>
<a href="login"> Login </a><br>
<a href="/"> Back To Main Menu </a><br>
</body>
</html>
