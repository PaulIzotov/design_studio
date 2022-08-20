<%@ page language="java" contentType="text.html; charSet=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<a href="controller?command=home">Home</a>
<c:if test="${sessionScope.designer != null}">
    <a href="controller?command=logout">Logout</a></li>
</c:if>
<c:if test="${sessionScope.designer == null}">
    <a href="controller?command=create_designer_form">Register new designer</a></li>
    <a href="controller?command=create_administrator_form">Register new admin</a></li>
    <a href="controller?command=login_form">Sign in</a></li>
</c:if>
<a href="controller?command=designers">List of designers</a></li>
<a href="controller?command=administrators">List of admins</a></li>
<a href="controller?command=projects">List of projects</a></li>
