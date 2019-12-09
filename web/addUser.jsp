
<%--
  Created by IntelliJ IDEA.
  User: NoName
  Date: 24.11.2019
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <output>${message}</output>
    <br>
    ID: <input type="number" name="id"/>
    Name: <input type="text" name="name"/>
    Mail: <input type="text" name="mail"/>
    Password: <input type="password" name="pass"/><br>
    <input type="submit" value="Registration">
</form>
</body>
</html>