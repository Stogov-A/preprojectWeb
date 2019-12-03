<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: NoName
  Date: 29.11.2019
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <input type="hidden" value="${user.id}" name="id"/>
    <label>Delete this user?</label><br>
    <c:out value="ID: ${user.id} NAME: ${user.name} MAIL: ${user.mail}"/><br>
    <label>Enter password</label>
    <input name="Pass" type="password"><br>
    <input type="submit" value="Confirm"/><br>
    <output>${message}</output>
    <br>
</form>
</body>
</html>
