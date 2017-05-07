<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<fmt:setBundle basename="messages" />
<c:set var="t" value="true" />
<title><fmt:message key="title" />Lecturer Process</title>
</head>
<body>
<div style="text-align:center;">
	<form action="/Team04CAPS/EnrollmentProcess" method=post>
		<!--<jsp:useBean id="enrollment" class="model.EnrollmentDTO"/> -->
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="45%">Description</th>
					<th width="55%">Detail</th>
				</tr>
				<tr>
					<td><fmt:message key="setup.courseID" /></td>
					<td><input type="text" name="courseID"
						value="${param['courseID']}" size=15 maxlength=20 readonly>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="setup.courseName" /></td>
					<td><input type="text" name="courseName"
						value="${param['name']}" size=15 maxlength=20 readonly>
				</tr>
				<tr>
					<td><fmt:message key="setup.size" /></td>
					<td><input type="text" name="size" value="${param['size']}"
						size=15 maxlength=20 readonly>
				</tr>
				<tr>
					<td><fmt:message key="setup.enrolSize" /></td>
					<td><input type="text" name="enrolSize" value="${arrySize}"
						size=15 maxlength=20 readonly>
				</tr>
			</table>
		</center>
	</form>
	<%-- <h1>${enrold}</h1> --%>
		<display:table name="enrold" pagesize="5" requestURI="/process"
		id="enrold" class="borderAll">
		<display:column property="student.studentID" title="Student ID"
			class="text-center" />
		<display:column property="student.studentFirstName" title="Student First Name"
			class="text-center" />
		<display:column property="student.studentSecondName" title="Student Second Name"
			class="text-center" />
		<display:column property="studentGrade" title="Grade"
			class="text-center" />
		</display:table>
<%-- 	<table>
		<tr>
			<th>ID</th>
			<th>Student ID</th>
			<th>Student First Name</th>
			<th>Student Second Name</th>
			<th>Grade</th>

		</tr>
		<c:forEach var="enr" items="${enrold}" varStatus="status">
			<tr>
				<td class="nowrap">${status.index + 1}</td>
				<td class="nowrap">${enr.getStudent().getStudentID()}</td>
				<td class="nowrap">${enr.getStudent().getStudentFirstName()}</td>
				<td class="nowrap">${enr.getStudent().getStudentSecondName()}</td>
				<td class="nowrap">${enr.studentGrade}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table> --%>
</div>
</body>
</html>