<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Administrators</title>
</head>
<body>
<h1>Administrators</h1>
<jsp:include page="navbar.jsp"/>
    <table>
        <a href="controller?command=administrators&page=1">First</a>
        <a href="controller?command=administrators&page=${requestScope.currentPage - 1}">Previous</a>
        Page ${requestScope.currentPage} / ${requestScope.totalPages}
        <a href="controller?command=administrators&page=${requestScope.currentPage + 1}">Next</a>
        <a href="controller?command=administrators&page=${requestScope.totalPages}">Last</a>
        <tr>
            <th>#</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Email</th>
        </tr>
        <c:forEach items="${administrators}" var="administrator" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><a href="controller?command=administrator&id=${administrator.id}">${administrator.firstName}</a></td>
                <td><a href="controller?command=administrator&id=${administrator.id}">${administrator.lastName}</a></td>
                <td>${administrator.email}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>