<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Register user</title>
    <link href="${pageContext.request.contextPath}/styles/formStyle.css" rel="stylesheet" type="text/css">
<%--    <style>--%>
<%--        <%@include file='../styles/formStyle.css' %>--%>
<%--    </style>--%>
</head>
<body>
<div class="form-style-6">
    <h1>Register new user:<br/></h1>
    <form action="/registration" method="POST">
        <input type="text" name="name" placeholder="User Name" />
        <input type="password" name="password" placeholder="User password" />
        <input type="submit" value="Register" />
    </form>
</div>
</body>
</html>
