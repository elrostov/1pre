<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%--Для поддержки кириллицы--%>
<?xml version="4.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html>
<head>
    <title>Preproject 1</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="GET">
    <input type="submit" value="Register user">
</form>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Password</th>
        <th>Update user</th>
        <th>Delete user</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getName()}</td>
            <td>${user.getPassword()}</td>
            <td>
                <form action="${pageContext.request.contextPath}/update" method="POST">
                    <input type="hidden" value="${user.getId()}" name="id"/>
                    <input type="hidden" value="${user.getName()}" name="name"/>
                    <input type="hidden" value="${user.getPassword()}" name="password"/>
                    <input type="hidden" value="yes" name="updatePage"/>
                    <input type="submit" value="Update">
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/delete" method="POST">
                    <input type="hidden" value="${user.getId()}" name="id"/>
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
