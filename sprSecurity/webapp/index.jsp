<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Hello ! Srv</title>
</head>
<body style="color: white; background-color: black;">

	<%-- <form action="delete.html" method="get">
		<c:forEach var="item" items="${message}">
			<c:out value="${item.tempName}  --# DBA #--  ${item.tempEmail}" />
			<button name="item" value="${item.tempName}">delete</button>
			<p>
		</c:forEach>
	</form>
 --%>
	<form action="HelloServlet" method="get" style="padding: 100px;">
		name:<input type="text" name="name" /> <br /> email:<input
			type="text" name="email" /><br />
		<button value="submit" type="submit" style="padding-right: 10;">submit</button>
	</form>

</body>
</html>