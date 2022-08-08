<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Project</title>
</head>
<body>
<h1>Info about project</h1>
<table>
    <tr><th>Field</th><th>Value</th></tr>
    <tr><th>Id</th><th>${project.id}</th></tr>
    <tr><th>Administrator</th><th>${project.administrator}</th></tr>
    <tr><th>Designer</th><th>${project.designer}</th></tr>
    <tr><th>Price for m2</th><th>${project.price_for_m2}</th></tr>
    <tr><th>Square</th><th>${project.square}</th></tr>
</table>
</body>
</html>