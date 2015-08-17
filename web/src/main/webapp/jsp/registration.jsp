<%@ include file="/jsp/include.jsp" %>
<html>
<head>
    <title><fmt:message key="registration.user.title"/></title>
</head>
<body>
<form name="addUserForm" method="post" action="controller" page="registration.jsp">
    <input type="hidden" name="command" value="save_user" />
    <br/><fmt:message key="registration.user.firstName"/><br/>
    <input type="text" name="firstName" value=""/>
    <br/><fmt:message key="registration.user.lastName"/><br/>
    <input type="text" name="lastName" value=""/>
    <br/><fmt:message key="registration.user.login"/><br/>
    <input type="text" name="login" value=""/>
    <br/><fmt:message key="registration.user.password"/><br/>
    <input type="text" name="password" value=""/>
    <br/>
    ${errorAddMetal}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="<fmt:message key="registration.user.button"/>"/>

</form><hr/>
<a href="controller?command=logout"><fmt:message key="registration.href.logout"/></a>
</body>
</html>