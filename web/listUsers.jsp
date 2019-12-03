<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action='<c:url value="/add" />' style="display:inline;">
    <input type="hidden" name="message" value="Create a new user">
    <input type="submit" value="Add new user">
</form>
</td>
<h1>
    <c:forEach var="user" items="${Users}">
        <li>
            <c:out value="ID: ${user.id}    NAME: ${user.name}    MAIL: ${user.mail}"/>
            <form method="post" action='<c:url value="/edit" />' style="display:inline;">
                <input type="hidden" name="id" value="${user.id}">
                <input type="submit" value="Edit">
            </form>
            <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                <input type="hidden" name="id" value="${user.id}">
                <input type="submit" value="Delete">
            </form>
        </li>
    </c:forEach>
</h1>
</body>
</html>
