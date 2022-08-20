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
                <td><a href="controller?command=project&id=${project.id}"><c:out value="${project.square}"/></a></td>
                <td><c:out value="${project.priceM2}"/></td>
                <td><a href="controller?command=administrator&id=${project.admin.id}"><c:out value="${project.admin}"/></a></td>
                <td><a href="controller?command=designer&id=${project.designer.id}"><c:out value="${project.designer}"/></a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="controller?command=projects&page=1">First</a>
    <a href="controller?command=projects&page=${requestScope.currentPage - 1}">Previous</a>
    <a href="controller?command=projects&page=${requestScope.currentPage + 1}">Next</a>
    <a href="controller?command=projects&page=${requestScope.totalPages}">Last</a>
    <p>Page ${requestScope.currentPage} of ${requestScope.totalPages}</p>
</body>
</html>