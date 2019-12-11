<%--
  Created by IntelliJ IDEA.
  User: NoName
  Date: 24.11.2019
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form method="post">
    <input type="hidden" value="${user.id}" name="id"/>
    <input type="hidden" value="send" name="message"/>
    <label>Name</label><br>
    <input name="Name" value="${user.name}"/><br><br>
    <label>Mail</label><br>
    <input name="Mail" value="${user.mail}" type="text"/><br><br>
    <label>New password</label><br>
    <input name="New password" type="password"/><br><br>
    <select name="Role">
        <option>admin</option>
        <option>user</option>
    </select><br><br>
    <label>Enter password</label><br>
    <input name="Pass" type="password"><br>
    <input type="submit" value="Edit"/><br>
    <output>${message}</output>
    <br>
</form>
</body>
</html>