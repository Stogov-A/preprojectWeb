<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: NoName
  Date: 27.11.2019
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>

<form method="post" action="index" style="display:inline;">
    <output>${message}</output><br><br>
    <label>Name</label><br>
    <input type="text" name="name"/><br><br>
    <label>Password</label><br>
    <input type="password" name="pass"><br><br>
    <input type="submit" value="Sign in"/>
</form>

</body>
</html>
