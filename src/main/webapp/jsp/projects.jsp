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
        <a href="controller?command=projects&page=1">First</a>
        <a href="controller?command=projects&page=${requestScope.currentPage - 1}">Previous</a>
        Page ${requestScope.currentPage} / ${requestScope.totalPages}
        <a href="controller?command=projects&page=${requestScope.currentPage + 1}">Next</a>
        <a href="controller?command=projects&page=${requestScope.totalPages}">Last</a>
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