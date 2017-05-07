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
<title><fmt:message key="student.title" />Student Information</title>
</head>
<body>
<div style="text-align:center;">
	<h1>
		<fmt:message key="label.student" />
	</h1>
	<!-- Add New Student Link -->
	<table>
		<tr>
			<td>To add new student</td>
			<td><c:url var="url" scope="page" value="/GenerateController">
					<c:param name="studentID" value="${student.studentID}" />
					<c:param name="insert" value="true" />
					<c:param name="autoGenerate" value="student" />
				</c:url> <a href="${url}" class="lnkGroup"><fmt:message
						key="label.student.add" /></a></td>
		</tr>
	</table>
	<!-- Search Student -->
	<display:table name="students" pagesize="7" requestURI="/load"
		id="students" class="borderAll">
		<display:column property="studentID" title="Student ID"
			class="text-center" />
		<display:column property="studentFirstName" title="First Name"
			class="text-center" />
		<display:column property="studentSecondName" title="Second Name"
			class="text-center" />
		<display:column property="studentAddress" title="Address"
			class="text-center" />
		<display:column property="studentYearStudy" title="Year of Study"
			class="text-center" />
		<display:column property="studentEmail" title="Email"
			class="text-center" />
		<%-- <display:column property="studentCGPA" title="GPA" class="text-center" /> --%>

		<display:column>
			<c:url var="updurl" scope="page" value="/views/admin/AddStudent.jsp">
				<c:param name="studentID" value="${students.studentID}" />
				<c:param name="FirstName" value="${students.studentFirstName}" />
				<c:param name="SecondName" value="${students.studentSecondName}" />
				<c:param name="Address" value="${students.studentAddress}" />
				<c:param name="YearofStudy" value="${students.studentYearStudy}" />
				<c:param name="Email" value="${students.studentEmail}" />
	<%-- 			<c:param name="CGPA" value="${students.studentCGPA}" /> --%>
				<c:param name="update" value="true" />
			</c:url>
			<a href="${updurl}"><fmt:message key="label.edit" /></a>&nbsp;&nbsp;
				<c:url var="delurl" scope="page" value="/StudentController">
				<c:param name="studentID" value="${students.studentID}" />
				<c:param name="action" value="delete" />
			</c:url>
			<a href="${delurl}"><fmt:message key="label.delete" /></a>
		</display:column>
	</display:table>
</div>
</body>
</html>