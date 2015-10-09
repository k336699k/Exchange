<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="webResources"/>
<html>
<body>
<head>
    <title><fmt:message key="add.metal.title"/></title>
</head>

<form:form   method="POST" modelAttribute="metal">
     <br/><fmt:message key="add.metal.titleMetal"/><br/>
    <form:input path="title" id="title"/>
    <form:errors path="title" cssClass="error"/>
     <br/><fmt:message key="add.metal.quantityMetal"/><br/>
     <form:input path="quantity" id="quantity"/>
    <form:errors path="quantity" cssClass="error"/>
      <br/><fmt:message key="add.metal.priceMetal"/><br/>
      <form:input path="priceString" id="priceString"/>
    <form:errors path="priceString" cssClass="error"/>
      
    
    <br/>
    ${errorAddMetal}
    <br/>
   
    <input type="submit" value="<fmt:message key="add.metal.button"/>"/>

</form:form>
<a href="<c:url value='/viewMetal' />"><fmt:message key="add.href.viewMetal"/></a>

</body>
</html>