<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%--Для поддержки кириллицы--%>
<?xml version="4.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html>
<head>
    <title>Update user</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link href="${pageContext.request.contextPath}/styles/formStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-6">
    <h1>Update user: <c:out value="${userName}"/><br/></h1>
    <form action="${pageContext.request.contextPath}/update" method="POST">
        <input type="hidden" value="${userId}" name="id">
        <input type="text" name="name" value="${userName}"/>
        <input type="text" name="password" value="${userPassword}"/>
        <input type="submit" value="Update" name="updatePage">
    </form>
</div>
</body>
</html>
