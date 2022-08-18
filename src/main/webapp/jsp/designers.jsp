<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Designers</title>
</head>
<body>
<h1>Designers</h1>
<jsp:include page="navbar.jsp"/>
    <table>
        <tr>
            <th>#</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Experience</th>
            <th>Email</th>
            <th>Specialization</th>
        </tr>
        <c:forEach items="${designers}" var="designer" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><a href="controller?command=designer&id=${designer.id}"><c:out value="${designer.firstName}"/></a></td>
                <td><a href="controller?command=designer&id=${designer.id}"><c:out value="${designer.lastName}"/></a></td>
                <td><c:out value="${designer.experience}"/></td>
                <td><c:out value="${designer.email}"/></td>
                <td><c:out value="${designer.specialization}"/></td>
                <td>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="edit_designer">
                        <input type="hidden" name="designerId" value="${designer.id}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="controller?command=designers&page=1">First</a>
    <a href="controller?command=designers&page=${requestScope.currentPage - 1}">Previous</a>
    <a href="controller?command=designers&page=${requestScope.currentPage + 1}">Next</a>
    <a href="controller?command=designers&page=${requestScope.totalPages}">Last</a>
    <p>Page ${requestScope.currentPage} of ${requestScope.totalPages}</p>
</body>
</html>