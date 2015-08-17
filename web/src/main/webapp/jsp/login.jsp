<%@ include file="/jsp/include.jsp" %>
<html><head>
    <title><fmt:message key="login.title"/></title></head>
<body>
<form name="loginForm" method="post" action="controller">
    <input type="hidden" name="command" value="login" />
    <br/><fmt:message key="login.login"/><br/>
    <input type="text" name="login" value=""/>
    <br/><fmt:message key="login.password"/><br/>
    <input type="password" name="password" value=""/>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="<fmt:message key="login.button"/>"/>
    
</form><hr/>
<a href="controller?command=add_user"><fmt:message key="login.href.registration"/></a>
</body>
</html>