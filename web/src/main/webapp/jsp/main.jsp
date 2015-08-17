<%@ include file="/jsp/include.jsp" %>
<html>
<head>
    <title><fmt:message key="main.title"/></title>
</head>
<body>
<h3><fmt:message key="main.title"/></h3>
<hr/>
${user}, <fmt:message key="main.greeting"/>
<hr/>
<a href="controller?command=logout"><fmt:message key="main.href.logout"/></a>
<a href="controller?command=view_metals"><fmt:message key="main.href.viewMetals"/></a>
</body>
</html>
