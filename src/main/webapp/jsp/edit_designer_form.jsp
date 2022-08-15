<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit designer</title>
</head>
<body>
    <h1>Register new designer</h1>
    <jsp:include page="navbar.jsp"/>
    <form method="post" action="controller">
        <input name="command" type="hidden" value="edit_designer"/>
        <input name="id" type="hidden" value="${requestScope.designer.id}">
        <label for="firstName-input">First name: </label>
        <input id="firstName-input" name="firstName" type="text" value="${requestScope.designer.firstName}"/>
        <br/>
        <label for="lastName-input">Last name: </label>
        <input id="lastName-input" name="lastName" type="text" value="${requestScope.designer.lastName}"/>
        <br/>
        <label for="experience-input">Experience: </label>
        <input id="experience-input" name="experience" type="text" value="${requestScope.designer.experience}"/>
        <br/>
        <label for="email-input">Email: </label>
        <input id="email-input" name="email" type="email" value="${requestScope.designer.email}"/>
        <br/>
        <input id="specialization-input-interior" name="specialization" type="radio"
            value="INTERIOR" ${requestScope.specialization=='INTERIOR' ? 'checked' : ''}>
        <label for="specialization-input-interior">Interior</label>
        <input id="specialization-input-exterior" name="specialization" type="radio"
            value="EXTERIOR" ${requestScope.specialization=='EXTERIOR' ? 'checked' : ''}>
        <label for="specialization-input-exterior">Exterior</label>
        <input id="specialization-input-architecture" name="specialization" type="radio"
            value="ARCHITECTURE" ${requestScope.specialization=='ARCHITECTURE' ? 'checked' : ''}>
        <label for="specialization-input-architecture">Architecture</label>
        <br/>
        <input id="role-input-admin" name="role" type="radio"
            value="ADMIN" ${requestScope.role=='ADMIN' ? 'checked' : ''}>
        <label for="role-input-admin">Admin</label>
        <input id="role-input-designer" name="role" type="radio"
            value="DESIGNER" ${requestScope.role=='DESIGNER' ? 'checked' : ''}>
        <label for="role-input-designer">Designer</label>
        <br/>
        <input type="submit" value="SAVE"/>
    </form>
</body>
</html>