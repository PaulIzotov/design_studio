<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Projects</title>
</head>
<body>
<h1>Projects</h1>
<jsp:include page="navbar.jsp"/>
    <table>
        <tr>
            <th>#</th>
            <th>Square</th>
            <th>Price for m2</th>
            <th>Administrator</th>
            <th>Designer</th>
        </tr>
        <c:forEach items="${projects}" var="project" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><a href="controller?command=project&id=${project.id}">${project.square}</a></td>
                <td>${project.priceM2}</td>
                <td>${project.admin}</td>
                <td>${project.designer}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>