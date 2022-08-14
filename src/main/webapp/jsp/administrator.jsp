<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Administrator</title>
</head>
<body>
<h1>Info about administrator</h1>
<table>
    <tr><th>Id</th><th>${administrator.id}</th></tr>
    <tr><th>FirstName</th><th>${administrator.firstName}</th></tr>
    <tr><th>LastName</th><th>${administrator.lastName}</th></tr>
    <tr><th>Email</th><th>${administrator.email}</th></tr>
</table>
</body>
</html>