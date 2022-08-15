<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<ul>
    <c:if test="${sessionScope.designer != null}">
        <li><a href="controller?command=logout">Logout</a></li>
    </c:if>
    <c:if test="${sessionScope.designer == null}">
        <li><a href="controller?command=create_designer_form">Sign up</a></li>
        <li><a href="controller?command=login_form">Sign in</a></li>
    </c:if>
    <li><a href="controller?command=designers">List of designers </a></li>
    <li><a href="controller?command=administrators">List of admins</a></li>
    <li><a href="controller?command=projects">List of projects</a></li>
</ul>