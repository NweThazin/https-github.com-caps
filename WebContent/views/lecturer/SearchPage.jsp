<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
<fmt:setBundle basename="messages" />
<c:set var="t" value="true" />
<title><fmt:message key="title" /></title>
</head>
<body>
<div style="text-align:center;">
	<form action="/Team04CAPS/search" method=post>

		<table style="cellpadding: 4; cellspacing: 2; border: 0">
			<tr>
				<th width="45%">Description</th>
				<th width="55%">Detail</th>
			</tr>
			<tr>
				<td><fmt:message key="setup.firstName" /></td>
				<td><input type="text" name="firstName"
					value="${param['name']}" size=15 maxlength=20 required></td>
			</tr>
			<tr>
				<td><input type="submit" value="Search" class="buttonGroup">
				</td>
				<td><input type="reset" value="Reset" class="buttonGroup"></td>
			</tr>
		</table>
	</form>

	<table class="borderAll">
		<tr>
			<th><fmt:message key="student.id" /></th>
			<th><fmt:message key="student.firstName" /></th>
			<th><fmt:message key="student.secondName" /></th>
			<th><fmt:message key="student.yearStudy" /></th>
			<%-- <th><fmt:message key="label.course.cGPA" /></th> --%>
			<th><fmt:message key="student.details.link" /></th>
		</tr>
		<c:forEach var="student" items="${searchlist}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${student.studentID}</td>
				<td class="nowrap">${student.studentFirstName}</td>
				<td class="nowrap">${student.studentSecondName}</td>
				<td class="nowrap">${student.studentYearStudy}</td>
				<%-- <td class="nowrap">${student.studentCGPA}</td> --%>
				<td class="nowrap"><c:url var="updurl" scope="page"
						value="/StudentTakenCourses">
						<c:param name="studentID" value="${student.studentID}" />
						<%-- <c:param name="action" value="delete" /> --%>
					</c:url><a href="${updurl}"><fmt:message key="label.course.view" /></a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>