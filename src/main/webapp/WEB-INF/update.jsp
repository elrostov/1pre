<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Update user</title>
    <link href="${pageContext.request.contextPath}/styles/formStyle.css" rel="stylesheet" type="text/css">
<%--    <style>--%>
<%--        <%@include file='../styles/formStyle.css' %>--%>
<%--    </style>--%>
</head>
<body>
<div class="form-style-6">
    <h1>Update user: <c:out value="${userName}"/><br/></h1>
    <form action="/update" method="POST">
        <input type="text" name="name" placeholder="${userName}" />
        <input type="password" name="password" placeholder="**********" />
        <input type="hidden" value="${userId}" name="id">
        <input type="submit" value="Update" />
    </form>
</div>
</body>
</html>
