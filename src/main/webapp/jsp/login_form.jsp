<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <jsp:include page="navbar.jsp"/>
    <form method="post" action="controller">
        <input name="command" type="hidden" value="login"/>
        <label for="email-input">Email: </label>
        <input id="email-input" name="email" type="email"/>
        <br/>
        <label for="password-input">Password: </label>
        <input id="password-input" name="password" type="password" min="4"/>
        <br/>
        <input type="submit" value="LOGIN"/>
    </form>
</body>
</html>