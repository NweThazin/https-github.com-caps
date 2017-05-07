<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
<title>Courses and Grades</title>
</head>
<body>
<div style="text-align:center;">
	<table class="borderAll">
		<tr>
			<th>No.</th>
			<th>CourseID</th>
			<th>CourseName</th>
			<th>Credits</th>
			<th>Grade</th>
		</tr>
		<!-- grades is a ArrayList of EnrollmentDTO, it has a getCourse method to mapping the courseDTO object -->
		<c:forEach var="line" items="${grades}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd' }">
				<td class="nowrap">${status.index + 1 }</td>
				<td class="nowrap">${line.getCourse().getCourseID()}</td>
				<td class="nowrap">${line.getCourse().getCourseName()}</td>
				<td class="nowrap">${line.getCourse().getCourseCredits()}</td>
				<td class="nowrap">${line.getStudentGrade()}</td>
		</c:forEach>
	</table>
	GPA =
	<c:out value="${GPA}" />
	</div>
</body>
</html>