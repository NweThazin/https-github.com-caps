<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
<fmt:setBundle basename="messages" />
<c:set var="t" value="true" />
<title><fmt:message key="title" />Search Lecturer Page</title>
</head>
<body>
	<div style="text-align: center;">
		<form action="/Team04CAPS/searchL" method=post>
			<table style="cellpadding: 4; cellspacing: 2; border: 0">
				<tr>
					<th width="45%">Criteria</th>
					<th width="55%">Search</th>
				</tr>
				<tr>
					<td><fmt:message key="setup.firstname" /></td>
					<td><input type="text" name="firstName"
						value="${param['firstName']}" size=15 maxlength=20></td>
				</tr>
				<tr>
					<td><fmt:message key="setup.secondname" /></td>
					<td><input type="text" name="secondName"
						value="${param['secondName']}" size=15 maxlength=20></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" class="buttonGroup">
					</td>
					<td><input type="reset" value="Reset" class="buttonGroup"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>