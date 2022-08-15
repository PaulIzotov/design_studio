<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Design-studio Monkeys</title>
</head>
<body>
    <jsp:include page="jsp/navbar.jsp"/>
    <h1>Welcome to design-studio Monkeys, ${sessionScope.designer != null ? sessionScope.designer.firstName : 'Guest'}!</h1>
    <h3>Here your dreams comes true</h3>
</body>
</html>