
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="webResources"/>
<html>
<body>
<head>
    <title>MainPage</title>
</head>
<form:form  method="POST" modelAttribute="user">
    <br/>Name:<br/>
    <form:input path="firstName" id="firstName"/>
    <form:errors path="firstName" cssClass="error"/>
    <br/>Surname:<br/>
    <form:input path="lastName" id="lastName"/>
    <form:errors path="lastName" cssClass="error"/>
    <br/>Login:<br/>
    <form:input path="login" id="login"/>
    <form:errors path="login" cssClass="error"/>
    <br/>Password:<br/>
    <form:input path="password" id="password"/>
    <form:errors path="password" cssClass="error"/>
   
    
    <br/>
    ${errorRegistration}
    <br/>
   <input type="submit" value="<fmt:message key="registration.user.button"/>"/>
</form:form>
</body>
</html>