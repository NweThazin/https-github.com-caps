<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
<fmt:setBundle basename="messages" />
<title><fmt:message key="title" />Lecturer Page</title>
</head>
<body>
<div style="text-align:center;">
	<h1>
		<fmt:message key="label.enrollment" />
	</h1>
	<br />
	<table class="borderAll">
		<tr>
			<th><fmt:message key="label.enrollment.courseID" /></th>
			<th><fmt:message key="label.enrollment.courseStart" /></th>
			<th><fmt:message key="label.enrollment.studentID" /></th>
			<th><fmt:message key="label.enrollment.grade" /></th>
			<th><fmt:message key="label.enrollment.edit" />
		</tr>
		<c:forEach var="enrollment" items="${enrList}" varStatus="status">
			<c:forEach var="enr" items="${enrollment}" varStatus="status">
					<tr class="${status.index%2==0?'even':'odd'}">
					<td class="nowrap">${enr.course.courseID}</td>
					<td class="nowrap">${enr.courseStart}</td>
					<td class="nowrap">${enr.student.studentID}</td>
			<%-- 		<td class="nowrap">${enr.student.studentName}</td> --%>
					<td class="nowrap">${enr.studentGrade}</td>
					<td class="nowrap"><c:url var="updurl" scope="page"
							value="/views/lecturer/EnrollmentSetUpPage.jsp">
							<c:param name="courseID" value="${enr.course.courseID}" />
							<c:param name="startDate" value="${enr.courseStart}" />
							<c:param name="studentID" value="${enr.student.studentID}" />
						<%-- 	<c:param name="studentName" value="${enr.studentName}" /> --%>
							<c:param name="grade" value="${enr.studentGrade}" />
							<c:param name="update" value="true" />
						</c:url> <a href="${updurl}"><fmt:message key="label.course.edit" /></a>
				  </td>
				  </tr>
			</c:forEach>
		</c:forEach>
	</table>
	</div>
</body>
</html>