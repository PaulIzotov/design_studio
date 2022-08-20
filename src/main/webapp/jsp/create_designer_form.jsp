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
    <form method="post" action="controller" enctype="multipart/form-data">
        <input type="file" name="avatar" accept="image/jpg">
        <br/>
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
        <input id="password-input" name="password" type="password" minlength="4"/>
        <br/>
        <input id="specialization-input-interior" name="specialization" type="radio"
            value="INTERIOR" ${requestScope.designer.specialization=='INTERIOR' ? 'checked' : ''}>
        <label for="specialization-input-interior">Interior</label>
        <input id="specialization-input-exterior" name="specialization" type="radio"
            value="EXTERIOR" ${requestScope.designer.specialization=='EXTERIOR' ? 'checked' : ''}>
        <label for="specialization-input-exterior">Exterior</label>
        <input id="specialization-input-architecture" name="specialization" type="radio"
            value="ARCHITECT" ${requestScope.designer.specialization=='ARCHITECT' ? 'checked' : ''}>
        <label for="specialization-input-architecture">Architecture</label>
        <br/>
        <input id="role-input-admin" name="role" type="radio"
            value="ADMIN" ${requestScope.designer.role=='ADMIN' ? 'checked' : ''}>
        <label for="role-input-admin">Admin</label>
        <input id="role-input-designer" name="role" type="radio"
            value="DESIGNER" ${requestScope.designer.role=='DESIGNER' ? 'checked' : ''}>
        <label for="role-input-designer">Designer</label>
        <br/>
        <input type="submit" value="REGISTER"/>
    </form>
</body>
</html>