<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit administrator</title>
</head>
<body>
    <h1>Edit designer</h1>
    <jsp:include page="navbar.jsp"/>
    <form method="post" action="controller">
        <input name="command" type="hidden" value="edit_administrator"/>
        <input name="id" type="hidden" value="${requestScope.administrator.id}"/>
        <label for="firstName-input">First name: </label>
        <input id="firstName-input" name="firstName" type="text" value="${requestScope.administrator.firstName}"/>
        <br/>
        <label for="lastName-input">Last name: </label>
        <input id="lastName-input" name="lastName" type="text" value="${requestScope.administrator.lastName}"/>
        <br/>
        <label for="email-input">Email: </label>
        <input id="email-input" name="email" type="email" value="${requestScope.administrator.email}"/>
        <br/>
        <input id="role-input-admin" name="role" type="radio"
            value="ADMIN" ${requestScope.designer.role=='ADMIN' ? 'checked' : ''}>
        <label for="role-input-admin">Admin</label>
        <input id="role-input-designer" name="role" type="radio"
            value="DESIGNER" ${requestScope.designer.role=='DESIGNER' ? 'checked' : ''}>
        <label for="role-input-designer">Designer</label>
        <br/>
        <input type="submit" value="SAVE"/>
    </form>
</body>
</html>