<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
<fmt:setBundle basename="messages" />
<title><fmt:message key="enroll.title" />Enrollment Information</title>
</head>
<body>
<div style="text-align:center;">
	<h1>
		<fmt:message key="label.enroll" />
	</h1>
	<div>
		<h3>Search Enrollment Lists</h3>
		<form action="/Team04CAPS/SearchController" method="post"
			name="enrollmentForm">
			<table class="borderAll">
				<tr>
					<td>Course ID:</td>
					<td><input type="text" name="courseID" size=25 maxlength=20 /></td>
				</tr>
				<tr>
					<td>Student ID:</td>
					<td><input type="text" name="studentID" size=25 maxlength=20 /></td>
				</tr>
				<tr>
					<td><input class="buttonGroup" type="submit" value="Search"></td>
					<td><c:url var="url" scope="page"
							value="/views/admin/AddEnrollment.jsp">
							<c:param name="insert" value="true" />
							<c:param name="autoGenerate" value="enroll" />
						</c:url><a href="${url}" class="lnkGroup"><fmt:message key="label.enroll.add" /></a></td>
				</tr>
			</table>
		</form>
	</div>
	<display:table name="enrolls" pagesize="7" requestURI="/load"
		id="enrolls" class="borderAll">
		<display:column property="course.courseID" title="Course ID"
			class="text-center" />
		<display:column property="student.studentID" title="Student ID"
			class="text-center" />
		<display:column property="courseStart" title="Start Date"
			class="text-center" />
		<display:column property="enrolmentBy" title="Enroll Date"
			class="text-center" />
		<display:column property="studentGrade" title="Student Grade"
			class="text-center" />
		<display:column>
			<%-- 		<c:url var="updurl" scope="page" value="/views/AddEnrollment.jsp">
				<c:param name="courseID" value="${enrolls.course.courseID}" />
				<c:param name="studentID" value="${enrolls.student.stuID}" />
				<c:param name="CourseStart" value="${enrolls.courseStart}" />
				<c:param name="EnrollBy" value="${enrolls.enrolmentBy}" />
				<c:param name="Grade" value="${enrolls.studentGrade}" />
				<c:param name="update" value="true" />
			</c:url>
			<a href="${updurl}"><fmt:message key="label.edit" /></a>&nbsp;&nbsp; --%>
			<c:url var="delurl" scope="page" value="/EnrollmentController">
				<c:param name="courseID" value="${enrolls.course.courseID}" />
				<c:param name="studentID" value="${enrolls.student.studentID}" />
				<c:param name="action" value="delete" />
			</c:url>
			<a href="${delurl}"><fmt:message key="label.delete" /></a>
		</display:column>
	</display:table>
	<%-- 	<table class="borderAll" width="100%" height="100%" border="1" align="center">
		<tr>
			<th><fmt:message key="course.id" /></th>
			<th><fmt:message key="student.id" /></th>
			<th><fmt:message key="enroll.courseStart" /></th>
			<th><fmt:message key="enroll.enrollBy" /></th>
			<th><fmt:message key="enroll.studentGrade" /></th>
			<th><fmt:message key="label.edit" /> &nbsp;&nbsp; <fmt:message
					key="label.delete" /></th>
		</tr>
		<c:forEach var="enroll" items="${enrolls}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${enroll.course.courseID}</td>
				<td class="nowrap">${enroll.student.stuID}</td>
				<td class="nowrap">${enroll.courseStart}</td>
				<td class="nowrap">${enroll.enrolmentBy}</td>
				<td class="nowrap">${enroll.studentGrade}</td>
				<td class="nowrap">
				<c:url var="updurl" scope="page" value="/views/AddEnrollment.jsp">
						<c:param name="courseID" value="${enroll.course.courseID}" />
						<c:param name="studentID" value="${enroll.student.stuID}" />
						<c:param name="CourseStart" value="${enroll.courseStart}" />
						<c:param name="EnrollBy" value="${enroll.enrolmentBy}" />
						<c:param name="Grade" value="${enroll.studentGrade}" />
						<c:param name="update" value="true" />
					</c:url> <a href="${updurl}"><fmt:message key="label.edit" /></a>&nbsp;&nbsp;

					<c:url var="delurl" scope="page" value="/EnrollmentController">
						<c:param name="courseID" value="${enroll.course.courseID}" />
						<c:param name="studentID" value="${enroll.student.stuID}" />
						<c:param name="action" value="delete" />
					</c:url> <a href="${delurl}"><fmt:message key="label.delete" /></a>
				</td>
			</tr>
		</c:forEach>
	</table> --%>
</div>
</body>
</html>