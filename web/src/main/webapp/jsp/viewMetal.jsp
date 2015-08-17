<%@ include file="/jsp/include.jsp" %>
<html>
<head>
    <title><fmt:message key="viewMetal.title"/></title>
</head>
<body>
<h3><fmt:message key="viewMetal.head"/></h3>
<table border="1" style="width:100%">
    <thead>
    <th><fmt:message key="list.metal.title"/></th>
    <th><fmt:message key="list.metal.quantity"/></th>
    <th><fmt:message key="list.metal.price"/></th>
    </thead>

    <c:forEach items="${metal}" var="metal">
        <tr>
            <td>${metal.title}</td>
            <td>${metal.quantity}</td>
            <td>${metal.price}</td>
        </tr>
    </c:forEach>
</table>
<hr/>
<a href="controller?command=logout"><fmt:message key="viewMetal.href.logout"/></a>
<a href="controller?command=add_metal"><fmt:message key="viewMetal.href.addMetal"/></a>
</body>
</html>
