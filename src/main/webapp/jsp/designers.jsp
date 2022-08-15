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
                <td><a href="controller?command=designer&id=${designer.id}">${designer.firstName}</a></td>
                <td><a href="controller?command=designer&id=${designer.id}">${designer.lastName}</a></td>
                <td>${designer.experience}</td>
                <td>${designer.email}</td>
                <td>${designer.specialization}</td>
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
</body>
</html>