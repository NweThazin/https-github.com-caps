<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet" type="text/css"/>
<title>Enroll for a Course</title>
</head>
<body>
<div style="text-align:center;">
	<table class="borderAll">
		<tr>
			<th>No.</th>
			<th>CourseID</th>
			<th>CourseName</th>
			<th>Credits</th>
			<th>Enroll</th>
		</tr>
		<c:forEach var="line" items="${courses}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd' }">
				<td class="nowrap">${status.index + 1 }</td>
				<td class="nowrap">${line.courseID}</td>
				<td class="nowrap">${line.courseName}</td>
				<td class="nowrap">${line.courseCredits}</td>
				<td>
					<%-- <c:url var="url_enroll" scope="page" value="StudentCenter?action=Student_EnrollSelects"> --%>
					<c:url var="url_enroll" scope="page" value="/StudentCenter">
						<c:param name="action" value="Student_EnrollSelects"/>
						<c:param name="courseID" value="${line.courseID}"/>
					</c:url>
					<a href="${url_enroll}">Enroll</a>
				</td>
		</c:forEach>
	</table>
	</div>
</body>
</html>