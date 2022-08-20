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

        <tr>
            <th>#</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Email</th>
        </tr>
        <c:forEach items="${administrators}" var="administrator" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><a href="controller?command=administrator&id=${administrator.id}">
                <c:out value="${administrator.firstName}"/></a></td>
                <td><a href="controller?command=administrator&id=${administrator.id}">
                <c:out value="${administrator.lastName}"/></a></td>
                <td>${administrator.email}</td>
                <td>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="edit_administrator_form">
                        <input type="hidden" name="administratorId" value="${administrator.id}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="controller?command=administrators&page=1">First</a>
    <a href="controller?command=administrators&page=${requestScope.currentPage - 1}">Previous</a>
    <a href="controller?command=administrators&page=${requestScope.currentPage + 1}">Next</a>
    <a href="controller?command=administrators&page=${requestScope.totalPages}">Last</a>
    <p>Page ${requestScope.currentPage} of ${requestScope.totalPages}</p>
</body>
</html>