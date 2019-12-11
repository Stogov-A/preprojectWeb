<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action='<c:url value="/admin/add" />' style="display:inline;">
    <input type="hidden" name="message" value="Create a new user">
    <input type="submit" value="Add new user">
</form>
</td>
<h1>

    <table border="12">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Mail</th>
            <th>Role</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="user" items="${Users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.mail}</td>
                <td>${user.role}</td>
                <td><a href=/admin/edit?id=${user.id}>Edit</a></td>
                <td><a href="/admin/delete?id=${user.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</h1>
</body>
</html>