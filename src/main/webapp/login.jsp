<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/28/2023
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="login" method="post">
    <input name = "email">
    <input name = "password" type="password">
    <input type="submit" value="login">
</form>
<form action="login" method="post">
    <input type="hidden" value="google" name="type">
    <input type="submit" value="login with google">
</form>
</body>
</html>
