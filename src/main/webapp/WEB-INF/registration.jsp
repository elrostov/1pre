<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%--Для поддержки кириллицы--%>
<?xml version="4.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html>
<head>
    <title>Register user</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link href="${pageContext.request.contextPath}/styles/formStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-6">
    <h1>Register new user:<br/></h1>
    <form action="${pageContext.request.contextPath}/registration" method="POST">
        <input type="text" name="name" placeholder="User Name" />
        <input type="password" name="password" placeholder="User password" />
        <input type="submit" value="Register" />
    </form>
</div>
</body>
</html>
