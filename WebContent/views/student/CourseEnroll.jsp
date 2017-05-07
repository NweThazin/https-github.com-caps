<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<fmt:setBundle basename="messages" />
<c:set var="t" value="true" />
<title><fmt:message key="title" /></title>
</head>
<body>
<div style="text-align:center;">
	<h2>Student Information Page</h2>
	<form action="/Team04CAPS/StudentCenter?action=6" method="post"
		name="studentForm">
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="35%">Description</th>
					<th width="65%">Detail</th>
				</tr>
				<tr>
					<td><fmt:message key="student.id" /></td>
					<td>
						<input type="text" name="studentID" value="${studentID}"
							size=25 maxlength=20 readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td><fmt:message key="course.id" /></td>
					<td>
						<input type="text" name="CourseID" value="${course.getCourseID()}" 
							size=25 maxlength=20 readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="course.vacancy" /></td>
					<td>
						<input type="text" name="Vacancy" value="${num} / ${course.getCourseSize()}" 
							size=25 maxlength=20 readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<!-- Need a Validation to check vacancy -->
					<td>
						<input class="buttonGroup" type="submit" value="Enroll This Course">
						<input type="hidden" name="enrolling" value="true" />
						<input type="hidden" name="studentID" value="${studentID}" />
						<input type="hidden" name="courseID" value="${course.getCourseID()}" />
					<!-- <td><input class="buttonGroup" type="reset" value="Clear"></td> -->
				</tr>
			</table>
		</center>

	</form>
	</div>
</body>
</html>