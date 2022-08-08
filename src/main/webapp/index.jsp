<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Design-studio Monkeys</title>
</head>
<body>
<h1>Welcome to design-studio Monkeys!</h1>
<h3>Here your dreams comes true</h3>
<ul>
    <li><a href="controller?command=create_designer_form">Sign up</a></li>
    <li><a href="controller?command=login_form">Sign in</a></li>
    <li><a href="controller?command=designers">List of designers </a></li>
    <li><a href="controller?command=administrators">List of admins</a></li>
    <li><a href="controller?command=projects">List of projects</a></li>
</ul>
</body>
</html>