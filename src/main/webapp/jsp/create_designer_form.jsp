<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register new designer</title>
</head>
<body>
    <h1>Register new designer</h1>
    <jsp:include page="navbar.jsp"/>
    <form method="post" action="controller">
        <input name="command" type="hidden" value="create_designer"/>
        <label for="firstName-input">First name: </label>
        <input id="firstName-input" name="firstName" type="text"/>
        <br/>
        <label for="lastName-input">Last name: </label>
        <input id="lastName-input" name="lastName" type="text"/>
        <br/>
        <label for="experience-input">Experience: </label>
        <input id="experience-input" name="experience" type="text"/>
        <br/>
        <label for="email-input">Email: </label>
        <input id="email-input" name="email" type="email"/>
        <br/>
        <label for="password-input">Password: </label>
        <input id="password-input" name="password" type="password" min="4"/>
        <br/>

        <input type="submit" value="REGISTER"/>
    </form>
</body>
</html>