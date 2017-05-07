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
<title><fmt:message key="title"/>Enrollment Information</title>
</head>
<body>
<div style="text-align:center;">
	<form action="/Team04CAPS/EnrollmentController" method=post>
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="45%">Description</th>
					<th width="55%">Detail</th>
				</tr>
				<tr>
					<td><fmt:message key="student.id" /></td>
					<td><input type="text" name="studentID"
						value="${param['courseID']}" size=25 maxlength=20 class="txtGroup"> <input
						type="hidden" name="ins" value="true" required /> <%-- </c:if>  --%>
				</tr>
				<tr>
					<td><fmt:message key="course.id" /></td>
					<td>
						<%-- <c:if test="${param['insert']==t}"> --%> <input type="text"
						name="courseID" value="${param['studentID']}" size=25 maxlength=20
						required class="txtGroup"/> <input type="hidden" name="ins" value="true" required/>
				</tr>
			<%-- 	<tr>
					<td><fmt:message key="enroll.courseStart" /></td>
					<td><input name="CourseStart" type="date"
						class="enrollmentDate" value="${param['courseStart']}" size=25 required/></td>
				</tr> --%>
		<%-- 		<tr>
					<td><fmt:message key="enroll.enrollBy" /></td>
					<td><input name="EnrollBy" type="text" class="enrollmentDate"
						value="${param['EnrollBy']}" size=25 required/></td>
				</tr> --%>

				<%-- 				<tr>
					<td><fmt:message key="enroll.studentGrade" /></td>
					<td><input type="text" name="Grade" value="${param['Grade']}"
						size=25 maxlength=20 readonly="readonly"></td>
				</tr> --%>
				<tr>
						<td><c:if test="${param['insert']==t}">
							<input class="buttonGroup" type="submit" value="Add Enrollment">
							<input type="hidden" name="ins" value="true" />
						</c:if> <c:if test="${param['update']==t}">
							<input class="buttonGroup" type="submit" value="Update Enrollment">
							<input type="hidden" name="ins" value="false" />
						</c:if></td>
					<td><input class="buttonGroup" type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>
	</form>
	</div>
</body>
</html>