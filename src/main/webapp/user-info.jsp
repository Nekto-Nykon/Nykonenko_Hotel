<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 11.04.2023
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User-Info page</title>
</head>
<body>
<h1>User info page  with email : <%= request.getSession().getAttribute("email") %></h1>
<a href="logout"> Logout</a>
</body>
</html>
