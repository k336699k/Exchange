<%@ include file="/jsp/include.jsp" %>
<html>
<head>
    <title><fmt:message key="add.metal.title"/></title>
</head>
<body>
<form name="addMetalForm" method="post" action="controller" page="addMetal.jsp">
    <input type="hidden" name="command" value="save_metal" />
    <br/><fmt:message key="add.metal.titleMetal"/><br/>
    <input type="text" name="title" value=""/>
    <br/><fmt:message key="add.metal.quantityMetal"/><br/>
    <input type="text" name="quantity" value=""/>
    <br/><fmt:message key="add.metal.priceMetal"/><br/>
    <input type="text" name="price" value=""/>
    <br/>
    ${errorAddMetal}
    <br/>
    ${errorUseMetal}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="<fmt:message key="add.metal.button"/>"/>

</form><hr/>
<a href="controller?command=view_metals"><fmt:message key="add.href.viewMetal"/></a>
</body>
</html>