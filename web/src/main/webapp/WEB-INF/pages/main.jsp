<%@ include file="/jsp/include.jsp"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<html>
<head>
<title><fmt:message key="main.title" /></title>
</head>
<body>
	<h3>
		<fmt:message key="main.title" />
	</h3>
	<hr />
	<sec:authorize access="isAuthenticated()">
		<p>
			<fmt:message key="main.greeting" /> <sec:authentication property="principal.username" />
		</p>
	</sec:authorize>
	
	<hr />
	<a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button"><fmt:message key="main.href.logout" /></a>
	<a href="<c:url value='/viewMetal' />"><fmt:message key="main.href.viewMetals" /></a>
</body>
</html>