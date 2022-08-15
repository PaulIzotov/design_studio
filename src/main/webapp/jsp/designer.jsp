<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Designer</title>
</head>
<body>
<h1>Info about designer</h1>
<p>${requestScope.message}</p>
<jsp:include page="navbar.jsp"/>
<table>
    <tr><th>Id</th><th>${designer.id}</th></tr>
    <tr><th>FirstName</th><th>${designer.firstName}</th></tr>
    <tr><th>LastName</th><th>${designer.lastName}</th></tr>
    <tr><th>Experience</th><th>${designer.experience}</th></tr>
    <tr><th>Email</th><th>${designer.email}</th></tr>
    <tr><th>Specialization</th><th>${designer.specialization}</th></tr>
</table>
</body>
</html>